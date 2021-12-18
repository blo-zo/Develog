package edu.kh.semi.post.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.post.model.dao.MainDAO;
import edu.kh.semi.post.model.vo.Post;

public class MainService {
	
	
	MainDAO dao = new MainDAO();
	

	public List<Post> selectPostListAll() throws Exception{

		Connection conn = getConnection();
		
		List<Post> postListAll = dao.selectPostListAll(conn); 
		
		
		close(conn);
		
		return postListAll;
	}

}
