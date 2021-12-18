package semi.main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/main")
public class MainServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//메인페이지 요청 시 로직처리
			/*
			 * String main = req.getContextPath()+"/resources/css/mainPage.css";
			 * req.setAttribute("main", main);
			 */
			
			String path = "/WEB-INF/views/common/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		
		}
}

