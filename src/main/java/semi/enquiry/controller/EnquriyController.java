package semi.enquiry.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.enquiry.model.service.EnquiryService;
import semi.enquiry.model.vo.Enquiry;
import semi.enquiry.model.vo.Pagination;
import semi.member.model.vo.Member;



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
					
				Pagination pagination = service.getPagination(cp);
			
				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				int memberNo = 0;
				if(loginMember != null) memberNo = loginMember.getMemberNo();
					
				//    페이징 처리에 필요 숫자들을 만들어냄
				// 3. 죄회 되어지는 게시글의 번호를 계산하여 DB에서 조회해옴
				List<Enquiry> enquiryList = service.selectEnquiryList(pagination,memberNo);
				System.out.println(enquiryList);
				// 4. 화면 출력
				req.setAttribute("pagination", pagination);
				req.setAttribute("enquiryList", enquiryList);
				path = "/WEB-INF/views/enquriy/enquriyList.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			// (조건식 ? 참인 경우 : 거짓인 경우)
			
			
			
				
				
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