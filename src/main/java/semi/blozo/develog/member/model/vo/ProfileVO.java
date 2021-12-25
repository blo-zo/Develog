package semi.blozo.develog.member.model.vo;

import java.sql.Date;

public class ProfileVO {
	private int memberNo; // 회원 번호
	private String memberNm; // 회원 이름
	private String memberEmail; // 회원 이메일

	private String intro;  	 // 한줄 소개
	private String blogTitle; // 블로그제목
	private int blogNo; //블로그넘버
	
	
	
	
	private int memberSnsNo; // 회원 소셜번호
	private String snsEmail;
	private String snsGit;
	private String snsTwitt;
	private String snsFbook;
	private String snsHome;
	
	
	
	public ProfileVO() {
		
	}



	public int getMemberNo() {
		return memberNo;
	}



	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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



	public String getIntro() {
		return intro;
	}



	public void setIntro(String intro) {
		this.intro = intro;
	}



	public String getBlogTitle() {
		return blogTitle;
	}



	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}



	public int getBlogNo() {
		return blogNo;
	}



	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}



	



	public int getMemberSnsNo() {
		return memberSnsNo;
	}



	public void setMemberSnsNo(int memberSnsNo) {
		this.memberSnsNo = memberSnsNo;
	}



	public String getSnsEmail() {
		return snsEmail;
	}



	public void setSnsEmail(String snsEmail) {
		this.snsEmail = snsEmail;
	}



	public String getSnsGit() {
		return snsGit;
	}



	public void setSnsGit(String snsGit) {
		this.snsGit = snsGit;
	}



	public String getSnsTwitt() {
		return snsTwitt;
	}



	public void setSnsTwitt(String snsTwitt) {
		this.snsTwitt = snsTwitt;
	}



	public String getSnsFbook() {
		return snsFbook;
	}



	public void setSnsFbook(String snsFbook) {
		this.snsFbook = snsFbook;
	}



	public String getSnsHome() {
		return snsHome;
	}



	public void setSnsHome(String snsHome) {
		this.snsHome = snsHome;
	}



	@Override
	public String toString() {
		return "ProfileVO [memberNo=" + memberNo + ", memberNm=" + memberNm
				+ ", memberEmail=" + memberEmail + ", intro=" + intro + ", blogTitle=" + blogTitle + ", blogNo="
				+ blogNo + ",  memberSnsNo=" + memberSnsNo + ", snsEmail=" + snsEmail + ", snsGit=" + snsGit + ", snsTwitt="
				+ snsTwitt + ", snsFbook=" + snsFbook + ", snsHome=" + snsHome + "]";
	}
	
	
	
	
	
}
	
	
