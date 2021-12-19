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

@WebServlet("/member/signup")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		 String signUp = req.getContextPath()+"/resources/css/signUp.css";
		 req.setAttribute("signUp", signUp);
		 String path = "/WEB-INF/views/member/signUp.jsp";
		 req.getRequestDispatcher(path).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memberNm = req.getParameter("signUpNm"); 
		String memberEmail = req.getParameter("signUpEmail");
		String memberPw = req.getParameter("signUpPw");
		Member member = new Member(memberPw, memberNm, memberEmail);
		
		try {
			MemberService service = new MemberService();
			int result = service.signUp(member);
			String message = null;
			if(result > 0) {
				message = "회원가입이 완료되었습니다.";
			}
			HttpSession session = req.getSession();
			
			session.setAttribute("message", message);
			
			resp.sendRedirect(req.getContextPath());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
