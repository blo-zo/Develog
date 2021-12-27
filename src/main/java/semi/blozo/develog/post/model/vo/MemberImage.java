package semi.blozo.develog.post.model.vo;

public class MemberImage {

	private int memberImgNo;			
	private String memberImgPath;
	private String memberImgName;
	private String memberImgDate;
	private String memberImgOriginal;
	private int memberNo;
	
	public MemberImage() {
		// TODO Auto-generated constructor stub
	}
	
	
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
	public String getMemberImgDate() {
		return memberImgDate;
	}
	public void setMemberImgDate(String memberImgDate) {
		this.memberImgDate = memberImgDate;
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
		return "MemberImage [memberImgNo=" + memberImgNo + ", memberImgPath=" + memberImgPath + ", memberImgName="
				+ memberImgName + ", memberImgDate=" + memberImgDate + ", memberImgOriginal=" + memberImgOriginal
				+ ", memberNo=" + memberNo + "]";
	}
	
	
	
	
}
