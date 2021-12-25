package semi.blozo.develog.member.model.service;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;

import semi.blozo.develog.member.model.dao.MemberDAO;
import semi.blozo.develog.member.model.dao.MemberProfileDAO;
import semi.blozo.develog.member.model.vo.Member;
import semi.blozo.develog.member.model.vo.MemberImg;
import semi.blozo.develog.member.model.vo.MemberSNS;


public class MemberProfileService {
	private MemberProfileDAO dao = new MemberProfileDAO();



	/** 회원 정보 수정
	 * @param member
	 * @param memberSNS
	 * @param memberImg
	 * @return 
	 * @throws Exception
	 */
	public int updateProfile(Member member, MemberSNS memberSNS, MemberImg memberImg) throws Exception{
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

	
}
