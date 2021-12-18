package semi.enquiry.model.dao;
import static semi.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.enquiry.model.vo.Enquiry;
import semi.enquiry.model.vo.Pagination;


public class EnquiryDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	public EnquiryDAO() {
		String filePath = EnquiryDAO.class.getResource("/semi/sql/enquriy-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 목록 수 조회
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int getListCount(Connection conn)throws Exception {
			
		
		int listCount = 0;
			try {
				String sql = prop.getProperty("getListCount");
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt(1);
					
					
				}
				
			}finally {
				close(rs);
				close(pstmt);
				
			}
		return listCount;
	}


	/** 문의 목록조회
	 * @param pagination
	 * @param conn
	 * @return enquiryList
	 * @throws Exception
	 */
	public List<Enquiry> selectEnquiryList(Pagination pagination, int memberNo , Connection conn )throws Exception {
		List<Enquiry> enquiryList = new ArrayList<Enquiry>();
		try {
			String sql = prop.getProperty("selectEnquriyList");
			int startRow =(pagination.getCurrentPage() - 1)* pagination.getLimit() +1;
			int endRow = startRow + pagination.getLimit() -1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, memberNo);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Enquiry enquiry = new Enquiry();
				// 게시글 번호 , 제목 , 작성자명 , 조회수 , 카테고리명 , 작성일 ,게시글 상태명
				
				enquiry.setEnquiryNo(rs.getInt("ENQUIRY_NO"));
				enquiry.setEnquiryTitle(rs.getString("ENQUIRY_TITLE"));
				enquiry.setEnquiryContent(rs.getString("ENQUIRY_CONTENT"));
				enquiry.setCreateDt(rs.getString("CREATE_DT"));
				enquiry.setModifyDt(rs.getString("MODIFY_DT"));
				enquiry.setParentEnquriy(rs.getString("PARENT_ENQUIRY"));
				
				enquiryList.add(enquiry);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		
			
		}
		
		
		return enquiryList;
	}
	
}
