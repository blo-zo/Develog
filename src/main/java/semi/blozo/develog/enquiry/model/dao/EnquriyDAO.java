package semi.blozo.develog.enquiry.model.dao;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.blozo.develog.enquiry.model.vo.Enquiry;
import semi.blozo.develog.enquiry.model.vo.Pagination;

public class EnquriyDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	public EnquriyDAO() {
		String filePath = EnquriyDAO.class.getResource("/semi/blozo/develog/sql/enquriy-query.xml").getPath();                    
		
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
	public int getListCount(Connection conn , int memberNo)throws Exception {
			
		
		int listCount = 0;
			try {
				String sql = prop.getProperty("getListCount");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memberNo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt(1);
					
					
				}
				//System.out.println(listCount);
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
			String sql = prop.getProperty("selectEnquiryList");
			int startRow =(pagination.getCurrentPage() - 1)* pagination.getLimit() +1;
			int endRow = startRow + pagination.getLimit() -1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				Enquiry enquiry = new Enquiry();
				// 게시글 번호 , 제목 , 작성자명 , 조회수 , 카테고리명 , 작성일 ,게시글 상태명
				
				enquiry.setEnquiryNo(rs.getInt("ENQUIRY_NO"));
				enquiry.setEnquiryTitle(rs.getString("ENQUIRY_TITLE"));
				enquiry.setEnquiryContent(rs.getString("ENQUIRY_CONTENT"));
				enquiry.setCreateDt(rs.getString("CREATE_DT"));
				enquiry.setParentEnquriy(rs.getInt("PARENT_ENQUIRY"));
				
				enquiryList.add(enquiry);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		
			
		}
		
		
		return enquiryList;
	}


	/** 목록 상세조회
	 * @param enquiryNo
	 * @param conn
	 * @return enquiry
	 * @throws Exception
	 */
	public Enquiry selectEnquiry(int enquiryNo, Connection conn)throws Exception {
		Enquiry enquiry = new Enquiry();
		try {
			String sql = prop.getProperty("selectEnquiry");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, enquiryNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				enquiry.setEnquiryNo(enquiryNo);
				enquiry.setEnquiryTitle(rs.getString("ENQUIRY_TITLE"));
				enquiry.setEnquiryContent(rs.getString("ENQUIRY_CONTENT"));
				enquiry.setCreateDt(rs.getString("CREATE_DT"));

				enquiry.setParentEnquriy(rs.getInt("PARENT_ENQUIRY"));
				
			
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
			
		}
		
		
		
		return enquiry;
	}


	public int insertEnquiry(String enquiryTitle, String enquiryContent, int memberNo,Connection conn)throws Exception{
		
		int result = 0;
		// 변수 초기화
		
		try {
			String sql = prop.getProperty("insertEnquiry");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, enquiryTitle);
			pstmt.setString(2, enquiryContent);
			pstmt.setInt(3, memberNo);
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
}
