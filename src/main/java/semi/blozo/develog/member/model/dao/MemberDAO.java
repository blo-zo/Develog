package semi.blozo.develog.member.model.dao;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.blozo.develog.member.model.vo.Member;
public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberDAO() {
		try {
			prop = new Properties();
			String filePath
			= MemberDAO.class.getResource("/semi/blozo/develog/sql/member-query.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	
	}

	/** 로그인입니다.
	 * @param memberEmail
	 * @param memberPw
	 * @param conn
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String memberEmail, String memberPw, Connection conn)throws Exception {
		Member loginMember = null;
		
		try {
			String sql = prop.getProperty("login");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberEmail);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
			
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberPw(memberPw);
				loginMember.setMemberNm(rs.getString("MEMBER_NM"));
				loginMember.setMemberEmail(memberEmail);
				loginMember.setEnrollDt(rs.getDate("ENROLL_DT"));
				loginMember.setIntro("INTRO");
				loginMember.setVlolationCount(rs.getInt("VIOLATION_COUNT"));
				loginMember.setModifyDt(rs.getDate("MODIFY_DT"));
				loginMember.setStatusCd(rs.getInt("STATUS_CD"));
				loginMember.setGradeCd(rs.getInt("GRADE_CD"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return loginMember;
	}

	/** 회원가입입니다.
	 * @param member
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member, Connection conn)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberNm());
			pstmt.setString(3, member.getMemberEmail());
			
			result = pstmt.executeUpdate();	
		}finally {
			close(pstmt);
			
		}
		return result;
	}
	

	
	/** 회원 소셜  DEFAULT 값삽입
	 * @return result1 성공 1 실패 0 
	 * @throws Exception
	 */
	public int insertSns(Connection conn) throws Exception{
		int result1 = 0;
		try {
			String sql = prop.getProperty("insertSns");
			pstmt = conn.prepareStatement(sql);
			
			
			result1 = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		
		return result1;
	}
	/** 블로그 제목 삽입
	 * @param conn
	 * @return result2 성공 1 실패 2
	 * @throws Exception
	 */
	public int insertBlog(Connection conn)throws Exception {
					int result2 = 0;
				try {
					String sql = prop.getProperty("insertBlog");
					pstmt = conn.prepareStatement(sql);
					
					result2 = pstmt.executeUpdate();
				}finally {
					close(pstmt);
				}
		
		return result2;
	}


	public int insertSNS(Member member, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	
	}

	public int insertBlog(Member member, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	


	/** 닉네임 중복검사
	 * @param inputName
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int nameDubCheck(String inputName, Connection conn)throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("nameDupCheck");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputName);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				
			}
			System.out.println(result);
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	/** 이메일 중복검사
	 * @param inputEmail
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail, Connection conn)throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("emailDupCheck");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputEmail);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	
	/** 비밀번호 찾기
	 * @param email
	 * @param searchPw
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int searchPw(String email, String searchPw, Connection conn)throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("searchPw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,searchPw);
			pstmt.setString(2, email);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}
	
	/**비밀번호 변경
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw, int memberNo, Connection conn)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, currentPw);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	

	


	
	


	
}
