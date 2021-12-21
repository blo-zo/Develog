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
				
				//-----------------------------------------------------------------------
				// 샘플 로그인 데이터 세팅
				
				Member loginMember = new Member("123123!", "뚱이", "ddong2@gmail.com");
				loginMember.setMemberNo(1);
				loginMember.setStatusCd(200);
				loginMember.setGradeCd(100);
				
				HttpSession session = req.getSession();
				session.setAttribute("loginMember", loginMember);
				//-----------------------------------------------------------------------
				
				
				try {
					
					PostService service = new PostService();
					
//					HttpSession session = req.getSession();
					
					int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
					

					// 메인페이지에서 blog 제목(글쓴이 memberName)을 파라미터로 받아오기
					
					
					// 블로그 메인 페이지
					if(arr.length == 1) {	
						
						// 특정 블로그에 있는 전체 게시글 수 카운트
						
						/* req.getParameter("blog"); */
						String memberName = "뚱이";
						
						PostPagination blogPostPagination = service.getPostPagination(cp, memberName);
						
						
						// 카테고리 메뉴 추가, 삭제에 사용
						// 로그인 회번 번호 조회
//						Member loginMember = (Member)req.getSession().getAttribute("loginMember");
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
						
						if(arr[1].equals("view")) {
							
							// pno, cp
							int postNo = Integer.parseInt(req.getParameter("pno"));
							
							
							// 게시글 수정 삭제에 사용예정
//							Member loginMember = (Member)req.getSession().getAttribute("loginMember");
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
								
								resp.sendRedirect("뚱이");
							}
							
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
								
								PostReply reply = new PostReply();
								
								reply.setMemberNo(memberNo);
								reply.setPostNo(postNo);
								reply.setReplyContent(replyContent);
								
								int result = new ReplyService().insertPostReply(reply);
								
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
								
//								resp.getWriter().print(new ReplyService().deleteReply(replyNo));
								
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
