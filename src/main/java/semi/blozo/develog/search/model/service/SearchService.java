package semi.blozo.develog.search.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.post.model.vo.Post;
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


	/** 전체 검색 결과
	 * @param searchInput
	 * @return searchPost
	 * @throws Exception
	 */
	public List<Post> searchPost(String searchInput)throws Exception {
	
		Connection conn = getConnection();
		List<Post> searchPost = dao.searchPost(conn , searchInput);
		
		
		close(conn);
		return searchPost;
	}




	
	
	
}
