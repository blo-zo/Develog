package semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;


import semi.board.model.service.PostingService;
import semi.board.model.vo.PostImageVO;
import semi.board.model.vo.PostVO;
import semi.common.MyRenamePolicy;

@WebServlet("/board/*")
public class PostingController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		
		String uri =req.getRequestURI();
		String contextPath = req.getContextPath();

		String command = uri.substring(  (contextPath + "/board/").length() );
		// 요청 위임이나 redirect를 해야하는 것들 
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		try {
			path  = "/WEB-INF/views/board/posting.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
			PostingService service = new PostingService();
			System.out.println("@여깅오니?");
			
			
			// 게시글 삽입
			if(command.equals("insert")) {
				System.out.println("@insert일때: "+method);
				//GET 방식 요청 -> 게시글 등록 화면 전환
				if(method.equals("GET")) {
					
					System.out.println("@get일때");
					
					path  = "/WEB-INF/views/board/posting.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
					
				} else { // POST 방식 요청
					System.out.println("@post일때"+method); //POST까지는 잘 오는데  MultipartRequest가 문제다
					
					// 이미지 처리하는 controller와의 연결 필요!
					
					
					// 2. 업로드 되는 파일을 서버 컴퓨터 어디에 저장할지 경로 지정
					HttpSession session = req.getSession();
					
					String root =  session.getServletContext().getRealPath("/");
					
					// 나머지 파일 경로(DB에 저장되어 주소경로로 사용할 예정)
					String filePath = "/resources/images/board/";
					
					// 실제 경로
					String realPath = root + filePath;
					
					
					
					// 왜  int가 필요한ㄱ지
					MultipartRequest mReq 
					= new MultipartRequest(req, realPath, (Integer) null, new MyRenamePolicy());
					// ( 기존 req, 파일 저장할 곳, 파일 용량(지금x), (title, content같이 파일 아닌것들) 변환(지금x) , 파일인경우)
					
					
					System.out.println("@post일때2"+req);
					String postTitle = mReq.getParameter("postTitle");
					
					System.out.println("@post일때3"+postTitle);
					
					String postContent = mReq.getParameter("postContent");
					int categoryCode = Integer.parseInt( mReq.getParameter("categoryCode"));
					int postStatusCode = Integer.parseInt( mReq.getParameter("postStatusCode"));
					//int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
					
					PostVO postVO = new PostVO();
					postVO.setPostTitle(postTitle);
					postVO.setPostContent(postContent);
					postVO.setCategoryCode(categoryCode);
					postVO.setPostStatusCode(postStatusCode);
					
					
					System.out.println("@post일때"+postVO.toString() );
					
					// 2) 파일 형식의 파라미터
					Enumeration<String> files = mReq.getFileNames();
					
					// 업로드 된 이미지 정보를 담을 List 생성
					List<PostImageVO> imgList = new ArrayList<PostImageVO>();
					
					
					
					// postVO, imgList db에 삽입하는 서비스 호출 후 결과 반환
					int result = service.insertPost(postVO, imgList);
					
					if(result > 0 ) {
						message = "게시글이 작성되었습니다.";
//						path = 작성된 후 게시글 페이지 주소 
						
					} else {				
						message = "게시글 등록 중 문제가 발생하였습니다.";
						path = "insert"; 
					}
					
					session.setAttribute("message", message);
					resp.sendRedirect(path);
				}
			
			}
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
