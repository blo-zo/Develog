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

import semi.blozo.develog.post.model.service.MainService;
import semi.blozo.develog.post.model.vo.Post;


@WebServlet("/main")
public class MainController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		MainService service = new MainService();
		
		try {
			
			HttpSession session = req.getSession();
			
			List<Post> postListAll = service.selectPostListAll();
			
			session.setAttribute("postListAll", postListAll);
			
			path = "/WEB-INF/views/post/mainBoard.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	
	}
	
	
}
