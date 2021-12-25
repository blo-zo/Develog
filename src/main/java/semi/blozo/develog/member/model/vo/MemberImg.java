package semi.blozo.develog.member.model.vo;

import java.util.Date;

public class MemberImg {
	
	private int memberImgNo;
	private String memberImgPath;
	private String memberImgName;
	private Date memberImgDt;
	private String memberImgOriginal;
	
	
	private int memberNo;


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


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	@Override
	public String toString() {
		return "MemberImg [memberImgNo=" + memberImgNo + ", memberImgPath=" + memberImgPath + ", memberImgName="
				+ memberImgName + ", memberImgDt=" + memberImgDt + ", memberImgOriginal=" + memberImgOriginal
				+ ", memberNo=" + memberNo + "]";
	}		
	
	
	
	
}
