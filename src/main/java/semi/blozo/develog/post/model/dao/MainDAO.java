package semi.blozo.develog.post.model.dao;

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

public class MainDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
		private Properties prop;

		
		public MainDAO() { // 기본 생성자
		
			// DAO 객체 생성 시 특정 위치에 있는 XML 파일을 읽어와 prop에 저장
			try {
				prop = new Properties();
				
				String filePath 
				= MainDAO.class.getResource("/semi/blozo/develog/sql/post-query.xml").getPath();     
				// -> SQL이 작성된 XML 파일의 경로
				
				prop.loadFromXML( new FileInputStream( filePath ) );
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}


		/** 전체 게시글 리스트
		 * @param conn
		 * @return postListAll
		 * @throws Exception
		 */
		public List<Post> selectPostListAll(Connection conn) throws Exception{
			
			List<Post> postListAll = new ArrayList<Post>();
			
			try {
				
				String sql = prop.getProperty("selectPostListAll");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					
					Post post = new Post();
					
					post.setPostNo(rs.getInt(1));
					post.setPostTitle(rs.getString(2));
					post.setPostContent(rs.getString(3));
					post.setCreateDate(rs.getString(4));
					post.setModifyDate(rs.getString(5));
					post.setReadCount(rs.getInt(6));
					post.setFavoriteCount(rs.getInt(7));
					post.setBlogNo(rs.getInt(8));
					post.setBlogTitle(rs.getString(9));
					post.setMemberNo(rs.getInt(10));
					
					postListAll.add(post);
					
				}
				
			}finally {
				close(stmt);
			}
			
			return postListAll;
		}
	
		
		
		
	
}
