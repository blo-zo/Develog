package semi.blozo.develog.main.service;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import semi.blozo.develog.main.dao.MainDAO;

import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostImage;


public class MainService {

	 MainDAO dao = new MainDAO();
	
	 

		/** 메인페이지 초기화면 list
		 * @return allList
		 * @throws Exception
		 */
		public List<Post> allList()throws Exception {
			// 처음 메인페이지 요청 시 나타나는 최신값
			
			Connection conn = getConnection();
			List<Post> allList = dao.allList(conn);
			if(allList != null) {
				for(Post post : allList) {
					
					PostImage searchImg = dao.searchImg(post.getPostNo(), conn);
					post.setPostImg(searchImg);
					
				}
			}
			
			close(conn);
			return allList;
		}

	
		/**캐러셀 부분
		 * @return readList 조회순
		 * @throws Exception
		 */
		public List<Post> readList()throws Exception { // 조회순 캐러셀 부분
			
			Connection conn = getConnection();
			
			List<Post> readList = dao.readList(conn);
			if(readList != null) {
				for(Post post : readList) {
					
					PostImage searchImg = dao.searchImg(post.getPostNo(), conn);
					post.setPostImg(searchImg);
					
				}
			}
			
			close(conn);
			return readList;
		}

	 
	 
	 /** 전체 게시글 조회type 값 제출시
	 * @return
	 * @throws Exception
	 */
	public List<Post> trendList()throws Exception{
		 
		// 최신, 트렌딩 매개변수 넣어서 위치홀더로 지정할 수 있도록

				Connection conn = getConnection();
	
				List<Post>	trendList = dao.trendList(conn);
					
					if(trendList != null) { // 썸네일 이미지 얻어오기
						for(Post post : trendList) {
							
							PostImage searchImg = dao.searchImg(post.getPostNo(), conn);
							post.setPostImg(searchImg);
							
						}
					}
					
		
				
		close(conn);
				
		return trendList;
	}





	



	


	










	
	
}
