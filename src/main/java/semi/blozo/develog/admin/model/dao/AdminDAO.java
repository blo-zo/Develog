package semi.blozo.develog.admin.model.dao;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.blozo.develog.admin.model.vo.Enquiry;
import semi.blozo.develog.admin.model.vo.Member;
import semi.blozo.develog.admin.model.vo.Pagination;
import semi.blozo.develog.admin.model.vo.Post;
import semi.blozo.develog.admin.model.vo.Report;

public class AdminDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	   public AdminDAO() {
		      String filePath = AdminDAO.class.getResource("/semi/blozo/develog/sql/admin.xml").getPath();                    
		      
		      try {
		         prop = new Properties();
		         prop.loadFromXML(new FileInputStream(filePath));
		         
		      }catch (Exception e) {
		         e.printStackTrace();
		      }
		   }

	public List<Member> selectMember(Pagination pagination, Connection conn) throws Exception {
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectMember");
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
//				MEMBER_NM, MEMBER_EMAIL, ENROLL_DT, INTRO, VIOLATION_COUNT, MODIFY_DT, STATUS_CD,GRADE_CD
				member.setMemberNo(rs.getInt(2));
				member.setMemberName(rs.getString(3));
				member.setMemberEmail(rs.getString(4));
				member.setEnrollDate(rs.getString(5));
				member.setIntro(rs.getString(6));
				member.setViolationCount(rs.getInt(7));
				member.setModifyDate(rs.getString(8));
				member.setStatusName(rs.getString(9));
				member.setGradeName(rs.getString(10));
				
				memberList.add(member);
			}
			
		}finally {
			pstmt.close();
			rs.close();
		}
		
		return memberList;
	}

	public int memberListCount(Connection conn) throws Exception{
		int memberListCount = 0;
		try {
			String sql = prop.getProperty("memberListCount");
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberListCount = rs.getInt(1);
			}
		}finally {
			pstmt.close();
			rs.close();
		}
		return memberListCount;
	}

	public List<Post> selectPost(Pagination pagination, Connection conn) throws Exception {
		List<Post> postList = new ArrayList<Post>();
		
		try {
			String sql = prop.getProperty("selectPost");
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt(2));
				post.setPostTitle(rs.getString(3));
				post.setPostContent(rs.getString(4));
				post.setCreateDate(rs.getString(5));
				post.setReadCount(rs.getInt(6));
				post.setViolationCount(rs.getInt(7));
				post.setLikeCount(rs.getInt(8));
				post.setModifyDate(rs.getString(9));
				post.setBlogNo(rs.getInt(10));
				post.setCategoryCode(rs.getInt(11));
				post.setPostStatusName(rs.getString(12));
				post.setReportCount(rs.getInt(13));
				postList.add(post);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return postList;
	
	}

	public List<Report> selectReport(Pagination pagination, Connection conn) throws Exception{
		List<Report> listReport = new ArrayList<Report>();
		
		try {
			String sql = prop.getProperty("selectReport");
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Report report = new Report();
				
				report.setReportNo(rs.getInt(2));
				report.setReportContent(rs.getString(3));
				report.setReportType(rs.getString(4));
				report.setCreateDate(rs.getString(5));
				report.setTargetNo(rs.getInt(6));
				report.setMemberNo(rs.getInt(7));
				report.setReportStatusName(rs.getString(8));

				listReport.add(report);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return listReport;
	}

	public List<Enquiry> selectEnquiry(Pagination pagination, Connection conn) throws Exception {
		List<Enquiry> enquiryList = new ArrayList<Enquiry>();
		// 커밋을 꼭 확인하자 
		try {
			String sql = prop.getProperty("selectEnquiry");
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Enquiry enq = new Enquiry();
				
				enq.setEnquiryNo(rs.getInt(2));
				enq.setEnquiryTitle(rs.getString(3));
				enq.setEnquiryContent(rs.getString(4));
				enq.setCreateDate(rs.getString(5));
				enq.setModifyDate(rs.getString(6));
				enq.setMemberNo(rs.getInt(7));
				enq.setParentEnquiry(rs.getInt(8));
				
				enquiryList.add(enq);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return enquiryList;
	
	}

	public int selecPostViews(Connection conn) throws Exception {
		int cumulativeViews = 0;
		try {
			String sql = prop.getProperty("selectPostViews");
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				cumulativeViews = rs.getInt(1);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return cumulativeViews;
	}

	public int selectMembers(Connection conn) throws Exception {
		int cumulativeMembers = 0;
		try {
			String sql = prop.getProperty("selectMembers");
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				cumulativeMembers = rs.getInt(1);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return cumulativeMembers;
	}

	public int selectPosts(Connection conn) throws Exception {
		int cumulativePosts = 0;
		try {
			String sql = prop.getProperty("selectPosts");
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				cumulativePosts = rs.getInt(1);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return cumulativePosts;
		
		
	}

	public int selectDailyViews(Connection conn) throws Exception {
		int dailyViews = 0;
		try {
			String sql = prop.getProperty("selectDailyViews");
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				dailyViews = rs.getInt(1);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return dailyViews;
	
	}

	public int selectDailyMembers(Connection conn) throws Exception  {
		int dailyMembers = 0;
		try {
			String sql = prop.getProperty("selectDailyMembers");
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				dailyMembers = rs.getInt(1);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return dailyMembers;
	}

	public int selectDailyPosts(Connection conn) throws Exception {
		int dailyPosts = 0;
		try {
			String sql = prop.getProperty("selectDailyPosts");
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				dailyPosts = rs.getInt(1);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return dailyPosts;
	}

	public Report selectDetailReport(int reportNo, Connection conn) throws Exception {
		Report report = new Report();
		try {
			String sql = prop.getProperty("selectDetailReport");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportNo);
			pstmt.setInt(2, reportNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				report.setReportNo(rs.getInt(1));
				report.setReportContent(rs.getString(2));
				report.setReportType(rs.getString(3));
				report.setCreateDate(rs.getString(4));
				report.setTargetNo(rs.getInt(5));
				report.setMemberNo(rs.getInt(6));
				report.setReportStatusName(rs.getString(7));
			}
		}finally {
			close(rs);
			pstmt.close();
		}
		
		return report;
	}

	public Enquiry selectDetailEnquiry(int enquiryNo, Connection conn) throws Exception {
		Enquiry enquiry = new Enquiry();
		try {
			String sql = prop.getProperty("selectDetailEnquiry");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, enquiryNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				enquiry.setEnquiryNo(rs.getInt(1));
				enquiry.setEnquiryTitle(rs.getString(2));
				enquiry.setEnquiryContent(rs.getString(3));
				enquiry.setCreateDate(rs.getString(4));
				enquiry.setModifyDate(rs.getString(5));
				enquiry.setMemberNo(rs.getInt(6));
				enquiry.setParentEnquiry(rs.getInt(7));
			}
		}finally {
			close(rs);
			pstmt.close();
		}
		
		return enquiry;
	}

	public List<Post> selectListCounts(Connection conn) throws Exception {
		List<Post> listCounts = new ArrayList<Post>();
		
		try {
			String sql = prop.getProperty("selectListCounts");
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Post post = new Post();
				post.setReadCountDate(rs.getString(1));
				post.setReadCount(rs.getInt(2));
				
				listCounts.add(post);
			}
		}finally {
			// 역순으로 닫는거 잊지 말고
			rs.close();
			pstmt.close();
		}
		
		return listCounts;
	
	}

	public List<Member> selectMemberSearch(String searchWord, String searchTag, Pagination pagination,
			Connection conn) throws Exception {
		List<Member> memberList = new ArrayList<Member>();
		try {
			String sql2 = null;
			switch(searchTag) {
			case "no": sql2 = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			}

			String sql1 = "SELECT * FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "		(SELECT MEMBER_NO, MEMBER_NM, MEMBER_EMAIL, \r\n"
					+ "			TO_CHAR(ENROLL_DT, 'YYYY-MM-DD') ENROLL_DT, INTRO, VIOLATION_COUNT,\r\n"
					+ "			TO_CHAR(MODIFY_DT, 'YYYY-MM-DD') MODIFY_DT, STATUS_NM,GRADE_NM\r\n"
					+ "		FROM MEMBER\r\n"
					+ "		JOIN MEMBER_STATUS USING(STATUS_CD)\r\n"
					+ "		JOIN GRADE USING(GRADE_CD)\r\n"
					+ sql2 +"\r\n"
					+ "		ORDER BY MEMBER_NO DESC) A)\r\n"
					+ "		WHERE RNUM BETWEEN ? AND ?";
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
//			System.out.println(searchWord);
//			System.out.println(searchTag);
			System.out.println(sql1);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberName(rs.getString("MEMBER_NM"));
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setEnrollDate(rs.getString("ENROLL_DT"));
				member.setIntro(rs.getString("INTRO"));
				member.setViolationCount(rs.getInt("VIOLATION_COUNT"));
				member.setModifyDate(rs.getString("MODIFY_DT"));
				member.setStatusName(rs.getString("STATUS_NM"));
				member.setGradeName(rs.getString("GRADE_NM"));
				
				memberList.add(member);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return memberList;
	}

	public int updateViolationPlus(int[] memberNo, Connection conn) throws Exception {
		int result = 0;
		try {
//			String sql = prop.getProperty("updateViolation");
			String str = "";
			for(int i = 0; i < memberNo.length; i++) {
				if(i != memberNo.length-1) {
					str += "MEMBER_NO = "+memberNo[i]+" OR ";
				}else {
					str += "MEMBER_NO = "+memberNo[i];					
				}
			}
			String temp = "UPDATE MEMBER SET VIOLATION_COUNT = VIOLATION_COUNT + 1 WHERE ";
			String sql = temp + str;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}

	public int updateViolationMinus(int[] memberNo, Connection conn) throws Exception {
		int result = 0;
		try {
//			String sql = prop.getProperty("updateViolation");
			String str = "";
			for(int i = 0; i < memberNo.length; i++) {
				if(i != memberNo.length-1) {
					str += "MEMBER_NO = "+memberNo[i]+" OR ";
				}else {
					str += "MEMBER_NO = "+memberNo[i];					
				}
			}
			String temp = "UPDATE MEMBER SET VIOLATION_COUNT = VIOLATION_COUNT - 1 WHERE ";
			String sql = temp + str;
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}
}
