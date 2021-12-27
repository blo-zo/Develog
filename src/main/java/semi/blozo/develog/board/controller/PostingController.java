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
		

		// 썸네일 이미지
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)req.getSession().getAttribute("loginMember");
		
		try {
			// 파일 들어오면 담을 객체 선언
			PostingService service = new PostingService();
			
			// 게시글 삽입
			if(command.equals("insert")) {
				//GET 방식 요청 -> 게시글 등록 화면 전환
				if(method.equals("GET")) {
					
					int blogNo = ((Member)req.getSession().getAttribute("loginMember")).getBlogNo();
					// 카테고리 테이블 내용 조회하기 // VO에 카테고리 
					List<Category> category = service.selectCategory(blogNo);
					
					req.setAttribute("category", category);
					
					
					path  = "/WEB-INF/views/board/posting.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				
				} else { // POST 방식 요청
					
					
					int maxSize = 1024 * 1024 * 100;
					String root =  session.getServletContext().getRealPath("/");
					String filePath = "/resources/images/board/";
					String realPath = root + filePath;
					
					MultipartRequest mReq 
					= new MultipartRequest( req, realPath, maxSize, "UTF-8", new MyRenamePolicy() );
					
					// 1) 텍스트 형식의 파라미터
					String postTitle = mReq.getParameter("postTitle");
					String postContent =  mReq.getParameter("postContent");
					int categoryCode = Integer.parseInt(mReq.getParameter("categoryCode"));
					int postStatusCode = Integer.parseInt(mReq.getParameter("postStatusCode"));
					int blogNo = ((Member)req.getSession().getAttribute("loginMember")).getBlogNo();
					
					PostVO postVO = new PostVO();
					postVO.setPostTitle(postTitle);
					postVO.setPostContent(postContent);
					postVO.setCategoryCode(categoryCode);
					postVO.setPostStatusCode(postStatusCode);
					postVO.setBlogNo(blogNo);
					
					// 태그
					String[] tags = mReq.getParameterValues("tags");
					
					List<TagVO> tagVOList = new ArrayList<TagVO>();
					
					if(tags != null) {
						for(String vo : tags) {
							TagVO tagvo = new TagVO();
							tagvo.setTagName(vo);
							tagVOList.add(tagvo);
							
						}
					}
					
					
					// 썸네일 이미지 처리 
					// 2) 파일 형식의 파라미터
					Enumeration<String> files = mReq.getFileNames();
					
					// 업로드 된 이미지 정보를 담을 List 생성
					List<ThumbImgVO> imgList = new ArrayList<ThumbImgVO>();
					
					
					// hasMoreElements() : 다음 요소가 있으면 true 
					while( files.hasMoreElements() ) {
						// 썸네일 추가
						// 썸네일vo가 잘 담겨왔는지 확인 후 result postVO 방식처럼 진행되고
						
						String name = files.nextElement(); // 다음 요소값(name) 얻어오기
						
						if( mReq.getFilesystemName(name) != null) { 
							
							//변경된 값들을 담을 객체
							ThumbImgVO temp = new ThumbImgVO();
							
							temp.setThumbImgName(mReq.getFilesystemName(name));
							temp.setThumbImgOriginal(mReq.getOriginalFileName(name));
							temp.setThumbImgPath(filePath); // 파일이 있는 주소 경로
							
							
							// imgList에 추가 
							imgList.add(temp);
						
						}
												
					}
					
					
					
					// service로 넘기기
					int result = service.insertPost(postVO, tagVOList, imgList);
					
					if (result > 0) {
						message = "게시글이 작성되었습니다.";
						
						
						byte[] ptext = loginMember.getMemberNm().getBytes("UTF-8");
						
                        String value = new String(ptext, "ISO-8859-1"); 
                        path = req.getContextPath() + "/blog/" + value + "/view?pno=" + postVO.getPostNo();
                        
//						/Develog/blog/봉국/view?pno=291
					} else {
						message = "게시글 등록 중 문제가 발생하였습니다.";
						path = "insert";
					}

					req.getSession().setAttribute("message", message);
					resp.sendRedirect(path);
				  
					
					
				} // else end
				
			}

			
			
			
			else if(command.equals("insertImage")) {
					
				int maxSize = 1024 * 1024 * 100;
				String root =  session.getServletContext().getRealPath("/");
				String filePath = "/resources/images/board/";
				String realPath = root + filePath;
				
				MultipartRequest mReq 
				= new MultipartRequest( req, realPath, maxSize, "UTF-8", new MyRenamePolicy() );
				
				Enumeration<String> files = mReq.getFileNames();
				
				if( files.hasMoreElements() ) {
					String name = files.nextElement(); // 다음 요소값(name) 얻어오기
					System.out.println(mReq.getOriginalFileName(name));
					System.out.println(mReq.getFilesystemName(name));
					resp.getWriter().print(req.getContextPath()+filePath+mReq.getFilesystemName(name));
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
