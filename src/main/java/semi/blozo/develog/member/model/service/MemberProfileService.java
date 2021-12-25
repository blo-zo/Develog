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
		
		int result= dao.updateProfileMember(profileVO,  conn);
		
//		result = dao.updateProfileBlog(profileVO,  conn);
		
		
		
		
		
		if(result > 0) {
			
			result= dao.updateMemberImg(memberImg, conn);
			// 둘 다 성공했을떄 
			if(result == 0) { // 실패하면 false, for문 멈춘다
				rollback(conn);
				   
			}  
		}
		
		
		
		
		close(conn);
		
		return result;
	}


	



	
}
