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
import semi.blozo.develog.post.model.vo.PostPagination;

public class PostDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
	private Properties prop;

	
	public PostDAO() { // 기본 생성자
	
		// DAO 객체 생성 시 특정 위치에 있는 XML 파일을 읽어와 prop에 저장
		try {
			prop = new Properties();
			
			String filePath 
			= PostDAO.class.getResource("/semi/blozo/develog/sql/post-query.xml").getPath();     
			// -> SQL이 작성된 XML 파일의 경로
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}


	/** 특정 블로그의 전체 게시물 수 조회
	 * @param conn
	 * @param blogTitle
	 * @return
	 * @throws Exception
	 */
	public int getBlogListCount(Connection conn, String blogTitle) throws Exception{

		int blogListCount = 0;
		try {
			String sql = prop.getProperty("getBlogListCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blogTitle);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				blogListCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return blogListCount;
	}


	/** 블로그 게시글 목록 조회 DAO
	 * @param blogPostPagination
	 * @param blogTitle 
	 * @param conn
	 * @return postList
	 * @throws Exception
	 */
	public List<Post> selectBlogPostList(PostPagination blogPostPagination, String blogTitle, Connection conn) throws Exception{
		
		List<Post> postList = new ArrayList<Post>();
		
		try {
			
			String sql = prop.getProperty("selectBlogPostList");
			int startRow =(blogPostPagination.getCurrentPage() - 1)* blogPostPagination.getLimit() +1;
			int endRow = startRow + blogPostPagination.getLimit() -1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blogTitle);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Post post = new Post();
				
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(rs.getString("POST_CONTENT"));
				post.setCreateDate(rs.getString("CREATE_DT"));
				post.setReadCount(rs.getInt("READ_COUNT"));
				post.setReportCount(rs.getInt("REPORT_COUNT"));
				post.setFavoriteCount(rs.getInt("LIKE_COUNT"));
				post.setBlogNo(rs.getInt("BLOG_NO"));
				post.setBlogTitle(rs.getString("BLOG_TITLE"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
//				post.setMemberName();
				post.setCategoryCode(rs.getInt("CATEGORY_CD"));
				post.setCategoryName(rs.getString("CATEGORY_NM"));
				post.setPostStatusCode(rs.getInt("POST_STATUS_CD"));
				post.setPostStatusName(rs.getString("POST_STATUS_NM"));
				
				postList.add(post);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return postList;
	}


	/** 포스트 상세 조회
	 * @param postNo
	 * @param conn
	 * @return post
	 * @throws Exception
	 */
	public Post selectPost(int postNo, Connection conn) throws Exception{

		Post post = null;
		
		try {
			
			String sql = prop.getProperty("selectPost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				post = new Post();
				
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(rs.getString("POST_CONTENT"));
				post.setCreateDate(rs.getString("CREATE_DT"));
				post.setModifyDate(rs.getString("MODIFY_DT"));
				post.setReadCount(rs.getInt("READ_COUNT"));
				post.setReportCount(rs.getInt("REPORT_COUNT"));
				post.setFavoriteCount(rs.getInt("LIKE_COUNT"));
				post.setBlogNo(rs.getInt("BLOG_NO"));
				post.setBlogTitle(rs.getString("BLOG_TITLE"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
//				post.setMemberName();
				post.setCategoryCode(rs.getInt("CATEGORY_CD"));
				post.setCategoryName(rs.getString("CATEGORY_NM"));
				post.setPostStatusCode(rs.getInt("POST_STATUS_CD"));
				post.setPostStatusName(rs.getString("POST_STATUS_NM"));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return post;
	}


	/** 조회수 증가
	 * @param postNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int plusReadCount(int postNo, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("plusReadCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	

	
	
	
	
}
