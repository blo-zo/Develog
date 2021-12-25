package semi.blozo.develog.post.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import semi.blozo.develog.board.model.service.PostingService;
import semi.blozo.develog.board.model.vo.Category;
import semi.blozo.develog.board.model.vo.PostVO;
import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.board.model.vo.ThumbImgVO;
import semi.blozo.develog.common.MyRenamePolicy;
import semi.blozo.develog.member.model.vo.Member;
import semi.blozo.develog.post.model.service.PostService;
import semi.blozo.develog.post.model.service.ReplyService;
import semi.blozo.develog.post.model.vo.Blog;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostCategory;
import semi.blozo.develog.post.model.vo.PostImage;
import semi.blozo.develog.post.model.vo.PostPagination;
import semi.blozo.develog.post.model.vo.PostReply;

@WebServlet("/blog/*")
public class PostController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

				// 요청 방식
				String method = req.getMethod();
				
				
				// requestUrI에서 '/' 개수를 찾아서 1개인 경우 : 블로그 메인
				// 2개인 경우 : 마지막 부분을 잘라서 명령어(command : insert/update/delete)로  사용한다 -> split
				String uri = req.getRequestURI();
				String contextPath = req.getContextPath();
				
				String command = uri.substring( (contextPath + "/blog/" ).length());
				
				
				// String을 직접 인코딩->디코딩하면 한글로 잘 돌아온다.
				// command(uri 주소 맨 뒤 한글값)에서 인코딩->디코딩 하면 한글로 나오지 않는다.
				
				
				String[] arr = uri.substring( (contextPath + "/blog/" ).length()).split("/");	
				// 주소의 뒷부분을 '/'로 나누어서 저장하는 배열
				
				
				String path = null;
				RequestDispatcher dispatcher = null;
				String message = null;
				
				HttpSession session = req.getSession();
				
				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				
				
				try {
					
					
					PostService service = new PostService();
					
					int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
					
					
					// 블로그 메인 페이지 ( + 정렬, 검색 만들어야함)
					if(arr.length == 1) {	
						
						// /blog/memberName 값
						String memberName = URLDecoder.decode(arr[0],"UTF-8");
						
						// memberName을 통해 블로그 객체 생성 (해당 이름의 블로그가 있는지 찾음) 
						Blog blog = service.selectBlog(memberName);
						
						if(blog != null) {	// 해당 블로그가 있는 경우
							
							// 특정 블로그에 있는 전체 게시글 수 카운트
							PostPagination blogPostPagination = service.getPostPagination(cp, memberName);
							
							
							// 카테고리 메뉴 추가, 삭제에 사용
							// 로그인 회번 번호 조회
							
							int memberNo = 0;
							if(loginMember != null) memberNo = loginMember.getMemberNo();
							
							List<Post> postList = service.selectBlogPostList(blogPostPagination, memberName);
							
							
							// 블로그 태그 조회



							List<TagVO> tagListAll = service.selectBlogTagList(blog.getBlogNo());
							
							
							
							// 화면 출력하기
							req.setAttribute("blog", blog);
							req.setAttribute("blogPostPagination", blogPostPagination);
							req.setAttribute("postList", postList);
//							req.setAttribute("tagListAll", tagListAll);
							
							
							path = "/WEB-INF/views/post/blogMain.jsp";
							dispatcher = req.getRequestDispatcher(path);
							dispatcher.forward(req, resp);
							
						}else { // 블로그가 없는 경우
							
							req.getSession().setAttribute("message", "존재하지 않는 블로그입니다.");
							resp.sendRedirect(req.getContextPath());
							
						}
						
						
						
					}else {
						
						// 포스트 상세 조회
						
						if(arr[1].equals("view")) {
							
							// pno, cp
							int postNo = Integer.parseInt(req.getParameter("pno"));
							
							String memberName = URLDecoder.decode(arr[0],"UTF-8");
							
							
							// 조회수에 사용
							int memberNo = 0;
							
							if(loginMember != null) memberNo = loginMember.getMemberNo();
							
							// 포스트 조회
							Post post = service.selectPost(postNo, memberNo);
							
							if(post != null) {
								
								// 태그 조회
								List<TagVO> tagList = service.selectTagList(postNo);
								
								// 블로그 태그 조회
								List<TagVO> tagListAll = service.selectBlogTagList(post.getBlogNo());
								
								if(!tagList.isEmpty()) {
									post.setTagList(tagList);
								}
								
								// + 댓글 조회
								List<PostReply> prList = new ReplyService().selectPostReplyList(postNo);
								
								// 좋아요 여부 저장용
								int likeYN = service.likedPost(postNo, memberNo);
								
								if(likeYN == 1) {	// 좋아요한 게시글일 경우
									req.setAttribute("likeYN", likeYN);
								}
								
								req.setAttribute("prList", prList);
								req.setAttribute("replyCount", prList.size());
								req.setAttribute("post", post);
								req.setAttribute("tagList", tagList);
								req.setAttribute("tagListAll", tagListAll);
								
								path = "/WEB-INF/views/post/postView.jsp";
								dispatcher = req.getRequestDispatcher(path);
								dispatcher.forward(req, resp);
								
								
							}else {
								
								req.getSession().setAttribute("message", "존재하지 않는 포스트입니다.");
								
								resp.sendRedirect( req.getContextPath() + "/blog/" + memberName );
								
							}
							
							
						}
						
						
						
						// 포스트 수정 화면 전환 (로그인회원 == 작성자일 경우에만 수정버튼 노출)
						else if(arr[1].equals("updateForm")) {
							
							int postNo = Integer.parseInt(req.getParameter("pno"));
							
							System.out.println(postNo);
							
							// 1. 수정할 게시글 조회하기
							Post post = service.updateView(postNo);
							
							// 2. 카테고리 목록 조회
//							List<Category> category = new PostingService().selectCategory(loginMember.getBlogNo());
							List<Category> category = new PostingService().selectCategory(21);
							
							// 3. 태그 목록 조회하기
							List<TagVO> tagList = service.selectTagList(postNo);
							
							// 4. 썸네일 이미지 조회하기
							PostImage thumbImg = service.selectThumbImg(postNo);
							
							req.setAttribute("post", post);
							req.setAttribute("category", category);
							req.setAttribute("tagList", tagList);
							req.setAttribute("thumbImg", thumbImg);
							
							
							path = "/WEB-INF/views/post/postUpdate.jsp";
							dispatcher = req.getRequestDispatcher(path);
							dispatcher.forward(req, resp);
							
						}
						
						
						// 포스트 수정하기
						else if(arr[1].equals("update")) {
							
							 int maxSize = 1024 * 1024 * 100;// 100MB
							 String root = session.getServletContext().getRealPath("/");
							 String filePath = "/resources/images/board/";
							 String realPath = root + filePath;
							
							 MultipartRequest mReq
							 	= new MultipartRequest(req, realPath , maxSize ,"UTF-8" ,new MyRenamePolicy());
							 
							// 1) 텍스트 형식의 파라미터
							String postTitle = mReq.getParameter("postTitle");
							String postContent =  mReq.getParameter("postContent");
							int categoryCode = Integer.parseInt(mReq.getParameter("categoryCode"));
							int postStatusCode = Integer.parseInt(mReq.getParameter("postStatusCode"));
							
							// 로그인멤버에 블로그번호 세팅되어있나 확인해보기
							int blogNo = ((Member)req.getSession().getAttribute("loginMember")).getBlogNo();
							
							// ****************** 테스트용 임의로 지정 ***************************
							blogNo = 21;
							
							// 수정할 게시글 번호 얻어오기 (insert와의 차이점)
							int postNo = Integer.parseInt(mReq.getParameter("pno"));
							
							PostVO postVO = new PostVO();
							
							postVO.setPostTitle(postTitle);
							postVO.setPostContent(postContent);
							postVO.setCategoryCode(categoryCode);
							postVO.setPostStatusCode(postStatusCode);
							postVO.setBlogNo(blogNo);
							postVO.setPostNo(postNo);
								
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
							
							System.out.println(tagVOList);
							
							// 썸네일 이미지 처리 
							// 2) 파일 형식의 파라미터
							Enumeration<String> files = mReq.getFileNames();
							
							// 업로드 된 이미지 정보를 담을 객체 생성
							PostImage thumbImg = new PostImage();
							
							// hasMoreElements() : 다음 요소가 있으면 true 
							if( files.hasMoreElements() ) {
								// 썸네일 추가
								// 썸네일vo가 잘 담겨왔는지 확인 후 result postVO 방식처럼 진행되고
								
								String name = files.nextElement(); // 다음 요소값(name) 얻어오기
								
								if( mReq.getFilesystemName(name) != null) { 
									
									//변경된 값들을 담을 객체
									thumbImg.setPostImgName(mReq.getFilesystemName(name));
									thumbImg.setPostImgOriginal(mReq.getOriginalFileName(name));
									thumbImg.setPostImgPath(filePath); // 파일이 있는 주소 경로
									
								}
														
							}
							
							// service로 넘기기
							int result = service.updatePost(postVO, tagVOList, thumbImg);
							
							if(result > 0) {
								
								message = "포스트가 수정되었습니다.";
								byte[] ptext = loginMember.getMemberNm().getBytes("UTF-8");
								String value = new String(ptext, "ISO-8859-1"); 
								path = "../" + value + "/?pno=" + postVO.getPostNo();
									
							}else {
								
								message = "포스트 수정 중 문제가 발생하였습니다.";
								path = "updateForm";
								
							}
							
							session.setAttribute("message", message);
							resp.sendRedirect(path);
							
							
						}
						
						// 포스트 삭제하기
						else if(arr[1].equals("delete")) {
							
							int postNo = Integer.parseInt(req.getParameter("pno"));
							String memberName = req.getParameter("memberName");
							
							int result = service.deletePost(postNo);
							
							if(result > 0) {
								
								message = "포스트가 삭제되었습니다.";
								
								// 포스트 리스트 페이지로 돌아가기
								// 브라우저 인코딩...
								byte[] ptext = memberName.getBytes("UTF-8");
								String value = new String(ptext, "ISO-8859-1"); 
								path = "../" + value;
								
							}else {
								
								message = "포스트 삭제 중 문제가 발생했습니다.";
								
								byte[] ptext = memberName.getBytes("UTF-8");
								String value = new String(ptext, "ISO-8859-1"); 
								path = "../" + value;
								
							}
							
							session.setAttribute("message", message);
							resp.sendRedirect(path);
							
							
						}
						
						// 포스트 신고하기
						else if(arr[1].equals("reportPost")) {
							
							String memberName = req.getParameter("memberName");
							String reportPostContent = req.getParameter("reportPostContent");
							int postNo = Integer.parseInt(req.getParameter("reportPostNo"));
							int memberNo = loginMember.getMemberNo();
							
							
							int result = service.reportPost(postNo, memberNo, reportPostContent);
							
							if(result>0) {
								message="신고가 정상적으로 접수되었습니다.";
								// 포스트 리스트 페이지로 돌아가기
								// 브라우저 인코딩...
								byte[] ptext = memberName.getBytes("UTF-8");
								String value = new String(ptext, "ISO-8859-1"); 
								path = "../" + value;
							}else {
								message="신고 과정 중 문제가 발생했습니다.";
								// 포스트 리스트 페이지로 돌아가기
								// 브라우저 인코딩...
								byte[] ptext = memberName.getBytes("UTF-8");
								String value = new String(ptext, "ISO-8859-1"); 
								path = "../" + value;
							}
							
							session.setAttribute("message", message);
							resp.sendRedirect(path);
							
							
						}
						
						
						
						// ----------------------- 댓글 ------------------------
						
						
						// 댓글 조회
						else if(arr[1].equals("reply")) {
							
							
							if(arr[2].equals("select")) {
								
								// 파라미터 얻어오기
								int postNo = Integer.parseInt(req.getParameter("postNo"));
								
								List<PostReply> prList = new ReplyService().selectPostReplyList(postNo);
								
								// ajax 비동기 통신 시
								// 연결된 스트림을 이용하여 값(JSON 형태)만을 내보낸다.
								
								// JSONSimple, GSON 둘 중 하나 사용
								new Gson().toJson(prList, resp.getWriter());
								
							}
							
							
							// 댓글 삽입
							else if(arr[2].equals("insert")) {
			                     
			                     int memberNo = 0;
			                     
			                     if(loginMember != null) memberNo = loginMember.getMemberNo();
								
								int postNo = Integer.parseInt(req.getParameter("postNo"));
								String replyContent = req.getParameter("replyContent");
								String secretReply = req.getParameter("secretReply");	// 비밀글 여부
								
								PostReply reply = new PostReply();
								
								reply.setMemberNo(memberNo);
								reply.setPostNo(postNo);
								reply.setReplyContent(replyContent);
								
								int result = new ReplyService().insertPostReply(reply, secretReply);
								
								resp.getWriter().print(result);
								
							}
							
							
							// 댓글 수정
							else if(arr[2].equals("update")) {
								
								int replyNo = Integer.parseInt(req.getParameter("replyNo"));
								String replyContent = req.getParameter("replyContent");
								
								resp.getWriter().print(new ReplyService().updateReply(replyNo, replyContent));
							}
							
							
							// 댓글 삭제
							else if(arr[2].equals("delete")) {
								
								int replyNo = Integer.parseInt(req.getParameter("replyNo"));
								
								resp.getWriter().print(new ReplyService().deleteReply(replyNo));
								
							}
							
							
							// 댓글 신고
							else if(arr[2].equals("report")) {
								
								String memberName = req.getParameter("memberName");
								String reportReplyContent = req.getParameter("reportReplyContent");
								int replyNo = Integer.parseInt(req.getParameter("replyNo"));
								int memberNo = loginMember.getMemberNo();
								
								System.out.println(memberName);
								System.out.println(memberNo);
								
								
							}
							
							
						}	// 댓글 부분 END
						
						
						
						// -------------------------- 좋아요 부분 ------------------------------------
						
						// 좋아요 입력/삭제
						else if(arr[1].equals("like")) {
							
							int memberNo = 0;
							if(loginMember != null) memberNo = loginMember.getMemberNo();
							int postNo = Integer.parseInt(req.getParameter("postNo"));
							
							int favoriteCount = service.likePost(memberNo, postNo);
							
							resp.getWriter().print(favoriteCount);
							
						}
						
						
						// 좋아요 수 조회하기
						else if(arr[1].equals("selectPostLike")) {
							
							int memberNo = 0;
							if(loginMember != null) memberNo = loginMember.getMemberNo();
							int postNo = Integer.parseInt(req.getParameter("postNo"));
							
							int postLikeCount = service.selectPostLikeCount(memberNo, postNo);
							
							int likeCount = service.setLikeCount(postNo, postLikeCount);
							
							resp.getWriter().print(postLikeCount);
							
						}
						
						
						
						
						
						
						
						
						
						
						
					}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			session.setAttribute("message", "오류가 발생했습니다. 메인페이지로 이동합니다.");
			
			resp.sendRedirect(req.getContextPath() + "/main");
			
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req,resp);
	
	}
	
}
