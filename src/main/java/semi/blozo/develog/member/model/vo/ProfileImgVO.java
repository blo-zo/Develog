package semi.blozo.develog.member.model.vo;

import java.sql.Date;

public class ProfileImgVO {
	private int memberImgNo; // 회원 이미지
	private String memberImgPath;
	private String memberImgName;
	private Date memberImgDt;
	private String memberImgOriginal;
	private int BlogNo;
	
	public ProfileImgVO() {}


	public int getMemberImgNo() {
		return memberImgNo;
	}


	public void setMemberImgNo(int memberImgNo) {
		this.memberImgNo = memberImgNo;
	}


	public String getMemberImgPath() {
		return memberImgPath;
	}


	public void setMemberImgPath(String memberImgPath) {
		this.memberImgPath = memberImgPath;
	}


	public String getMemberImgName() {
		return memberImgName;
	}


	public void setMemberImgName(String memberImgName) {
		this.memberImgName = memberImgName;
	}


	public Date getMemberImgDt() {
		return memberImgDt;
	}


	public void setMemberImgDt(Date memberImgDt) {
		this.memberImgDt = memberImgDt;
	}


	public String getMemberImgOriginal() {
		return memberImgOriginal;
	}


	public void setMemberImgOriginal(String memberImgOriginal) {
		this.memberImgOriginal = memberImgOriginal;
	}

	
	

	public int getBlogNo() {
		return BlogNo;
	}


	public void setBlogNo(int blogNo) {
		BlogNo = blogNo;
	}


	@Override
	public String toString() {
		return "ProfileImgVO [memberImgNo=" + memberImgNo + ", memberImgPath=" + memberImgPath + ", memberImgName="
				+ memberImgName + ", memberImgDt=" + memberImgDt + ", memberImgOriginal=" + memberImgOriginal
				+ ", BlogNo=" + BlogNo + "]";
	}


	
	
	
	
}
