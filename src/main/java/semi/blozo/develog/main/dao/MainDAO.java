package semi.blozo.develog.main.dao;

import static semi.blozo.develog.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.blozo.develog.post.model.dao.PostDAO;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostImage;

public class MainDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public MainDAO() {
		
		try {
			prop = new Properties();
			
			String filePath 
			= PostDAO.class.getResource("/semi/blozo/develog/sql/main-query.xml").getPath();     
			// -> SQL이 작성된 XML 파일의 경로
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		}
	
	/** 게시글 전체 조회 DAO
	 * @param conn
	 * @return List
	 * @throws Exception
	 */
	public List<Post> allList(Connection conn) throws Exception{
		
		List<Post> allList = new ArrayList<Post>();
		
		try {
			
			String sql = prop.getProperty("selectPostListAll");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// 값 세팅하기
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
				allList.add(post);
			}
			
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return allList;
	}
	
	/** 캐러셀 조회수 부분
	 * @param conn
	 * @return readList
	 * @throws Exception
	 */
	public List<Post> readList(Connection conn)throws Exception {
		List<Post> readList = new ArrayList<Post>();
		
		try {
			
			String sql = prop.getProperty("selectReadCount");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// 값 세팅하기
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
				readList.add(post);
			}
			
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return readList;
	}

	
	/** 좋아요순
	 * @param conn
	 * @return allPostList
	 * @throws Exception
	 */
	public List<Post> trendList(Connection conn)throws Exception {
	List<Post> trendList = new ArrayList<Post>();
		
		try {
			
			String sql = prop.getProperty("selectTrendList");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// 값 세팅하기
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
				trendList.add(post);
			}
			
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return trendList;
	}
	
	

	
	/** 썸네일 이미지 가져오기
	 * @param postNo
	 * @param conn
	 * @return searchImg
	 * @throws Exception
	 */
	public PostImage searchImg(int postNo, Connection conn)throws Exception {
		
		PostImage searchImg = null;
		
		try {
			
			String sql = prop.getProperty("selectImg");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				searchImg = new PostImage();
				
				searchImg.setPostImgNo(rs.getInt("THUMB_IMG_NO"));
				searchImg.setPostImgPath(rs.getString("THUMB_IMG_PATH"));
				searchImg.setPostImgName(rs.getString("THUMB_IMG_NM"));
				searchImg.setPostImgOriginal(rs.getString("THUMB_IMG_ORIGINAL"));
				searchImg.setPostNo(postNo);
				
			}
			
		}finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return searchImg;
	}

	


	
	
	
	
	
	
}
