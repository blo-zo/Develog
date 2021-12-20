package semi.blozo.develog.search.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.blozo.develog.search.model.dao.SearchDAO;

public class SearchService {

	
	SearchDAO dao = new SearchDAO(); 
	
	
	/** 검색 결과 수 Service
	 * @param searchInput
	 * @return searchResultCount(검색 결과 수 조회)
	 * @throws Exception
	 */
	public int searchResultCount(String searchInput) throws Exception{

		Connection conn = getConnection();
		int result = dao.searchResultCount(searchInput, conn);
		close(conn);
		return result;
	}

	
	
	
}
