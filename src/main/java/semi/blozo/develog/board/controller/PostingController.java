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
import semi.blozo.develog.board.model.vo.ThumbImgVO;
import semi.blozo.develog.board.model.vo.PostVO;
import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.common.MyRenamePolicy;
import semi.blozo.develog.member.model.vo.Member;

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

			PostingService service = new PostingService();
			
			// 게시글 삽입
			if(command.equals("insert")) {
				System.out.println("@insert일때: "+method);
				//GET 방식 요청 -> 게시글 등록 화면 전환
				if(method.equals("GET")) {
					
					int blogNo = ((Member)req.getSession().getAttribute("loginMember")).getMemberNo();
					// 카테고리 테이블 내용 조회하기 // VO에 카테고리 
					List<Category> category = service.selectCategory(blogNo);
					
					req.setAttribute("category", category);
					
					System.out.println(category);
					
					path  = "/WEB-INF/views/board/posting.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				
				} else { // POST 방식 요청
					System.out.println("@post일때" + method); 
					
					// 1) 텍스트 형식의 파라미터
	
					String postTitle = req.getParameter("postTitle");
					String postContent =  req.getParameter("postContent");
					int categoryCode = Integer.parseInt(req.getParameter("categoryCode"));
					int postStatusCode = Integer.parseInt(req.getParameter("postStatusCode"));
					int blogNo = ((Member)req.getSession().getAttribute("loginMember")).getMemberNo();
					
					PostVO postVO = new PostVO();
					postVO.setPostTitle(postTitle);
					postVO.setPostContent(postContent);
					postVO.setCategoryCode(categoryCode);
					postVO.setPostStatusCode(postStatusCode);
					postVO.setBlogNo(blogNo);
					
					
//					// 썸네일 이미지
//					HttpSession session = req.getSession();
//					
//					int maxSize = 1024 * 1024 * 100;
//					String root =  session.getServletContext().getRealPath("/");
//					String filePath = "/resources/images/board/";
//					String realPath = root + filePath;
//					
//					MultipartRequest mReq 
//					= new MultipartRequest( req, realPath, maxSize, "UTF-8", new MyRenamePolicy() );
					
					// 썸네일
					/*
					 * if( mReq.getFilesystemName(name) != null) {
					 * 
					 * 
					 * 
					 * }
					 * 
					 */
					
				
					String[] tags = req.getParameterValues("tags");
					
					List<TagVO> tagVOList = new ArrayList<TagVO>();
					if(tags != null) {
						for(String vo : tags) {
							TagVO tagvo = new TagVO();
							tagvo.setTagName(vo);
							
							tagVOList.add(tagvo);
						}
					}
					
					int result = service.insertPost(postVO, tagVOList);
					
					System.out.println("test : " + result);
					
					
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
