package semi.blozo.develog.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.blozo.develog.admin.model.service.AdminService;
import semi.blozo.develog.admin.model.vo.Enquiry;
import semi.blozo.develog.admin.model.vo.Member;
import semi.blozo.develog.admin.model.vo.Pagination;
import semi.blozo.develog.admin.model.vo.Post;
import semi.blozo.develog.admin.model.vo.Report;

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
				List<Member> memberList = new ArrayList<Member>();
				if(req.getParameter("searchWord") ==  null) {
				
					memberList = service.selectMember(pagination);
				}else {
					String searchWord = req.getParameter("searchWord");
					String searchTag = req.getParameterValues("searchTag")[0];
					
					req.setAttribute("searchWord", searchWord);
					req.setAttribute("searchTag", searchTag);
					memberList = service.selectMemberSearch(searchWord, searchTag, pagination);
					
				}
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
				
				 int cumulativeViews = service.selectPostViews();
				 int cumulativeMembers = service.selectMembers();
				 int cumulativePosts = service.selectPosts();
				 int dailyViews = service.selectDailyViews();
				 int dailyMembers = service.selectDailyMembers();
				 int dailyPosts = service.selectDailyPosts();
				
				 req.setAttribute("cumulativeViews", cumulativeViews);
				 req.setAttribute("cumulativeMembers", cumulativeMembers);
				 req.setAttribute("cumulativePosts", cumulativePosts);
				 req.setAttribute("dailyViews", dailyViews);
				 req.setAttribute("dailyMembers", dailyMembers);
				 req.setAttribute("dailyPosts", dailyPosts);
				 
				 List<Post> listCounts = service.selectListCounts();

				 req.setAttribute("listCounts", listCounts);
				 
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
				
			}else if(command.equals("report/modal")) {
				int reportNo = Integer.parseInt(req.getParameter("reportNo"));
				
				Report report = service.selectDetailReport(reportNo);
				
				new Gson().toJson(report, resp.getWriter());
			}
			
			else if(command.equals("enquiry")) {
				Pagination pagination = service.getPagination(cp);
				
				List<Enquiry> enquiryList = service.selectEnquiry(pagination);
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("enquiryList", enquiryList);
				 path = "/WEB-INF/views/admin/enquiry.jsp";
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}else if(command.equals("enquiry/modal")) {
				System.out.println("모달 확인");
				int enquiryNo = Integer.parseInt(req.getParameter("enquiryNo"));
				
				Enquiry enquiry = service.selectDetailEnquiry(enquiryNo);
				System.out.println(enquiry);
				
				new Gson().toJson(enquiry, resp.getWriter());
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
