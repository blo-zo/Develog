package semi.blozo.develog.post.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.member.model.vo.Member;
import semi.blozo.develog.post.model.service.PostService;
import semi.blozo.develog.post.model.service.ReplyService;
import semi.blozo.develog.post.model.vo.Blog;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostCategory;
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
				System.out.println("uri : " + uri);
				System.out.println("arr : " + Arrays.toString(arr));
				
				
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
						
						System.out.println(memberName);
						System.out.println(blog);
						
						if(blog != null) {	// 해당 블로그가 있는 경우
							
							// 특정 블로그에 있는 전체 게시글 수 카운트
							PostPagination blogPostPagination = service.getPostPagination(cp, memberName);
							
							
							// 카테고리 메뉴 추가, 삭제에 사용
							// 로그인 회번 번호 조회
							
							int memberNo = 0;
							if(loginMember != null) memberNo = loginMember.getMemberNo();
							
							List<Post> postList = service.selectBlogPostList(blogPostPagination, memberName);
							
							// list에서 post 하나씩 꺼내와 post.getPostContent().substring(0,50);
							
							// 화면 출력하기
							req.setAttribute("blog", blog);
							req.setAttribute("blogPostPagination", blogPostPagination);
							req.setAttribute("postList", postList);
							
							
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
							
							
							Post post = service.selectPost(postNo, memberNo);
							
							
							if(post != null) {
								
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
							
							// 1. 수정할 게시글 조회하기
							Post post = service.updateView(postNo);
							
							// 2. 카테고리 목록 조회
							// List<Category> category = service.selectCategory();
							
							// 3. 태그 목록 조회하기
							List<TagVO> tagList = service.selectTagList(postNo);
							
							System.out.println(tagList);
							
							req.setAttribute("post", post);
							req.setAttribute("tagList", tagList);
							// req.setAttribute("category", category);
							
							path = "/WEB-INF/views/post/postUpdate.jsp";
							dispatcher = req.getRequestDispatcher(path);
							dispatcher.forward(req, resp);
							
						}
						
						
						// 포스트 수정하기
						else if(arr[1].equals("update")) {
							
							
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
								System.out.println(memberName);
								System.out.println(path);
								System.out.println(value);
								
							}else {
								
								message = "포스트 삭제 중 문제가 발생했습니다.";
								
								// 포스트 리스트 페이지로 돌아가기
								path = req.getContextPath() + "/blog/" + memberName;
								
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
