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
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionIdGenerator;

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
		HttpSession session = req.getSession();
		AdminService service = new AdminService();
		System.out.println(command);
		try{
			int cp = req.getParameter("cp") == null ? 1: Integer.parseInt(req.getParameter("cp"));
			
			// 로그인이 안되는 문제를 알았다. 
			// 올바른 비밀번호를 입력하면 관리자 세션이 생성되고 필터에 잘 걸러져 정상 작동한거다.
			// 틀린 비밀번호를 입력하면 관리자 세션이 생성이 되지 않고 다른 페이지로 이동하게 되는데 그렇게 되면 필터에 의해서 다시 돌아온다.
			// 그러니 문제가 생긴 것
			
			if(command.equals("login")) {
				path = "/WEB-INF/views/admin/login.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
			}else if(command.equals("test")){
				System.out.println("연결 확인");
				String a = req.getParameter("adminId");
				String b = req.getParameter("adminPw");
				System.out.println(a);
				System.out.println(b);
				path = "/WEB-INF/views/admin/test.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
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
				
				if(req.getParameterValues("check") != null) {
					String[] checked = req.getParameterValues("check");
					System.out.println(checked);
					System.out.println(checked[0]);
					System.out.println(checked[1]);
					System.out.println(checked[2]);
					System.out.println(checked[3]);
				}
				System.out.println(pagination);
				System.out.println(memberList);
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("memberList", memberList);
				path = "/WEB-INF/views/admin/member.jsp";
				req.getRequestDispatcher(path).forward(req, resp);

			}else if(command.equals("member/warningPlus")) {
				String[] temp = req.getParameterValues("memberNo"); // 언제 부터 values였지? 자동으로 되어있내
				int[] memberNo = new int[temp.length];
				System.out.println(temp.length);
				for(int i=0; i < temp.length; i++) {
					memberNo[i] = Integer.parseInt(temp[i]);
				}
				int result = service.updateViolationPlus(memberNo);
				System.out.println(result);
				
				
			}else if(command.equals("member/warningMinus")) {
				System.out.println("연결 확인");
				String[] temp = req.getParameterValues("memberNo"); // 언제 부터 values였지? 자동으로 되어있내
				int[] memberNo = new int[temp.length];
				System.out.println(temp.length);
				for(int i=0; i < temp.length; i++) {
					memberNo[i] = Integer.parseInt(temp[i]);
				}
				int result = service.updateViolationMinus(memberNo);
				System.out.println(result);
				
				
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
