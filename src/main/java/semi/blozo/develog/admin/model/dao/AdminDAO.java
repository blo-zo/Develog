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
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(rs.getString("POST_CONTENT"));
				post.setCreateDate(rs.getString("ENROLL_DT"));
				post.setReadCount(rs.getInt("READ_COUNT"));
				post.setViolationCount(rs.getInt("VIOLATION_COUNT"));
				// READCONTET가 길어지면 SUBsTRING으로 줄이고 ...을 붙이자
				post.setLikeCount(rs.getInt("LIKE_COUNT"));
				post.setModifyDate(rs.getString("MODIFY_DT"));
				post.setBlogNo(rs.getInt("BLOG_NO"));
				post.setCategoryCode(rs.getInt("CATEGORY_CD"));
				post.setPostStatusName(rs.getString("POST_STATUS_NM"));
				post.setReportCount(rs.getInt("REPORT_COUNT"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
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
	private static String stringToDate(String searchWord) {
		// 211231 ==> 21/12/31
		StringBuffer input1 = new StringBuffer();
		
		input1.append(searchWord);
		input1.insert(2, "/");
		input1.insert(5, "/");
		return input1.toString();
	}
	public List<Member> selectMemberSearch(String searchWord, String searchTag, Pagination pagination,
			Connection conn) throws Exception {
		List<Member> memberList = new ArrayList<Member>();
		try {
			String sql2 = null;
			switch(searchTag) {
			case "no": sql2 = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "email": sql2 = "WHERE MEMBER_EMAIL LIKE '%"+searchWord +"%'";break;
			case "name" : sql2 = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "enrollDate" : if(searchWord.length() == 15) {
									String str1 = searchWord.substring(0,6);
									String str2 = searchWord.substring(9,15);
									str1 = stringToDate(str1);
									str2 = stringToDate(str2);
									
									sql2 = "WHERE TO_DATE(ENROLL_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;

								}else if(searchWord.length() == 6) {
									String str = searchWord.substring(0, 6);
									sql2 = "WHERE TO_DATE(ENROLL_DT) = '"+ str +"'"; break;
									
								}
//			case "report" : sql2 = "WHERE MEMBER_ LIKE '%"+searchWord +"%'"; break;
			case "violation" : sql2 = "WHERE VIOLATION_COUNT = "+searchWord; break;
			case "status" : sql2 = "WHERE SATATUS_NM LIKE '%"+searchWord +"%'"; break;
			}

			String sql1 = "SELECT * FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "		(SELECT MEMBER_NO, MEMBER_NM, MEMBER_EMAIL, \r\n"
					+ "			TO_CHAR(ENROLL_DT, 'YYYY-MM-DD') ENROLL_DT, INTRO, (SELECT COUNT(*)\r\n"
					+ "                                                      FROM VIOLATION A\r\n"
					+ "                                                      WHERE A.MEMBER_NO = B.MEMBER_NO) VIOLATION_COUNT,\r\n"
					+ "			TO_CHAR(MODIFY_DT, 'YYYY-MM-DD') MODIFY_DT, STATUS_NM,GRADE_NM\r\n"
					+ "		FROM MEMBER B\r\n"
					+ "		JOIN MEMBER_STATUS USING(STATUS_CD)\r\n"
					+ "		JOIN GRADE USING(GRADE_CD)\r\n"
					+sql2 +"\r\n"
					+ "		ORDER BY MEMBER_NO DESC) A)\r\n"
					+ "		WHERE RNUM BETWEEN ? AND ?";
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
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
	
	public int memberSearchListCount(String searchWord, String searchTag, Connection conn) throws Exception {
		int memberSearchListCount = 0;
		try {
			String sql2 = null;
			switch(searchTag) {
			case "no": sql2 = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "email": sql2 = "WHERE MEMBER_EMAIL LIKE '%"+searchWord +"%'";break;
			case "name" : sql2 = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "enrollDate" : if(searchWord.length() == 15) {
									String str1 = searchWord.substring(0,6);
									String str2 = searchWord.substring(9,15);
									str1 = stringToDate(str1);
									str2 = stringToDate(str2);
									
									sql2 = "WHERE TO_DATE(ENROLL_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;

								}else if(searchWord.length() == 6) {
									String str = searchWord.substring(0, 6);
									sql2 = "WHERE TO_DATE(ENROLL_DT) = '"+ str +"'"; break;
									
								}
//			case "report" : sql2 = "WHERE MEMBER_ LIKE '%"+searchWord +"%'"; break;
			case "violation" : sql2 = "WHERE VIOLATION_COUNT = "+searchWord; break;
			case "status" : sql2 = "WHERE SATATUS_NM LIKE '%"+searchWord +"%'"; break;
			}

			String sql1 = "SELECT COUNT(*) FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "		(SELECT MEMBER_NO, MEMBER_NM, MEMBER_EMAIL, \r\n"
					+ "			TO_CHAR(ENROLL_DT, 'YYYY-MM-DD') ENROLL_DT, INTRO, (SELECT COUNT(*)\r\n"
					+ "                                                      FROM VIOLATION A\r\n"
					+ "                                                      WHERE A.MEMBER_NO = B.MEMBER_NO) VIOLATION_COUNT,\r\n"
					+ "			TO_CHAR(MODIFY_DT, 'YYYY-MM-DD') MODIFY_DT, STATUS_NM,GRADE_NM\r\n"
					+ "		FROM MEMBER B\r\n"
					+ "		JOIN MEMBER_STATUS USING(STATUS_CD)\r\n"
					+ "		JOIN GRADE USING(GRADE_CD)\r\n"
					+sql2 +"\r\n"
					+ "		ORDER BY MEMBER_NO DESC) A)";
			
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberSearchListCount = rs.getInt(1);
			}
		}finally {
			pstmt.close();
			rs.close();
		}
		return memberSearchListCount;
	}
	
	public int updateViolationPlus(Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateViolationPlus");
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}

	public int updateViolationMinus(Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateViolationMinus");
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			System.out.println(sql);
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}

	public Member adminLogin(String adminPw, Connection conn) throws Exception {
		Member loginMember = null;
		// 처음에 null로 했어야 했내. 객체에 저장하면 member의 int값이 0 처리되서 넘어가내
		// 아 여기서 loginMember가 null값이 나오는게아니라 int 값은 0처리되서 값이 넘어가지내
		try {
			String sql = prop.getProperty("adminLogin");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, adminPw);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberPw(rs.getString("MEMBER_PW"));
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
//				loginMember.setEnrollDate(rs.getString("EMROLL_DT"));
//				loginMember.setIntro(rs.getString("INTRO"));
				loginMember.setViolationCount(rs.getInt("VIOLATION_COUNT"));
//				loginMember.setModifyDate(rs.getString("MODIFY_DT"));
				loginMember.setStatusCode(rs.getInt("STATUS_CD"));
				loginMember.setGradeCode(rs.getInt("GRADE_CD"));
				
			}
		}finally {
			pstmt.close();
		}
		
		return loginMember;
	}

	public int insertViolationPlus(int memberNo, String content, Connection conn) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("insertViolationPlus");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	
	
	
	}

	public List<Report> selectViolation(int memberNo, Connection conn) throws Exception {
		List<Report> violationList = new ArrayList<Report>();
		
		try {
			String sql = prop.getProperty("selectViolation");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Report violation = new Report();
				violation.setReportNo(rs.getInt(1));
				violation.setMemberNo(rs.getInt(2));
				violation.setReportContent(rs.getString(3));
				violationList.add(violation);
			}
		}finally {
			
		}
		return violationList;
	}

	public int deleteViolation(int violationNo, Connection conn) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("deleteViolation");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, violationNo);
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	
	}

	public int insertDeletePost(int postNo, String content, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertDeletePost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, postNo);
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;
	
	
	}

	public int updatePostStatus(int postNo, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePostStatus");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}


}
