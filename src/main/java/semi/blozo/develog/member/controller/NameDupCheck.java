package semi.blozo.develog.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.blozo.develog.member.model.service.MemberService;



@WebServlet("/member/nameDupCheck")
public class NameDupCheck extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputName = req.getParameter("inputName");
		
		try {
			MemberService service = new MemberService();
			
			int result = service.nameDubCheck(inputName);
			
			
			PrintWriter out = resp.getWriter();
			
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
