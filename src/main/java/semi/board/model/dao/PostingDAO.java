package semi.board.model.dao;

import static semi.common.JDBCTemplate.close;
import static semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.board.model.vo.PostImageVO;
import semi.board.model.vo.PostVO;



public class PostingDAO {
	
	// JDBC 객체 참조 변수
		private Statement stmt;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
		private Properties prop;
		private PostingDAO postingDAO;
		// Properties : key, value가 모두 String인 Map
		
		
		public PostingDAO() { // 기본 생성자
			
			// DAO 객체 생성 시 특정 위치에 있는 XML 파일을 읽어와 prop에 저장
			try {
				prop = new Properties();
				
				String filePath 
				= PostingDAO.class.getResource("/sql/post-query.xml").getPath();     
				// -> SQL이 작성된 XML 파일의 경로
				
				prop.loadFromXML( new FileInputStream( filePath ) );
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}


		/** 다음 게시글 번호 조회
		 * @param conn
		 * @return
		 * @throws Exception
		 */
		public int nextPostNo(Connection conn) throws Exception{
			
			int postNo = 0;
			
			try {
				String sql = prop.getProperty("nextPostNo");
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if( rs.next()) {
					postNo = rs.getInt(1);
				}
				
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return postNo;
		}


		/** 게시글 삽입
		 * @param postVO
		 * @param conn
		 * @return result 
		 * @throws Exception
		 */
		public int insertPost(PostVO postVO, Connection conn)  throws Exception{

			int result = 0;
			
			try {
				String sql = prop.getProperty("insertPost");
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, postVO.getPostNo());
				pstmt.setString(2, postVO.getPostTitle());
				pstmt.setString(3, postVO.getPostContent());
				pstmt.setInt(4, postVO.getCategoryCode());
				pstmt.setInt(5, postVO.getPostStatusCode());
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 게시글 이미지 정보 삽입
		 * @param img
		 * @param conn
		 * @return result
		 * @throws Exception
		 */
		public int insertPostImage(PostImageVO img, Connection conn)  throws Exception{
			int result =0;
			
		      try {
		          String sql = prop.getProperty("insertBoardImage");
		          pstmt = conn.prepareStatement(sql);
		          /* POSTIMGVO에 필드 생성 
		          pstmt.setString(1, img.getImgPath());
		          pstmt.setString(2, img.getImgName());
		          pstmt.setString(3, img.getImgOriginal());
		          pstmt.setInt(4, img.getImgLevel());
		          pstmt.setInt(5, img.getBoardNo());
		          */
		          result = pstmt.executeUpdate();
		          
		       }finally {
		          close(pstmt);
		       }   
		       return result;
		}
	
	
	
}
