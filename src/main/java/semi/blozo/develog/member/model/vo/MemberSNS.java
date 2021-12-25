package semi.blozo.develog.member.model.vo;

public class MemberSNS {
	 // ghldnj
	private int memberSnsNo; // 회원 소셜번호
	private int memberNo; // 회원 번호
	private String snsEmail;
	private String snsGit;
	private String snsTwitt;
	private String snsFbook;
	private String snsHome;
	
	public MemberSNS(){}

	public int getMemberSnsNo() {
		return memberSnsNo;
	}

	public void setMemberSnsNo(int memberSnsNo) {
		this.memberSnsNo = memberSnsNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
		return "MemberSNS [memberSnsNo=" + memberSnsNo + ", memberNo=" + memberNo + ", snsEmail=" + snsEmail
				+ ", snsGit=" + snsGit + ", snsTwitt=" + snsTwitt + ", snsFbook=" + snsFbook + ", snsHome=" + snsHome
				+ "]";
	}

	
	
	
}
