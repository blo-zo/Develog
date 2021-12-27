package semi.blozo.develog.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.blozo.develog.member.model.service.MemberProfileService;

@WebServlet("/member/secession")
public class SecessionServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberProfileService service = new MemberProfileService();
		HttpSession session = req.getSession();
		try {
			
			int memberNo = Integer.parseInt(req.getParameter("memberNo"));
			
			int result = service.updateSecession(memberNo);
			
			String message = null;
			if(result > 0) {
				message = "회원탈퇴를 성공했습니다.";
				session.removeAttribute("loginMember");
				
			}else {
				message = "회원탈퇴 중 문제가 발생했습니다.";
			}
			resp.getWriter().print(message);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}