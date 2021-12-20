package semi.blozo.develog.search.model.dao;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

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
				= SearchDAO.class.getResource("/edu/kh/semi/sql/search-query.xml").getPath();     
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


		
		
		
		


}
