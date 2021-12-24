package semi.blozo.develog.main.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.blozo.develog.post.model.service.PostService;
import semi.blozo.develog.post.model.vo.Blog;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostPagination;
@WebServlet("/main/*")
public class ManiServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//String method = req.getMethod();
			//String uri = req.getRequestURI();
			//String command = uri.substring( (contextPath + "/main/").length());
			//String[] arr = uri.substring( (contextPath + "/main/" ).length()).split("/");
			String contextPath = req.getContextPath();
			
			String path = null;
			RequestDispatcher dispatcher = null;
			String message = null;
			
			
			try {
				
					
				PostService service = new PostService();
				
				
				int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));
				
				
				
//				if(arr.length == 1) {	
//				}
					
					
					// String memberName = URLDecoder.decode(arr[0],"UTF-8");
					
				//PostPagination blogPostPagination = service.getPostPagination(cp, memberName);
				//req.setAttribute("blogPostPagination", blogPostPagination);

					
				
					
				
				List<Post> postListAll = service.selectPostListAll();
				//화면출력
				System.out.println(postListAll);
				req.setAttribute("postListAll", postListAll);
				path = "/WEB-INF/views/common/main.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			// 최신순과 트랜드 순을 어떻게 나눠야될 것인가
						
						//String trend = req.getParameter("trend");
						//System.out.print(trend);
					
					
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			

			
		} 
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp); // do get 으로 post 요청처리
		
		}
		
}
