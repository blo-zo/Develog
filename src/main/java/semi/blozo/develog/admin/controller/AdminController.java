package semi.blozo.develog.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("controller : " + command);
		try{
			int cp = req.getParameter("cp") == null ? 1: Integer.parseInt(req.getParameter("cp"));
			
			// 로그인이 안되는 문제를 알았다. 
			// 올바른 비밀번호를 입력하면 관리자 세션이 생성되고 필터에 잘 걸러져 정상 작동한거다.
			// 틀린 비밀번호를 입력하면 관리자 세션이 생성이 되지 않고 다른 페이지로 이동하게 되는데 그렇게 되면 필터에 의해서 다시 돌아온다.
			// 그러니 문제가 생긴 것
			
			if(command.equals("login")) {
				if(session.getAttribute("admin") == null) {
					path = "/WEB-INF/views/admin/login.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);					
				}else {
					resp.sendRedirect(req.getContextPath()+ "/admin/member");
				}
			}else if(command.equals("login/try")) {
				
					
					String adminPw = req.getParameter("adminPw");
					Member admin = service.adminLogin(adminPw);
					session.setAttribute("admin", admin);
				if(admin !=null) {
					resp.sendRedirect(req.getContextPath()+ "/admin/member");
				
				}else {
					message = "비밀번호가 틀렸습니다.";
					session.setAttribute("message", message);
					resp.sendRedirect(req.getContextPath()+ "/admin/login");
				}
				
			}else if(command.equals("logout")) {
				session.removeAttribute("admin");
				message = "관리자 로그아웃";
				session.setAttribute("message", message);
				resp.sendRedirect(req.getContextPath()+"/admin/login");
			
			}else if(command.equals("member")) {
				Pagination pagination = service.getPagination(cp);
				List<Member> memberList = new ArrayList<Member>();
				if(req.getParameter("searchWord") == null || req.getParameter("searchWord").equals(""))  {
					memberList = service.selectMember(pagination);
				}else {
					// 페이지를 넘기니까 무조건 searchTag는 넘어와 지내 그러니 그조건도 써야 일로 안넘어오지
					String searchWord = req.getParameter("searchWord");
					String searchTag = req.getParameterValues("searchTag")[0];
					
					req.setAttribute("searchWord", searchWord);
					req.setAttribute("searchTag", searchTag);
					
					pagination = service.getSearchPagination(searchWord, searchTag, cp);
					memberList = service.selectMemberSearch(searchWord, searchTag, pagination);
					
				}
				
//				if(req.getParameterValues("check") != null) {
//					String[] checked = req.getParameterValues("check");
//				}
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("memberList", memberList);
				path = "/WEB-INF/views/admin/member.jsp";
				req.getRequestDispatcher(path).forward(req, resp);

			}else if(command.equals("member/warningPlus")) {
				String[] arr = req.getParameterValues("memberNo"); // 언제 부터 values였지? 자동으로 되어있내
				int[] memberNo = new int[arr.length-1];
				System.out.println(arr);
				for(int i=0; i<arr.length-1; i++) {
						memberNo[i] = Integer.parseInt(arr[i]);
				}
				
				String content = arr[arr.length-1];
				int result = 0;
				String str ="";
				
				for(int i=0; i < memberNo.length; i++) {
					if(!content.equals("")) {
						result = service.insertViolationPlus(memberNo[i], content);						
					}
						if(result != 1) {
							str += memberNo[i]+" ";
						}else {
						}
				}
				
				if(result>0) {
					message = "경고 기능이 수행되었습니다.";
					int updateStatus = service.updateViolationPlus();
				}else {
					
					message = "경고 기능이 수행되지 않았습니다.\r\n"
							+ "경고 실패 회원 번호" + str;
				}
					
				resp.getWriter().print(message);
				
				
			}else if(command.equals("member/warningMinus")) {
				int violationNo = Integer.parseInt(req.getParameter("violationNo"));
				
				int result = service.deleteViolation(violationNo);
				int updateStatus = service.updateViolationMinus();
				System.out.println(updateStatus);
			}
			
			else if(command.equals("post")) {
				Pagination pagination = service.getPagination(cp);
				
				List<Post> postList = service.selectPost(pagination);
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("postList", postList);
				 path = "/WEB-INF/views/admin/post.jsp";
				 req.getRequestDispatcher(path).forward(req, resp);
				
			}else if(command.equals("post/remove")) {
				String[] arr = req.getParameterValues("postNo"); 
				int[] postNo = new int[arr.length-1];
				for(int i=0; i<arr.length-1; i++) {
						postNo[i] = Integer.parseInt(arr[i]);
				}
				String content = arr[arr.length-1];
				int result = 0;
				String str ="";
				for(int i=0; i < postNo.length; i++) {
					if(!content.equals("")) {
							result = service.insertDeletePost(postNo[i], content);													
					}
						if(result != 1) {
							str += postNo[i]+" ";
						}else {
						}
				}
				if(result>0) {
					message = "삭제 되었습니다.";
				}else {
					message = "삭제를 실패하였습니다.\r\n"
							+ "삭제 실패 게시글 번호" + str;
				}
					
				resp.getWriter().print(message);
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
				int enquiryNo = Integer.parseInt(req.getParameter("enquiryNo"));
				
				Enquiry enquiry = service.selectDetailEnquiry(enquiryNo);
				
				new Gson().toJson(enquiry, resp.getWriter());
			}else if(command.equals("violation")) {
				
				int memberNo = Integer.parseInt(req.getParameter("memberNo"));
				
				// vo report 이용
				List<Report> violation = service.selectViolation(memberNo);
				
				new Gson().toJson(violation, resp.getWriter());
				
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
