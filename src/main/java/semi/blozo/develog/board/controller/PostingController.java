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
import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.common.MyRenamePolicy;
import semi.blozo.develog.member.model.Member;

@WebServlet("/board/*")
public class PostingController extends HttpServlet {

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
					
					
					
					// 왜  int가 필요한지
					MultipartRequest mReq 
					= new MultipartRequest(req, realPath, (Integer)0, new MyRenamePolicy());
					// ( 기존 req, 파일 저장할 곳, 파일 용량(지금x), (title, content같이 파일 아닌것들) 변환(지금x) , 파일인경우)
=======
					System.out.println("@post일때" + method); 
>>>>>>> 3594aa63d4f0ba7b18b875db6366064bdd16e65a
					
					// 1) 텍스트 형식의 파라미터
	
					String postTitle = req.getParameter("postTitle");
					String postContent =  req.getParameter("postContent");
					int categoryCode = Integer.parseInt(req.getParameter("categoryCode"));
					int postStatusCode = Integer.parseInt(req.getParameter("postStatusCode"));
					int blogNo = 3; //((Member)req.getSession().getAttribute("loginMember")).getMemberNo();
					
					PostVO postVO = new PostVO();
					postVO.setPostTitle(postTitle);
					postVO.setPostContent(postContent);
					postVO.setCategoryCode(categoryCode);
					postVO.setPostStatusCode(postStatusCode);
					postVO.setBlogNo(blogNo);
					
					String[] tags = req.getParameterValues("tags");
					
					List<TagVO> tagVOList = new ArrayList<TagVO>();
					
					for(String vo : tags) {
						TagVO tagvo = new TagVO();
						tagvo.setTagName(vo);
						tagVOList.add(tagvo);
					}
					
					int result = service.insertPost(postVO, tagVOList );
					
					if (result > 0) {
						message = "게시글이 작성되었습니다.";
						// path = 작성된 후 게시글 페이지 주소

					} else {
						message = "게시글 등록 중 문제가 발생하였습니다.";
						path = "insert";
					}

					req.getSession().setAttribute("message", message);
					resp.sendRedirect(path);
				  
				}
				
			}// insert if end
			
			else if(command.equals("insertImage")) {
			      if(ServletFileUpload.isMultipartContent(req)) {
			          String root = req.getSession().getServletContext().getRealPath("/");
			          String savePath = root + "/resources/images/board/";
			          int maxSize = 1024 * 1024 * 20;  // 업로드 사이즈 제한 20M 이하
			          
			          MultipartRequest multiRequest = new MultipartRequest(req, savePath, maxSize, "utf-8",new MyRenamePolicy());
			          
			          // 단일 파일 업로드
			          Enumeration<String> files = multiRequest.getFileNames();
			          String saveFile = null;
			          String originFile = null;
			          if(files.hasMoreElements()) {
			             
			             String name = files.nextElement();
			             System.out.println(name);
			             if(multiRequest.getFilesystemName(name) != null) {
			                saveFile = multiRequest.getFilesystemName(name);
			                originFile = multiRequest.getOriginalFileName(name);
			             }
			          }
			          
			          System.out.println(saveFile);
			          System.out.println(originFile);
			          
			          
			          String reqPath = req.getContextPath()+"/resources/images/board/";
			          
			          String uploadFile = reqPath + saveFile;
			          
			          System.out.println(uploadFile);
			          resp.getWriter().print(uploadFile);
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
