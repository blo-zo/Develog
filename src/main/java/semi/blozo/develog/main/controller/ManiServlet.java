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
			String contextPath = req.getContextPath();
			
			String path = null;
			RequestDispatcher dispatcher = null;
			String message = null;
			
			try {
				
				PostService service = new PostService();
				
			//	int type = Integer.parseInt(req.getParameter("type"));
				
				
				
					

				List<Post> postListAll = service.selectPostListAll();
				
				
				//화면출력
				System.out.println(postListAll);
				req.setAttribute("postListAll", postListAll);
				path = "/WEB-INF/views/common/main.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
						
					
					
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			

			
		} 
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp); // do get 으로 post 요청처리
		
		}
		
}
