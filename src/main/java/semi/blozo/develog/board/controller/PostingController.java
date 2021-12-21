package semi.blozo.develog.board.controller;

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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import semi.blozo.develog.board.model.vo.Category;
import semi.blozo.develog.board.model.service.PostingService;
import semi.blozo.develog.board.model.vo.PostImageVO;
import semi.blozo.develog.board.model.vo.PostVO;
import semi.blozo.develog.common.MyRenamePolicy;
import semi.blozo.develog.member.model.Member;

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

			PostingService service = new PostingService();
			
			
			// 게시글 삽입
			if(command.equals("insert")) {
				System.out.println("@insert일때: "+method);
				//GET 방식 요청 -> 게시글 등록 화면 전환
				if(method.equals("GET")) {
					// 카테고리 테이블 내용 조회하기 // VO에 카테고리 
					List<Category> category = service.selectCategory();
					
					req.setAttribute("category", category);
					System.out.println("@get일때");
					
					path  = "/WEB-INF/views/board/posting.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				
					
				} else { // POST 방식 요청
					System.out.println("@post일때" + method); 

					// 2. 업로드 되는 파일을 서버 컴퓨터 어디에 저장할지 경로 지정
					HttpSession session = req.getSession();
					int maxSize = 1024 * 1024 * 100;
					
					String root =  session.getServletContext().getRealPath("/");
					
					// 나머지 파일 경로(DB에 저장되어 주소경로로 사용할 예정)
					String filePath = "/resources/images/board/";
					
					// 실제 경로
					String realPath = root + filePath;
					realPath = "C:\\workspace\\Servlet_JSP\\Develog\\src\\main\\webapp\\resources\\images\\board";
					
					 MultipartRequest mReq = new MultipartRequest(req, filePath, maxSize, "utf-8",new MyRenamePolicy());
					 // 1) 텍스트 형식의 파라미터
					System.out.println("@post일때2"+req);
					String postTitle = mReq.getParameter("postTitle");
					String postContent = mReq.getParameter("postContent");
					int categoryCode = Integer.parseInt( mReq.getParameter("categoryCode"));
					int postStatusCode = Integer.parseInt( mReq.getParameter("postStatusCode"));
					
					System.out.println("@post일때3"+postTitle);
					System.out.println("@post일때3"+postContent);
					System.out.println("@post일때3"+categoryCode);
					System.out.println("@post일때3"+postStatusCode);
				         
					int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
					
					PostVO postVO = new PostVO();
					postVO.setPostTitle(postTitle);
					postVO.setPostContent(postContent);
					postVO.setCategoryCode(categoryCode);
					postVO.setPostStatusCode(postStatusCode);
					postVO.setMemberNo(memberNo); 
			         
					
					System.out.println(postTitle);
					
					
					// 파일 이미지 처리 
					if(ServletFileUpload.isMultipartContent(req)) {
				         String root = req.getSession().getServletContext().getRealPath("/");
				         String savePath = root + "/resources/images/board/";
				         int maxSize = 1024 * 1024 * 10;  // 업로드 사이즈 제한 10M 이하
				         
				         MultipartRequest mReq = new MultipartRequest(req, savePath, maxSize, "utf-8",new MyRenamePolicy());
				         
				         // 단일 파일 업로드
				         Enumeration<String> files = mReq.getFileNames();
				         String saveFile = null;
				         String originFile = null;
				         if(files.hasMoreElements()) {
				            
				            String name = files.nextElement();
				            System.out.println(name);
				            if(mReq.getFilesystemName(name) != null) {
				               saveFile = mReq.getFilesystemName(name);
				               originFile = mReq.getOriginalFileName(name);
				            }
				         }
				          
						
				      } // if end
					   
				         
					
					
					
					
					 
				}

					
//					
//					// postVO, imgList db에 삽입하는 서비스 호출 후 결과 반환
//					int result = service.insertPost(postVO, imgList );
//					
//					if(result > 0 ) {
//						message = "게시글이 작성되었습니다.";
////						path = 작성된 후 게시글 페이지 주소 
//						
//					} else {				
//						message = "게시글 등록 중 문제가 발생하였습니다.";
//						path = "insert"; 
//					}
//					
//					session.setAttribute("message", message);
//					resp.sendRedirect(path);
				
			
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
