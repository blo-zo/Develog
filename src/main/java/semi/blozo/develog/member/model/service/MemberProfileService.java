package semi.blozo.develog.member.model.service;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.blozo.develog.common.XSS;
import semi.blozo.develog.member.model.dao.MemberDAO;
import semi.blozo.develog.member.model.dao.MemberProfileDAO;
import semi.blozo.develog.member.model.vo.Member;
import semi.blozo.develog.member.model.vo.ProfileImgVO;
import semi.blozo.develog.member.model.vo.ProfileVO;


public class MemberProfileService {
	private MemberProfileDAO dao = new MemberProfileDAO();

	/** 회원 정보 조회 
	 * @param memberNo
	 * @return profile
	 * @throws Exception
	 */
	public ProfileVO selectProfile(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		ProfileVO profileVO = dao.selectProfile(memberNo, conn);
		
		close(conn);
		return profileVO;
	}

	
	/** 회원 이미지 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public ProfileImgVO selectProfileImg(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		ProfileImgVO profileImg = dao.selectProfileImgVO(memberNo, conn);
		
		close(conn);
		return profileImg;
	}

	
	/** 회원 정보 수정
	 * @param profileVO
	 * @param memberImg 
	 * @return result
	 * @throws Exception
	 */
	public int updateProfile(ProfileVO profileVO, ProfileImgVO memberImg) throws Exception{
		Connection conn = getConnection();
		
		// xss 처리
		profileVO.setMemberNm(XSS.replaceParameter(profileVO.getMemberNm()));
		profileVO.setIntro(XSS.replaceParameter(profileVO.getIntro()));
		
		profileVO.setBlogTitle(XSS.replaceParameter(profileVO.getBlogTitle()));
		
		profileVO.setSnsEmail(XSS.replaceParameter(profileVO.getSnsEmail()));
		profileVO.setSnsGit(XSS.replaceParameter(profileVO.getSnsGit()));
		profileVO.setSnsTwitt(XSS.replaceParameter(profileVO.getSnsTwitt()));
		profileVO.setSnsFbook(XSS.replaceParameter(profileVO.getSnsFbook()));
		profileVO.setSnsHome(XSS.replaceParameter(profileVO.getSnsHome()));
		
		// 1) 닉네임, 한 줄 소개
		int result= dao.updateProfileMember(profileVO,  conn);
		
		if(result > 0) {
			// 2) 디벨로그 제목
			result = dao.updateProfileBlog(profileVO,  conn);

			if(result > 0) {
				// 3) 소셜정보
				result = dao.updateProfileSns(profileVO,  conn);
				
				if(result >0) {
					// 4) 회원 이미지 유무 조회
					result = dao.selectMemberImg(profileVO,  conn);
					
					if(result >0) {
						// 4-1) 회원 이미지 있을 시 수정
						result = dao.updateMemberImg(memberImg, conn);	
					}
//					}else {
//						// 4-2) 회원 이미지 없을 시 삽입
//						result = dao.insertMemberImg(memberImg, conn);
//						
//					}
					if(result >0) { conn.commit(); }
					
					else { conn.rollback(); }
					
					
				}else { conn.rollback(); }
				
			} else { rollback(conn); }
			
		}  else { rollback(conn);}	
		
		
		close(conn);
		
		return result;
		


	}


	public int updateSecession(int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateSecession(memberNo, conn);
		
		if(result >0) conn.commit();
		else		  conn.rollback();
		
		conn.close();
		
		return result;
	
	}


}
