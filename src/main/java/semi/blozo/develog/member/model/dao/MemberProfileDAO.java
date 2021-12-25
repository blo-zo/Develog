package semi.blozo.develog.member.model.dao;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.blozo.develog.member.model.vo.Member;
import semi.blozo.develog.member.model.vo.ProfileImgVO;
import semi.blozo.develog.member.model.vo.ProfileVO;
public class MemberProfileDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberProfileDAO() {
		try {
			prop = new Properties();
			String filePath
			= MemberProfileDAO.class.getResource("/semi/blozo/develog/sql/member-query2.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/** profile 조회 
	 * @param memberNo
	 * @param conn
	 * @return  profile
	 * @throws Exception
	 */
	public ProfileVO selectProfile(int memberNo, Connection conn)  throws Exception{

		ProfileVO profileVO = null;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectProfile"));
			
			pstmt.setInt(1, memberNo);
					
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				profileVO = new ProfileVO();
				profileVO.setMemberNo(rs.getInt("MEMBER_NO"));
				profileVO.setMemberNm(rs.getString("MEMBER_NM"));
				profileVO.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				profileVO.setIntro(rs.getString("INTRO"));
				
				profileVO.setBlogNo(rs.getInt("BLOG_NO"));
				profileVO.setBlogTitle(rs.getString("BLOG_TITLE"));
				
				profileVO.setSnsEmail(rs.getString("SNS_EMAIL"));
				profileVO.setSnsGit(rs.getString("SNS_GIT"));
				profileVO.setSnsTwitt(rs.getString("SNS_TWITT"));
				profileVO.setSnsFbook(rs.getString("SNS_FBOOK"));
				profileVO.setSnsHome(rs.getString("SNS_HOME"));
			}
					
		}finally {
			close(rs);
			close(pstmt);
		}
		return profileVO;
	}

	
	/** 회원이미지 조회
	 * @param memberNo
	 * @param conn
	 * @return 
	 * @throws Exception
	 */
	public ProfileImgVO selectProfileImgVO(int memberNo, Connection conn) throws Exception {

		ProfileImgVO profileImg = null;
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectProfileImg"));
				
			pstmt.setInt(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				profileImg = new ProfileImgVO();
				
				profileImg.setMemberImgPath(rs.getString("MEMBER_IMG_PATH"));
				profileImg.setMemberImgName(rs.getString("MEMBER_IMG_NM"));
				profileImg.setMemberImgOriginal(rs.getString("MEMBER_IMG_ORIGINAL"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return profileImg;
	}

	

	
	
	/** 회원정보 수정  1)닉네임, 한 줄 소개
	 * @param profileVO
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileMember(ProfileVO profileVO, Connection conn)  throws Exception{
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateProfileMember"));
			
			pstmt.setString(1, profileVO.getMemberNm());
			
			
		}  finally { close(pstmt); }
		
		return result;
	}

	
	
	/** 회원정보 수정  2) 디벨로그 제목
	 * @param profileVO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateProfileBlog(ProfileVO profileVO, Connection conn) throws Exception{
		int result = 0;
			
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateProfileBlog"));
			
			
			
			
		}  finally { close(pstmt); }
		
		return result;
	}

	
	
	/** 회원정보 수정 3) 벨로그 제목 
	 * @param profileVO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateProfileTitle(ProfileVO profileVO, Connection conn) throws Exception{
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateMemberImg"));
			
			
			
			
		}  finally { close(pstmt); }
		
		return result;
	}

	
	
	/** 회원정보 수정  4) 프로필 이미지 
	 * @param memberImg
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int updateMemberImg(ProfileImgVO memberImg, Connection conn)  throws Exception{
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(prop.getProperty("updateMemberImg"));
			
			
			
			
		}  finally { close(pstmt); }
		
		return result;
	}

	

	

	
	

	
	


	
}
