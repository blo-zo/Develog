package semi.blozo.develog.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.blozo.develog.post.model.service.PostService;
import semi.blozo.develog.post.model.vo.Post;
@WebServlet("/main/*")
public class ManiServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// 메인 페이지 요청과 동시에 블로그 전체 게시글 수 조회 후 카드 생성
			
			PostService service = new PostService();
			//List<Post> postListAll = service.selectBlogPostListAll();
			
			
			String path = "/WEB-INF/views/common/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			

			
		} 
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp); // do get 으로 post 요청처리
		
		}
		
}
