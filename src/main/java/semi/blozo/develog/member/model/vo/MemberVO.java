package semi.blozo.develog.member.model.vo;

import java.sql.Date;

public class MemberVO {
	private int memberNo; // 회원 번호
	private String memberPw; // 회원 비밀번호
	private String memberNm; // 회원 이름
	private String memberEmail; // 회원 이메일
	
	private Date enrollDt; // 회원 가입일
	private String intro;  	 // 한줄 소개
	private int vlolationCount; // 경고수
	private Date modifyDt; // 수정일
	
	private int statusCd;// 회원상태코드
	private int gradeCd; // 회원 등급 코드
	private int blogNo; //블로그넘버
	private String blogTitle; // 블로그제목
	
	public MemberVO() {
		
	}
	
	public int getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public MemberVO(String memberPw, String memberNm, String memberEmail) {
		super();
		this.memberPw = memberPw;
		this.memberNm = memberNm;
		this.memberEmail = memberEmail;
	}

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
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}
	public int getVlolationCount() {
		return vlolationCount;
	}
	public void setVlolationCount(int vlolationCount) {
		this.vlolationCount = vlolationCount;
	}
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	public int getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(int statusCd) {
		this.statusCd = statusCd;
	}
	public int getGradeCd() {
		return gradeCd;
	}
	public void setGradeCd(int gradeCd) {
		this.gradeCd = gradeCd;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberPw=" + memberPw + ", memberNm=" + memberNm + ", memberEmail="
				+ memberEmail + ", enrollDt=" + enrollDt + ", intro=" + intro + ", vlolationCount=" + vlolationCount
				+ ", modifyDt=" + modifyDt + ", statusCd=" + statusCd + ", gradeCd=" + gradeCd + ", blogNo=" + blogNo
				+ ", blogTitle=" + blogTitle + "]";
	}

	
	
	
	
	
}
	
	
