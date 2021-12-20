package semi.blozo.develog.search.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.blozo.develog.search.model.service.SearchService;

@WebServlet("/search/*")
public class SearchServlet extends HttpServlet{

	// 검색 결과 페이지
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = null;
		RequestDispatcher dispatcher = null;
		
		
		SearchService service = new SearchService();
		

		try {
			
			
			String searchInput = req.getParameter("search-input");	// 검색어
			
			if(searchInput != null) {	// 검색어를 입력한 경우
				
				int searchResultCount = service.searchResultCount(searchInput);	// 검색 결과 수
				req.setAttribute("searchInput", '"' + searchInput + '"' + "의 검색 결과");
				req.setAttribute("searchResultCount", searchResultCount);
				System.out.println(searchInput);
				
			}else {	// 검색어가 null인 경우
				
				req.setAttribute("searchInput", "입력한 검색어가 없습니다.");
				req.setAttribute("searchResultCount", "null");
			}
			
			
			path = "/WEB-INF/views/search/search.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);

			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}

		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req,resp);
	
	}
	
}
