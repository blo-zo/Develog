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

import org.apache.tomcat.dbcp.dbcp2.cpdsadapter.PStmtKeyCPDS;

import semi.blozo.develog.admin.model.vo.Enquiry;
import semi.blozo.develog.admin.model.vo.Member;
import semi.blozo.develog.admin.model.vo.Pagination;
import semi.blozo.develog.admin.model.vo.Post;
import semi.blozo.develog.admin.model.vo.Reply;
import semi.blozo.develog.admin.model.vo.Report;
import semi.blozo.develog.common.XSS;

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

	public List<Post> selectPost(Pagination pagination, String searchWord, String searchTag, String orderTag, Connection conn) throws Exception {
		List<Post> postList = new ArrayList<Post>();
		
		try {
			String where = "";
			String order = "ORDER BY POST_NO DESC";
			
			switch(searchTag) {
			case "no" : where = "WHERE POST_NO LIKE '%"+searchWord +"%'"; break;
			case "title" : where = "WHERE POST_TITLE LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE POST_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE C.MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
											String str1 = searchWord.substring(0,6);
											String str2 = searchWord.substring(9,15);
											str1 = stringToDate(str1);
											str2 = stringToDate(str2);
											
											where = "WHERE TO_DATE(CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
							
										}else if(searchWord.length() == 6) {
											String str = searchWord.substring(0, 6);
											where = "WHERE TO_DATE(CREATE_DT) = '"+ str +"'"; break;
											
										}
			case "status" : where = "WHERE POST_STATUS_NM LIKE '%"+searchWord +"%'"; break;
			default : where = "";
			}
			
			switch(orderTag) {
			case "ascNo" : order ="ORDER BY POST_NO"; break;
			case "descNo" : order ="ORDER BY POST_NO DESC"; break;
			case "ascViews" : order ="ORDER BY READ_COUNT"; break;
			case "descViews" : order ="ORDER BY READ_COUNT DESC"; break;
			case "ascLikes" : order ="ORDER BY LIKE_COUNT "; break;
			case "descLikes" : order ="ORDER BY LIKE_COUNT DESC"; break;
			case "ascReports" : order ="ORDER BY REPORT_COUNT"; break;
			case "descReports" : order ="ORDER BY REPORT_COUNT DESC"; break;
			default : order ="ORDER BY POST_NO DESC";
			}
			
			String sql = "SELECT * FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "			(SELECT POST_NO, POST_TITLE, POST_CONTENT,\r\n"
					+ "					TO_CHAR(CREATE_DT, 'YYYY-MM-DD') ENROLL_DT,   C.MEMBER_NO, READ_COUNT, LIKE_COUNT, \r\n"
					+ "					TO_CHAR(A.MODIFY_DT, 'YYYY-MM-DD') MODIFY_DT,\r\n"
					+ "		       		BLOG_NO, CATEGORY_CD, POST_STATUS_NM,(SELECT COUNT(POST_NO) FROM POST_REPORT B GROUP BY POST_NO HAVING B.POST_NO = A.POST_NO) REPORT_COUNT, \r\n"
					+ "		       		(SELECT COUNT(*) FROM VIOLATION A WHERE A.MEMBER_NO = B.MEMBER_NO) VIOLATION_COUNT, C.MEMBER_NM\r\n"
					+ "			FROM POST A\r\n"
					+ "			JOIN BLOG B  USING(BLOG_NO)\r\n"
					+ "			JOIN POST_STATUS USING (POST_STATUS_CD)\r\n"
					+ "            JOIN MEMBER C ON(B.MEMBER_NO = C.MEMBER_NO)\r\n"
					+ where + "\r\n"
					+ order + ")\r\n"
					+ "					 A)\r\n"
					+ "		WHERE RNUM BETWEEN ? AND ?";

					
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				String content = rs.getString("POST_CONTENT").substring(0, 12);
//				 15 글자 넘어가면 안되는 이유가 0~12글자를 자를수 없을 정도로 작은 게 있기 때문이다
				String postContent = rs.getString("POST_CONTENT");
//				postContent = postContent.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
				postContent = postContent.replaceAll("<[^>]*>", "");
				Post post = new Post();
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(postContent);
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
				post.setMemberName(rs.getString("MEMBER_NM"));
				postList.add(post);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return postList;
	
	}
	
	
	
	public int postListCount(String searchWord, String searchTag, String orderTag, Connection conn) throws Exception{
		int postSearchListCount = 0;
		try {
			String where = "";
			String order = "ORDER BY POST_NO DESC";
			
			switch(searchTag) {
			case "no" : where = "WHERE POST_NO LIKE '%"+searchWord +"%'"; break;
			case "title" : where = "WHERE POST_TITLE LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE POST_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE C.MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
								String str1 = searchWord.substring(0,6);
								String str2 = searchWord.substring(9,15);
								str1 = stringToDate(str1);
								str2 = stringToDate(str2);
								
								where = "WHERE TO_DATE(CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
				
								}else if(searchWord.length() == 6) {
									String str = searchWord.substring(0, 6);
									where = "WHERE TO_DATE(CREATE_DT) = '"+ str +"'"; break;
									
								}
			case "status" : where = "WHERE POST_STATUS_NM LIKE '%"+searchWord +"%'"; break;
			default : where = "";
			}
			
			switch(orderTag) {
			case "ascNo" : order ="ORDER BY POST_NO"; break;
			case "descNo" : order ="ORDER BY POST_NO DESC"; break;
			case "ascViews" : order ="ORDER BY READ_COUNT"; break;
			case "descViews" : order ="ORDER BY READ_COUNT DESC"; break;
			case "ascLikes" : order ="ORDER BY LIKE_COUNT "; break;
			case "descLikes" : order ="ORDER BY LIKE_COUNT DESC"; break;
			case "ascReports" : order ="ORDER BY REPORT_COUNT"; break;
			case "descReports" : order ="ORDER BY REPORT_COUNT DESC"; break;
			default : order ="ORDER BY POST_NO DESC";
			}
			
			String sql = "SELECT COUNT(*) FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "			(SELECT POST_NO, POST_TITLE, POST_CONTENT,\r\n"
					+ "					TO_CHAR(CREATE_DT, 'YYYY-MM-DD') ENROLL_DT, C.MEMBER_NO, READ_COUNT, LIKE_COUNT, \r\n"
					+ "					TO_CHAR(A.MODIFY_DT, 'YYYY-MM-DD') MODIFY_DT,\r\n"
					+ "		       		BLOG_NO, CATEGORY_CD, POST_STATUS_NM,(SELECT COUNT(POST_NO) FROM POST_REPORT B GROUP BY POST_NO HAVING B.POST_NO = A.POST_NO) REPORT_COUNT, \r\n"
					+ "		       		(SELECT COUNT(*) FROM VIOLATION A WHERE A.MEMBER_NO = B.MEMBER_NO) VIOLATION_COUNT, C.MEMBER_NM\r\n"
					+ "			FROM POST A\r\n"
					+ "			JOIN BLOG B  USING(BLOG_NO)\r\n"
					+ "			JOIN POST_STATUS USING (POST_STATUS_CD)\r\n"
					+ "            JOIN MEMBER C ON(B.MEMBER_NO = C.MEMBER_NO)\r\n"
					+ where + "\r\n"
					+ order + ")\r\n"
					+ "					 A)\r\n";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				postSearchListCount = rs.getInt(1);
			}
		}finally {
			pstmt.close();
			rs.close();
		}
		return postSearchListCount;
	
	
	}

	public List<Report> selectReport(String searchWord, String searchTag, Pagination pagination, Connection conn) throws Exception{
		List<Report> listReport = new ArrayList<Report>();
		
		try {
			String where = "";
			String order = "ORDER BY POST_NO DESC";
			
			switch(searchTag) {
			case "no" : where = "WHERE REPORT_NO LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "nickname" : where = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "target" : where = "WHERE REPORT_TYPE LIKE '%"+searchWord +"%'"; break;
			case "targetNo" : where = "LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE REPORT_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
											String str1 = searchWord.substring(0,6);
											String str2 = searchWord.substring(9,15);
											str1 = stringToDate(str1);
											str2 = stringToDate(str2);
											
											where = "WHERE TO_DATE(CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
							
										}else if(searchWord.length() == 6) {
											String str = searchWord.substring(0, 6);
											where = "WHERE TO_DATE(CREATE_DT) = '"+ str +"'"; break;
											
										}
			default : where = "";
			}
			String sql = "";
			if(searchTag.equals("targetNo")) {
				sql = "SELECT * FROM(\r\n"
						+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
						+ "		(SELECT REPORT_NO, REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, POST_NO,MEMBER_NO, MEMBER_NM\r\n"
						+ "        FROM POST_REPORT\r\n"
						+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
						+ "WHERE POST_NO " +where 
						+ "         UNION ALL   \r\n"
						+ "       SELECT REPORT_NO,REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, REPLY_NO,MEMBER_NO, MEMBER_NM\r\n"
						+ "        FROM REPLY_REPORT\r\n"
						+ "        JOIN MEMBER USING(MEMBER_NO)"
						+ "WHERE REPLY_NO "+where
						+ ") A)\r\n"
						+ "		WHERE RNUM BETWEEN ? AND ?";
								
			}else {
				sql = "SELECT * FROM(\r\n"
						+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
						+ "		(SELECT REPORT_NO, REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, POST_NO,MEMBER_NO, MEMBER_NM\r\n"
						+ "        FROM POST_REPORT\r\n"
						+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
						+ where
						+ "         UNION ALL   \r\n"
						+ "       SELECT REPORT_NO,REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, REPLY_NO,MEMBER_NO, MEMBER_NM\r\n"
						+ "        FROM REPLY_REPORT\r\n"
						+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
						+ where
						+ ") A)\r\n"
						+ "		WHERE RNUM BETWEEN ? AND ?";
			}
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
				report.setCreateDate(rs.getString(4));
				report.setReportType(rs.getString(5));
				report.setTargetNo(rs.getInt(6));
				report.setMemberNo(rs.getInt(7));
				report.setMemberName(rs.getString(8));

				listReport.add(report);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return listReport;
	}
	
	public int reportListCount(String searchWord, String searchTag, Connection conn) throws Exception {
		int reportSearchListCount = 0;
	try {
		String where = "";
		String order = "ORDER BY POST_NO DESC";
		
		switch(searchTag) {
		case "no" : where = "WHERE REPORT_NO LIKE '%"+searchWord +"%'"; break;
		case "memberNo" : where = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
		case "nickname" : where = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
		case "target" : where = "WHERE REPORT_TYPE LIKE '%"+searchWord +"%'"; break;
		case "targetNo" : where = "LIKE '%"+searchWord +"%'"; break;
		case "content" : where = "WHERE REPORT_CONTENT LIKE '%"+searchWord +"%'"; break;
		case "createDate" : if(searchWord.length() == 15) {
										String str1 = searchWord.substring(0,6);
										String str2 = searchWord.substring(9,15);
										str1 = stringToDate(str1);
										str2 = stringToDate(str2);
										
										where = "WHERE TO_DATE(CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
						
									}else if(searchWord.length() == 6) {
										String str = searchWord.substring(0, 6);
										where = "WHERE TO_DATE(CREATE_DT) = '"+ str +"'"; break;
										
									}
		default : where = "";
		}
		String sql = "";
		if(searchTag.equals("targetNo")) {
			sql = "SELECT COUNT(*) FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "		(SELECT REPORT_NO, REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, POST_NO,MEMBER_NO, MEMBER_NM\r\n"
					+ "        FROM POST_REPORT\r\n"
					+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
					+ "WHERE POST_NO " +where 
					+ "         UNION ALL   \r\n"
					+ "       SELECT REPORT_NO,REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, REPLY_NO,MEMBER_NO, MEMBER_NM\r\n"
					+ "        FROM REPLY_REPORT\r\n"
					+ "        JOIN MEMBER USING(MEMBER_NO)"
					+ "WHERE REPLY_NO "+where
					+ ") A)";
							
		}else {
			sql = "SELECT COUNT(*) FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "		(SELECT REPORT_NO, REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, POST_NO,MEMBER_NO, MEMBER_NM\r\n"
					+ "        FROM POST_REPORT\r\n"
					+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
					+ where
					+ "         UNION ALL   \r\n"
					+ "       SELECT REPORT_NO,REPORT_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), REPORT_TYPE, REPLY_NO,MEMBER_NO, MEMBER_NM\r\n"
					+ "        FROM REPLY_REPORT\r\n"
					+ "        JOIN MEMBER USING(MEMBER_NO)"
					+ where
					+ ") A)";
		}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			reportSearchListCount = rs.getInt(1);
		}
	}finally {
		pstmt.close();
		rs.close();
	}
	return reportSearchListCount;
	}

	public List<Enquiry> selectEnquiry(String searchWord, String searchTag, String orderTag, Pagination pagination, Connection conn) throws Exception {
		List<Enquiry> enquiryList = new ArrayList<Enquiry>();
		// 커밋을 꼭 확인하자 
		try {
			String where = "";
			String order = "ORDER BY ENQUIRY_NO DESC";
			
			switch(searchTag) {
			case "enquiryNo" : where = "WHERE ENQUIRY_NO LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "nickname" : where = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "title" : where = "WHERE ENQUIRY_TITLE LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE ENQUIRY_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
											String str1 = searchWord.substring(0,6);
											String str2 = searchWord.substring(9,15);
											str1 = stringToDate(str1);
											str2 = stringToDate(str2);
											
											where = "WHERE TO_DATE(CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
							
										}else if(searchWord.length() == 6) {
											String str = searchWord.substring(0, 6);
											where = "WHERE TO_DATE(CREATE_DT) = '"+ str +"'"; break;											
										}
			default : where = "";
			}
			
			switch(orderTag) {
			case "answer" : order ="ORDER BY PARENT_ENQUIRY "; break;
			case "noAnswer" : order ="ORDER BY PARENT_ENQUIRY DESC"; break;
			default : order ="ORDER BY ENQUIRY_NO DESC";
			}
			
			String sql = "SELECT * FROM(\r\n"
					+ "        SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "        (SELECT ENQUIRY_NO, ENQUIRY_TITLE, ENQUIRY_CONTENT, TO_CHAR(CREATE_DT, 'YYYY-MM-DD'), MEMBER_NO, MEMBER_NM,\r\n"
					+ "                (SELECT COUNT(*) FROM ENQUIRY B WHERE B.PARENT_ENQUIRY = C.ENQUIRY_NO)\r\n"
					+ "        FROM ENQUIRY C\r\n"
					+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
					+ where
					+ order 
					+ "        ) A)\r\n"
					+ "        WHERE RNUM BETWEEN ? AND ?";
			
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Enquiry enq = new Enquiry();
				String enquiryContent = rs.getString(4); 
				enquiryContent = enquiryContent.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
				enq.setEnquiryNo(rs.getInt(2));
				enq.setEnquiryTitle(rs.getString(3));
				enq.setEnquiryContent(enquiryContent);
				enq.setCreateDate(rs.getString(5));
				enq.setMemberNo(rs.getInt(6));
				enq.setMemberName(rs.getString(7));
				enq.setParentEnquiry(rs.getInt(8));
				
				enquiryList.add(enq);
			}
			
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return enquiryList;
	
	}
	
	public int enquiryListCount(String searchWord, String searchTag, String orderTag, Connection conn) throws Exception{
		int enquirySearchListCount = 0;
		try {
			String where = "";
			String order = "ORDER BY ENQUIRY_NO DESC";
			
			switch(searchTag) {
			case "enquiryNo" : where = "WHERE ENQUIRY_NO LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "nickname" : where = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "title" : where = "WHERE ENQUIRY_TITLE LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE ENQUIRY_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
											String str1 = searchWord.substring(0,6);
											String str2 = searchWord.substring(9,15);
											str1 = stringToDate(str1);
											str2 = stringToDate(str2);
											
											where = "WHERE TO_DATE(CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
							
										}else if(searchWord.length() == 6) {
											String str = searchWord.substring(0, 6);
											where = "WHERE TO_DATE(CREATE_DT) = '"+ str +"'"; break;											
										}
			default : where = "";
			}
			
			switch(orderTag) {
			case "answer" : order ="ORDER BY PARENT_ENQUIRY DESC"; break;
			case "noAnswer" : order ="ORDER BY PARENT_ENQUIRY DESC"; break;
			default : order ="ORDER BY ENQUIRY_NO DESC";
			}
			
			String sql = "SELECT COUNT(*) FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM\r\n"
					+ "		(SELECT ENQUIRY_NO,ENQUIRY_TITLE,ENQUIRY_CONTENT,TO_CHAR(CREATE_DT, 'YYYY-MM-DD') CREATE_DT, MEMBER_NO, PARENT_ENQUIRY, MEMBER_NM\r\n"
					+ "		FROM ENQUIRY\r\n"
					+ "        JOIN MEMBER USING(MEMBER_NO)\r\n"
					+ where
					+ order
					+ ") A)";
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				enquirySearchListCount = rs.getInt(1);
			}
		}finally {
			pstmt.close();
			rs.close();
		}
		return enquirySearchListCount;
	
	
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
				report.setMemberName(rs.getString(7));
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
				enquiry.setMemberNo(rs.getInt(5));
				enquiry.setParentEnquiry(rs.getInt(6));
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
				violation.setReportContent(rs.getString(2));
				violation.setMemberNo(rs.getInt(3));
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

	public Post selectDeletePost(int postNo, Connection conn) throws Exception {
		Post removeContent = new Post();
		
		try {
			String sql = prop.getProperty("selectDeletePost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				removeContent.setMemberNo(rs.getInt(1));
				removeContent.setPostContent(rs.getString(2));
				removeContent.setCreateDate(rs.getString(3));
				removeContent.setPostNo(rs.getInt(4));
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		
		return removeContent;
	}

	public int deletePostContent(int postNo, Connection conn) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deletePostContent");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}

	public int updateResotrePostStatus(int postNo, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateResotrePostStatus");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		
		return result;
	
	}

	public int insertEnquiry(Enquiry enquiry, String content, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("insertEnquiry");
			pstmt = conn.prepareStatement(sql);
			String title = "Develog : " + enquiry.getEnquiryTitle();
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, enquiry.getEnquiryNo());
			pstmt.setInt(4, enquiry.getMemberNo());
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	}

	public List<Reply> selectReply(String searchWord, String searchTag, String orderTag, Pagination pagination, Connection conn) throws Exception {
		List<Reply> listReply = new ArrayList<Reply>();
		try {
			String where = "";
			String order = "ORDER BY REPLY_NO DESC";
			
			switch(searchTag) {
			case "no" : where = "WHERE REPLY_NO LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "memberName" : where = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE REPLY_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
											String str1 = searchWord.substring(0,6);
											String str2 = searchWord.substring(9,15);
											str1 = stringToDate(str1);
											str2 = stringToDate(str2);
											
											where = "WHERE TO_DATE(REPLY_CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
							
										}else if(searchWord.length() == 6) {
											String str = searchWord.substring(0, 6);
											where = "WHERE TO_DATE(REPLY_CREATE_DT) = '"+ str +"'"; break;
											
										}
			case "postNo" : where = "WHERE POST_NO LIKE '%"+searchWord +"%'"; break;
			case "status" : where = "WHERE REPLY_STATUS_NM LIKE '%"+searchWord +"%'"; break;
			default : where = "";
			}
			
			switch(orderTag) {
			case "ascReports" : order ="ORDER BY REPORT_COUNT"; break;
			case "descReports" : order ="ORDER BY REPORT_COUNT DESC"; break;
			default : order ="ORDER BY REPLY_NO DESC";
			}
			
			String sql = "SELECT * FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM (     \r\n"
					+ "		SELECT REPLY_NO, REPLY_CONTENT, TO_CHAR(REPLY_CREATE_DT, 'YYYY-MM-DD') REPLY_CREATE_DT, POST_NO, MEMBER_NO, MEMBER_NM, REPLY_STATUS_NM,\r\n"
					+ "                (SELECT COUNT(*) FROM REPLY_REPORT B WHERE B.REPLY_NO = A.REPLY_NO) REPORT_COUNT \r\n"
					+ "		FROM REPLY A\r\n"
					+ "		JOIN MEMBER USING(MEMBER_NO)\r\n"
					+ "		JOIN REPLY_STATUS USING(REPLY_STATUS_CD)\r\n"
					+ where +"\r\n"
					+ order + ") A)\r\n"
					+ "		WHERE RNUM BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			int startRow = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			
			int endRow = startRow + pagination.getLimit() -1;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				reply.setReplyCreateDate(rs.getString("REPLY_CREATE_DT"));
				reply.setPostNo(rs.getInt("POST_NO"));
				reply.setMemberNo(rs.getInt("MEMBER_NO"));
				reply.setMemberName(rs.getString("MEMBER_NM"));
				reply.setReplyStatusName(rs.getString("REPLY_STATUS_NM"));
				reply.setReportCount(rs.getInt("REPORT_COUNT"));
				listReply.add(reply);
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return listReply;
	
	}

	public int replytListCount(String searchWord, String searchTag, String orderTag, Connection conn) throws Exception {
		int replySearchListCount = 0;
		try {
			String where = "";
			String order = "ORDER BY REPLY_NO DESC";
			
			switch(searchTag) {
			case "no" : where = "WHERE REPLY_NO LIKE '%"+searchWord +"%'"; break;
			case "memberNo" : where = "WHERE MEMBER_NO LIKE '%"+searchWord +"%'"; break;
			case "memberName" : where = "WHERE MEMBER_NM LIKE '%"+searchWord +"%'"; break;
			case "content" : where = "WHERE REPLY_CONTENT LIKE '%"+searchWord +"%'"; break;
			case "createDate" : if(searchWord.length() == 15) {
											String str1 = searchWord.substring(0,6);
											String str2 = searchWord.substring(9,15);
											str1 = stringToDate(str1);
											str2 = stringToDate(str2);
											
											where = "WHERE TO_DATE(REPLY_CREATE_DT) BETWEEN '"+ str1 +"' AND '"+str2+"'"; break;
							
										}else if(searchWord.length() == 6) {
											String str = searchWord.substring(0, 6);
											where = "WHERE TO_DATE(REPLY_CREATE_DT) = '"+ str +"'"; break;
											
										}
			case "postNo" : where = "WHERE POST_NO LIKE '%"+searchWord +"%'"; break;
			case "status" : where = "WHERE REPLY_STATUS_NM LIKE '%"+searchWord +"%'"; break;
			default : where = "";
			}
			
			switch(orderTag) {
			case "ascReports" : order ="ORDER BY REPORT_COUNT"; break;
			case "descReports" : order ="ORDER BY REPORT_COUNT DESC"; break;
			default : order ="ORDER BY REPLY_NO DESC";
			}
			
			String sql = "SELECT COUNT(*) FROM(\r\n"
					+ "		SELECT ROWNUM RNUM, A.* FROM (     \r\n"
					+ "		SELECT REPLY_NO, REPLY_CONTENT, TO_CHAR(REPLY_CREATE_DT, 'YYYY-MM-DD') REPLY_CREATE_DT, POST_NO, MEMBER_NO, MEMBER_NM, REPLY_STATUS_NM,\r\n"
					+ "                (SELECT COUNT(*) FROM REPLY_REPORT B WHERE B.REPLY_NO = A.REPLY_NO) REPORT_COUNT \r\n"
					+ "		FROM REPLY A\r\n"
					+ "		JOIN MEMBER USING(MEMBER_NO)\r\n"
					+ "		JOIN REPLY_STATUS USING(REPLY_STATUS_CD)\r\n"
					+ where +"\r\n"
					+ order + ") A)";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				replySearchListCount = rs.getInt(1);
			}
		}finally {
			pstmt.close();
			rs.close();
		}
		return replySearchListCount;
	
	}

	public int updateReplyStatus(int replyNo, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("updateReplyStatus");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		
		}finally {
			pstmt.close();
		}
		return result;
	}

	public Reply selectBlindReply(int replyNo, Connection conn) throws Exception{
		Reply reply = new Reply();
		
		try {
			String sql = prop.getProperty("selectBlindReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reply.setReplyNo(rs.getInt("BLIND_REPLY_NO"));
				reply.setReplyContent(rs.getString("BLIND_REPLY_CONTENT"));
				reply.setReplyCreateDate(rs.getString("BLIND_REPLY_DT"));
				reply.setPostNo(rs.getInt("REPLY_NO"));
			}
		}finally {
			rs.close();
			pstmt.close();
		}
		return reply;
	}

	public int insertBlindReply(int replyNo, String content, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("insertBlindReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2, replyNo);
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
				
		return result;
	}

	public int deleteBlindReply(int replyNo, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteBlindReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			pstmt.close();
		}
		
		return result;
		
	}

	public int restoreReply(int replyNo, Connection conn) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("restoreReply");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		
		}finally {
			pstmt.close();
		}
		return result;
	
	}

	public int updateMemberRestore(int memberNo, Connection conn) throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("updateMemberRestore");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
		}finally {
			pstmt.close();
		}
		return result;
	
	}

	






}
