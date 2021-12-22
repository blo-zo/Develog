package semi.blozo.develog.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.blozo.develog.member.model.Member;
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
				
				
				String[] arr = uri.substring( (contextPath + "/blog/" ).length()).split("/");	
				// 주소의 뒷부분을 '/'로 나누어서 저장하는 배열
				
				
				String path = null;
				RequestDispatcher dispatcher = null;
				String message = null;
				
				HttpSession session = req.getSession();
				
				//-----------------------------------------------------------------------
				// 샘플 로그인 데이터 세팅
				
//				Member loginMember = new Member("123123!", "뚱이", "ddong2@gmail.com");
//				loginMember.setMemberNo(1);
//				loginMember.setStatusCd(200);
//				loginMember.setGradeCd(100);
//				
//				session.setAttribute("loginMember", loginMember);
//				session.setMaxInactiveInterval(1800);
				//-----------------------------------------------------------------------
				
				
				try {
					
					
					PostService service = new PostService();
					
					int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
					
					
					
					
					
//					Member loginMember = (Member)req.getSession().getAttribute("loginMember");

					
					
					// 블로그 메인 페이지 ( + 정렬, 검색 만들어야함)
					if(arr.length == 1) {	
						
						
						//포스트를 클릭하면 이름,소개,블로그제목을 얻어온다.
						String memberName = req.getParameter("blogMemberName");
						String intro = req.getParameter("blogIntro");
						String blogTitle = req.getParameter("blogTitle");
						
						System.out.println(memberName);
						System.out.println(intro);
						System.out.println(blogTitle);
						
						
						memberName = "유동";
						
						
						// 특정 블로그에 있는 전체 게시글 수 카운트
						PostPagination blogPostPagination = service.getPostPagination(cp, memberName);
						
						
						// 카테고리 메뉴 추가, 삭제에 사용
						// 로그인 회번 번호 조회
						Member loginMember = (Member)req.getSession().getAttribute("loginMember");
						
//						int memberNo = 0;
						int memberNo = 1;
						if(loginMember != null) memberNo = loginMember.getMemberNo();
						
						List<Post> postList = service.selectBlogPostList(blogPostPagination, memberName);
						
						
						// 화면 출력하기
						req.setAttribute("blogPostPagination", blogPostPagination);
						req.setAttribute("postList", postList);
						
						
						path = "/WEB-INF/views/post/blogMain.jsp";
						dispatcher = req.getRequestDispatcher(path);
						dispatcher.forward(req, resp);
						
						
						
					}else {
						
						// 포스트 상세 조회
						
						if(arr[1].equals("view")) {
							
							// pno, cp
							int postNo = Integer.parseInt(req.getParameter("pno"));
							
							String memberName = req.getParameter("blogMemberName");
							String intro = req.getParameter("blogIntro");
							String blogTitle = req.getParameter("blogTitle");
							
							System.out.println(memberName);
							System.out.println(intro);
							System.out.println(blogTitle);
							
							
							
							// 조회수에 사용
							Member loginMember = (Member)req.getSession().getAttribute("loginMember");
							
							int memberNo = 0;
							
							if(loginMember != null) memberNo = loginMember.getMemberNo();
							
							
							Post post = service.selectPost(postNo, memberNo);
							
							
							if(post != null) {
								
								// + 댓글 조회
								List<PostReply> prList = new ReplyService().selectPostReplyList(postNo);
								
								req.setAttribute("prList", prList);
								req.setAttribute("replyCount", prList.size());
								req.setAttribute("post", post);
								
								path = "/WEB-INF/views/post/postView.jsp";
								dispatcher = req.getRequestDispatcher(path);
								dispatcher.forward(req, resp);
								
								
							}else {
//								req.getSession().setAttribute("message", "삭제되었거나 존재하지 않는 포스트입니다.");
								
								
								// 경로 동적 요소로 바꾸기
								
								resp.sendRedirect("유동");
							}
							
							
						}
						
						
						// 포스트 수정 화면 전환 (로그인회원 == 작성자일 경우에만 수정버튼 노출)
						else if(arr[1].equals("updateForm")) {
							
							int postNo = Integer.parseInt(req.getParameter("pno"));
							
							// 1. 수정할 게시글 조회하기
							Post post = service.updateView(postNo);
							
							// 2. 카테고리 목록 조회
							// List<Category> category = service.selectCategory();
							
							req.setAttribute("post", post);
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
							
//							int result = service.deletePost(postNo);
//							
//							if(result > 0) { // 삭제 성공
//								
//								path = "뚱이";
//								
//							}else { // 실패
//								
//								path = "view";
//								
//							}
							
							
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
								
								//int memberNo = Integer.parseInt(req.getParameter("memberNo"));
								int memberNo = 1;	// 테스트용
								
								int postNo = Integer.parseInt(req.getParameter("postNo"));
								String replyContent = req.getParameter("replyContent");
								String secretReply = req.getParameter("secretReply");	// 비밀글 여부
								
								System.out.println(secretReply);
								
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
						
						
						
						
						
						
						
						
						
						
						
						
					}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req,resp);
	
	}
	
}
