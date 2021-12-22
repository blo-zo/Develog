package semi.blozo.develog.member.model;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;


public class MemberService {
	private MemberDAO dao = new MemberDAO();
	
	/** 로그인입니다.
	 * @param memberEmail
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String memberEmail, String memberPw)throws Exception {
		Connection conn = getConnection();
		
		Member loginMember = dao.login(memberEmail , memberPw , conn);
		
		
		close(conn);
		return loginMember;
	}

	/**회원가입
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member)throws Exception {
		Connection conn = getConnection();
		int result = dao.signUp(member,conn);
		if(result>0) commit(conn);
		else		 rollback(conn);
		close(conn);		
		return result;
	}

	
	
	

	/** 닉네임 중복검사
	 * @param inputName
	 * @return
	 * @throws Exception
	 */
	public int nameDubCheck(String inputName)throws Exception {
		
		Connection conn = getConnection();
		int result = dao.nameDubCheck(inputName , conn);
		
		close(conn);
		
		return result;
	}
	/** 이메일 중복 확인
	 * @param inputEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail)throws Exception {
		
		Connection conn = getConnection();
		int result = dao.emailDupCheck(inputEmail , conn);
		close(conn);
		
		return result;
	}
	
	
	
	
	
	/** 비밀번호 찾기 (업데이트)
	 * @param email
	 * @param searchPw
	 * @return result
	 * @throws Exception
	 */
	public int searchPw(String email, String searchPw)throws Exception{
		Connection conn = getConnection();
		
		int result = dao.searchPw(email,searchPw, conn);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		
		
		return result;
	}

	
	
	
	
	/** 비밀번호 변경
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw, int memberNo)throws Exception{
		Connection conn  = getConnection();
		
		int result = dao.updatePw(currentPw , newPw , memberNo , conn);
		
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		close(conn);
		return result;
	}


	
}
