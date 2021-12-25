package semi.blozo.develog.main.service;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;

import java.util.List;


import semi.blozo.develog.main.dao.MainDAO;

import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostImage;


public class MainService {

	 MainDAO dao = new MainDAO();
	
	 
	 /** 전체 게시글 조회
	 * @return
	 * @throws Exception
	 */
	public List<Post> selectPostListAll() throws Exception{
		 
		// 최신, 트렌딩 매개변수 넣어서 위치홀더로 지정할 수 있도록
		
		Connection conn = getConnection();
				
		List<Post> allPostList = dao.selectPostListAll(conn);
			
			if(allPostList != null) {
				for(Post post : allPostList) {
					
					PostImage searchImg = dao.searchImg(post.getPostNo(), conn);
					post.setPostImg(searchImg);
					
				}
			}
		close(conn);
				
		return allPostList;
	}
	
	
	
}
