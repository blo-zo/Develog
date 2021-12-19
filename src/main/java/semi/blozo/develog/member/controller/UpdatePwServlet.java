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



@WebServlet("/member/updatepw")
public class UpdatePwServlet extends HttpServlet{

	// GET 방식 요청 시 비밀번호 변경 JSP로 요청 위임
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updatePw = req.getContextPath()+"/resources/css/updatePw.css";
		req.setAttribute("updatePw", updatePw);
		req.getRequestDispatcher("/WEB-INF/views/member/updatePw.jsp").forward(req, resp);
			
	}
	// POST 방식 요청 시 DB 비밀번호 변경
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPw = req.getParameter("currentPw");
		String newPw = req.getParameter("newPw");

		System.out.println("currentPw" + currentPw);
		System.out.println("newPw" + newPw);
		
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		String message = null;
		String path = null;
		int memberNo = loginMember.getMemberNo();
		System.out.println(memberNo);
		try {
			int result = new MemberService().updatePw(currentPw, newPw, memberNo);
		
			System.out.println(result);
			if(result>0) {
				
				message = "비밀번호가 변경되었습니다";
				path = "updatepw";
			}else {
				message = "현재 비밀번호가 일치하지 않습니다.";
				path = "updatepw";
			}
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
