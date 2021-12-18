package semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.member.model.service.MemberService;
import semi.member.model.vo.Member;




@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String memberEmail = req.getParameter("memberEmail");
		String memberPw = req.getParameter("memberPw");
		System.out.println("memberEmail" + memberEmail);
		System.out.println("memberPw" + memberPw);
		try {
			MemberService service = new MemberService();
			Member loginMember = service.login(memberEmail,memberPw);
			HttpSession session = req.getSession();
			if(loginMember != null) {
				if(loginMember.getStatusCd() == 200) {
					session.setAttribute("loginMember", loginMember);
					session.setMaxInactiveInterval(1800);
				
					Cookie cookie = new Cookie("save",memberEmail);
					
					if(req.getParameter("idcheck") != null) {
						cookie.setMaxAge(60*60*24*30);
					}else {
						cookie.setMaxAge(0);
					}
					cookie.setPath(req.getContextPath());
					resp.addCookie(cookie);
				}else {
				session.setAttribute("message", "정지회원입니다");
				}
				
			}else {
				session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");
			}
			resp.sendRedirect(req.getContextPath());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
