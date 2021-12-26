package semi.blozo.develog.search.model.dao;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.blozo.develog.post.model.vo.Post;

public class SearchDAO {
	
		private Statement stmt;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
		private Properties prop;
	
		
		public SearchDAO() { // 기본 생성자
		
			// DAO 객체 생성 시 특정 위치에 있는 XML 파일을 읽어와 prop에 저장
			try {
				prop = new Properties();
				
				String filePath 
				= SearchDAO.class.getResource("/semi/blozo/develog/sql/search-query.xml").getPath();     
				// -> SQL이 작성된 XML 파일의 경로
				
				prop.loadFromXML( new FileInputStream( filePath ) );
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}


		/** 검색 결과 수 DAO
		 * @param searchInput
		 * @param conn
		 * @return result (검색 결과 수)
		 * @throws Exception
		 */
		public int searchResultCount(String searchInput, Connection conn) throws Exception{

			int result = 0;
			
			// 게시글 상태가 정상인 글만 검색해서 보여주기
			// CLOB 데이터 타입은 뭔가 다른가?? 
			try {
				String sql = prop.getProperty("searchResultCount");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchInput);
//				pstmt.setString(2, searchInput);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("COUNT(*)");
				}
				
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return result;
		}


		/** 전체 검색결과 수
		 * @param conn
		 * @param searchInput
		 * @return searchPost
		 */
		public List<Post> searchPost(Connection conn, String searchInput)throws Exception {
			
			
			List<Post> searchPost = new ArrayList<Post>();
			
			try {
				String sql = prop.getProperty("searchPost");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchInput);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Post post = new Post();
					post.setPostNo(rs.getInt("POST_NO"));
					post.setPostTitle(rs.getString("POST_TITLE"));
					post.setPostContent(rs.getString("POST_CONTENT"));
					post.setPostTitle(rs.getString("POST_TITLE"));
					post.setCreateDate(rs.getString("CREATE_DT"));
					post.setModifyDate(rs.getString("MODIFY_DT"));
					post.setReadCount(rs.getInt("READ_COUNT"));
					post.setFavoriteCount(rs.getInt("LIKE_COUNT"));
					post.setBlogNo(rs.getInt("BLOG_NO"));
					post.setBlogTitle(rs.getString("BLOG_TITLE"));
					post.setMemberNo(rs.getInt("MEMBER_NO"));
					post.setMemberName(rs.getString("MEMBER_NM"));
					searchPost.add(post);
					
					
				}
				
				
				
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			
			
			
			return searchPost;
		}


		
		
		
		


}
