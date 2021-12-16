package semi.develog.admin.model.dao;

import static semi.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.develog.admin.model.vo.Member;
import semi.develog.admin.model.vo.Pagination;
import semi.develog.admin.model.vo.Post;
import semi.develog.admin.model.vo.Report;

public class AdminDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	   public AdminDAO() {
		      String filePath = AdminDAO.class.getResource("/semi/develog/sql/admin.xml").getPath();                    
		      
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

	public List<Report> listReport(Pagination pagination, Connection conn) throws Exception{
		List<Report> listReport = new ArrayList<Report>();
		
		
		
		return listReport;
	}

}
