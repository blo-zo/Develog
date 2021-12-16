package semi.develog.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.develog.admin.model.service.AdminService;
import semi.develog.admin.model.vo.Member;
import semi.develog.admin.model.vo.Pagination;
import semi.develog.admin.model.vo.Post;
import semi.develog.admin.model.vo.Report;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring( (contextPath + "/admin/").length());
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		AdminService service = new AdminService();
		try{
			int cp = req.getParameter("cp") == null ? 1: Integer.parseInt(req.getParameter("cp"));
			if(command.equals("login")) {
				 path = "/WEB-INF/views/admin/login.jsp";
//				 dispatcher = req.getRequestDispatcher(path); // 여기에 .forward(req, resp)하면 왜 안되지?
//				 dispatcher.forward(req, resp);
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}
			
			else if(command.equals("member")) {
				
				Pagination pagination = service.getPagination(cp);
				
				List<Member> memberList = service.selectMember(pagination);
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("memberList", memberList);
				path = "/WEB-INF/views/admin/member.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
//				 dispatcher = req.getRequestDispatcher(path); 
//				 dispatcher.forward(req, resp);

			}
			
			else if(command.equals("post")) {
				Pagination pagination = service.getPagination(cp);
				
				List<Post> postList = service.selectPost(pagination);
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("postList", postList);
				 path = "/WEB-INF/views/admin/post.jsp";
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}
			
			else if(command.equals("statistics")) {
				 path = "/WEB-INF/views/admin/statistics.jsp";
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}
			
			else if(command.equals("report")) {
				
				Pagination pagination = service.getPagination(cp);
				
				List<Report> reportList = service.selectReport(pagination);
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("reportList", reportList);
				 path = "/WEB-INF/views/admin/report.jsp";
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}
			
			else if(command.equals("enquiry")) {
				 path = "/WEB-INF/views/admin/enquiry.jsp";
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
