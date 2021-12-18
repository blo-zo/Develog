package edu.kh.semi.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.post.model.service.PostService;
import edu.kh.semi.post.model.vo.Blog;
import edu.kh.semi.post.model.vo.Post;
import edu.kh.semi.post.model.vo.PostCategory;
import edu.kh.semi.post.model.vo.PostPagination;
import edu.kh.semi.post.model.vo.PostReply;

@WebServlet("/blog/*")
public class PostController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 방식
		String method = req.getMethod();
		
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring( (contextPath + "/blog/" ).length());
		
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		try {
			
			
			PostService service = new PostService();
			
			HttpSession session = req.getSession();
			
			int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
			
			// 메인페이지에서 blog 제목을 파라미터로 받아오기
			
			
			
			// -------------- 블로그 메인 -------------------
			
			// 메인 페이지에서 작성자를 클릭하면 블로그 메인으로 이동
			// 요청 주소는 blog/@블로그타이틀 
			// 메인 페이지 주소가 @블로그타이틀인 경우코드 강사님께 물어보기
			if(command.equals("sample")) {
				
				
				// 쿼리스트링으로 블로그에 번호 부여(bNo)
				
				// 특정 블로그에 있는 전체 게시글 수 카운트
				
				String blogTitle = "sample"; /* req.getParameter("blog"); */
				
				PostPagination blogPostPagination = service.getPostPagination(cp, blogTitle);
				
				
				// 카테고리 메뉴 추가, 삭제에 사용
				// 로그인 회번 번호 조회
//				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
//				int memberNo = 0;
//				if(loginMember != null) memberNo = loginMember.getMemberNo();
				
				List<Post> postList = service.selectBlogPostList(blogPostPagination, blogTitle);
				
				
				// 화면 출력하기
				req.setAttribute("blogPostPagination", blogPostPagination);
				req.setAttribute("postList", postList);
				
				
				path = "/WEB-INF/views/post/blogMain.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
				
			}
			
			
			// 게시글 상세 페이지
			else if(command.equals("view")) {
				
				// pno, cp
				int postNo = Integer.parseInt(req.getParameter("pno"));
				
				// 게시글 수정 삭제에 사용예정
//				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				int memberNo = 0;
//				if(loginMember != null) memberNo = loginMember.getMemberNo();
				
				Post post = service.selectPost(postNo, memberNo);
				
				if(post != null) {
					
					// + 댓글 조회
					List<PostReply> prList = new ReplyService(). 
					
					req.setAttribute("post", post);
					
					path = "/WEB-INF/views/post/postView.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
					
				}else {
//					req.getSession().setAttribute("message", "삭제되었거나 존재하지 않는 포스트입니다.");
					resp.sendRedirect("main");
				}
				
			}
			
			
			// 게시글 작성 페이지
			else if(command.equals("insert")) {
				
				// 글 작성 페이지로 이동
				if(method.equals("GET")) {
					
					// 카테고리 조회하기
//					List<PostCategory> category = service.selectCategory();
					
//					req.setAttribute("category", category);
					
					path = "/WEB-INF/views/post/boardInsert.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
				}
				
				
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
