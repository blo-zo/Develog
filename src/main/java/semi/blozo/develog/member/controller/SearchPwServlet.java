package semi.blozo.develog.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.blozo.develog.member.model.service.MemberService;
import semi.blozo.develog.member.model.vo.Member;
@WebServlet("/member/searchpw")
public class SearchPwServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String searchPw = req.getContextPath()+"/resources/css/searchPw.css";
		req.setAttribute("searchPw", searchPw);
		
		String path = "/WEB-INF/views/member/searchPw.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("searchPwEmail");
		String searchPw = req.getParameter("searchPw");

		System.out.println("email" + email);
		System.out.println("searchPw" + searchPw);
		
	
		String message = null;
		String path = null;
		
		
		try {
			
			int result = new MemberService().searchPw(email,searchPw);
			System.out.println(result);
			if(result>0) {
				
				message = "비밀번호가 변경되었습니다";
				path = req.getContextPath()+"/main";
			}else {
				message = "비밀번호 수정중 오류가 발생했습니다.";
				path = "/WEB-INP/views/member/searchPw.jsp";
			}
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
