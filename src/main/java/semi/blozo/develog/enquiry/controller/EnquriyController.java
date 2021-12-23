package semi.blozo.develog.enquiry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.blozo.develog.enquiry.model.service.EnquiryService;
import semi.blozo.develog.enquiry.model.vo.Enquiry;
import semi.blozo.develog.enquiry.model.vo.Pagination;
import semi.blozo.develog.member.model.Member;


@WebServlet("/enquiry/*")
public class EnquriyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method = req.getMethod();
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring( (contextPath + "/enquiry/").length());
	
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		try {
			// command에 저장된 값에 따라 서로 다른 동작을 수행
			EnquiryService service =new EnquiryService();
			// **(중요)**
			// 게시판 관련 기능 수행 시 "현재 페이지" 는 여러 의미로 많이 사용되기 때문에
			// 미리 얻어와서 준비 시켜두는 것이 좋다
			int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
			// 게시글 목록 조회 Controller
			if(command.equals("list")) {
				
				// 목록 조회 + 페이징 처리
				// 목록 조회 순서
				// 1. DB에 저장된 조회 가능한 게시글 전체 수 카운트
				// 2. 전체 게시글 수 + 현재 페이지를 이용해서
					
				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				//int memberNo = (int)req.getSession().getAttribute("memberNo");;
				int memberNo = loginMember.getMemberNo();
				//System.out.println(memberNo);
				Pagination pagination = service.getPagination(cp,memberNo);
				
				if(loginMember != null) { memberNo = loginMember.getMemberNo();
				
				//    페이징 처리에 필요 숫자들을 만들어냄
				// 3. 죄회 되어지는 게시글의 번호를 계산하여 DB에서 조회해옴
				List<Enquiry> enquiryList = service.selectEnquiryList(pagination,memberNo);
				//System.out.println(enquiryList);
				// 4. 화면 출력
				req.setAttribute("pagination", pagination);
				req.setAttribute("enquiryList", enquiryList);
				path = "/WEB-INF/views/enquiry/enquiryList.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			
			
			
			
				
				
				}
			}else if(command.equals("view")){
				// 문의 넘버 받아오기
				int enquiryNo = Integer.parseInt(req.getParameter("no"));
	
				// 문의 상세 조회 서비스 호출 후 결과 반환 받기
				Enquiry enquiry = service.selectEnquiry(enquiryNo);
				if(enquiry !=  null) { //조회 성공
					
					req.setAttribute("enquiry", enquiry);
					
					path = "/WEB-INF/views/enquiry/enquiryView.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				else { //문의사항 없는 경우
					
					message = "문의사항이 존재하지 않습니다";
					req.getSession().setAttribute("message", message);
					resp.sendRedirect("list");
					
				}
			}else if(command.equals("insert")){
				if(method.equals("GET")) {
					
					path = "/WEB-INF/views/enquiry/enquiryInsert.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
					}else {
						
						Member loginMember = (Member)req.getSession().getAttribute("loginMember");
						int memberNo = 0;
						if(loginMember != null) memberNo = loginMember.getMemberNo();
						
						// POST 방식 삽입시
						String enquiryTitle = req.getParameter("enquiryTitle");
						String enquiryContent = req.getParameter("enquiryContent");
						//System.out.println(enquiryTitle);
						//System.out.println(enquiryContent);
						HttpSession session = req.getSession();
						try {
							int result = service.insertEnquiry(enquiryTitle , enquiryContent,memberNo);
							
							if(result > 0) {// 성공시
								message = "문의사항이 등록되었습니다.";
								// 상세 조회 redirect 
								path = "list";
							}else{// 실패
								
								message = "문의사항 등록 중 오류입니다.";
								path = "insert";
							}
							
							
						
							session.setAttribute("message", message);
							resp.sendRedirect(path);
						
						}catch(Exception e) {
							e.printStackTrace();
							
						}
						
					}
				
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
	}
	
	
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	
		}
	
	
	
	
}