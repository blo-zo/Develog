package semi.blozo.develog.search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.blozo.develog.main.service.MainService;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.search.model.service.SearchService;

@WebServlet("/search/*")
public class SearchServlet extends HttpServlet{

	// 검색 결과 페이지
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String message = null;
		String path = null;
		RequestDispatcher dispatcher = null;
//		String uri =req.getRequestURI();
//		String contextPath = req.getContextPath();
//
//		String command = uri.substring(  (contextPath + "/searcb/").length() );
		
		SearchService service = new SearchService();
		MainService  service2 = new MainService();
		HttpSession session = req.getSession();
		try {
			
			
			String searchInput = req.getParameter("searchInput");	// 검색어
			System.out.println(searchInput);
			if(searchInput != null ) {	// 검색어를 입력한 경우
				
				
				int searchResultCount = service.searchResultCount(searchInput);	// 검색 결과 수
				System.out.println(searchResultCount);
				
				
				if(searchResultCount > 0) { // 검색 결과 수가 있을시 검색화면으로넘어감
					
					List<Post> searchPost = service.searchPost(searchInput); // 검색결과 전체 조회
					
					
				
					
					req.setAttribute("searchInput", searchInput );
					req.setAttribute("searchResultCount", searchResultCount);
					req.setAttribute("searchPost", searchPost);
					System.out.println("검색 결과 전체 조회" + searchPost); // 글상태 일반일 경우만 조회
					path = "/WEB-INF/views/search/search.jsp";
					
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);

				}else if(searchResultCount == 0 ) { //검색 결과 가 없을 시 다시 alert창 메세지
					
					List<Post> allList = service2.allList();
					message = "검색결과가 없습니다";
					path = "/WEB-INF/views/search/search.jsp";
					req.setAttribute("allList" , allList);
					req.setAttribute("searchInput", searchInput );
					session.setAttribute("message", message);
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				
			}else {	// 검색어가 null인 경우
				
				req.setAttribute("searchInput", "입력한 검색어가 없습니다.");
				req.setAttribute("searchResultCount", "0");
				path = "/WEB-INF/views/search/search.jsp";
				
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
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
