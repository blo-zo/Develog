package semi.member.model.vo;

import java.sql.Date;

public class MemberVO {
	
		private int memberNo;			// 회원 번호
		private String memberPw;		// 비밀번호
		private String memberName;		// 닉네임
		private String memberEmail;		// 이메일
		
		private Date enrollDate; 		// 회원가입일
		private String intro;			// 한 줄 소개
		
		
		private int statusCode;			// 회원 상태 코드
		private int gradeCode;			// 회원 등급 코드

		// 기본 생성자
		public MemberVO() {}

		public int getMemberNo() {
			return memberNo;
		}

		public void setMemberNo(int memberNo) {
			this.memberNo = memberNo;
		}

		public String getMemberPw() {
			return memberPw;
		}

		public void setMemberPw(String memberPw) {
			this.memberPw = memberPw;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getMemberEmail() {
			return memberEmail;
		}

		public void setMemberEmail(String memberEmail) {
			this.memberEmail = memberEmail;
		}

		public Date getEnrollDate() {
			return enrollDate;
		}

		public void setEnrollDate(Date enrollDate) {
			this.enrollDate = enrollDate;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public int getGradeCode() {
			return gradeCode;
		}

		public void setGradeCode(int gradeCode) {
			this.gradeCode = gradeCode;
		}

		@Override
		public String toString() {
			return "MemberVO [memberNo=" + memberNo + ", memberPw=" + memberPw + ", memberName=" + memberName
					+ ", memberEmail=" + memberEmail + ", enrollDate=" + enrollDate + ", intro=" + intro
					+ ", statusCode=" + statusCode + ", gradeCode=" + gradeCode + "]";
		}

		
		
		
}
