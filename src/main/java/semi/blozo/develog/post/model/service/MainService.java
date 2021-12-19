package semi.blozo.develog.post.model.service;

import static semi.blozo.develog.common.JDBCTemplate2.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.post.model.dao.MainDAO;
import semi.blozo.develog.post.model.vo.Post;

public class MainService {
	
	
	MainDAO dao = new MainDAO();
	

	public List<Post> selectPostListAll() throws Exception{

		Connection conn = getConnection();
		
		List<Post> postListAll = dao.selectPostListAll(conn); 
		
		
		close(conn);
		
		return postListAll;
	}

}
