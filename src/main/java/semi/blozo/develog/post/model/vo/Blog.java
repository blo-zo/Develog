package semi.blozo.develog.post.model.vo;

import java.util.List;

public class Blog {

	
	// 블로그에서 필요한 것
	// 카테고리 , 블로그 주인 정보, 블로그가 가지고 있는 태그, SNS
	
	private int blogNo;
	private String blogName;
	
	private int memberNo;
	private String memberName;
	private String intro;
	
	private int categoryCode;
	private String categoryName;
	
	private int tagCode;
	private String tagName;
	
	// sns
	private int memberSnsNo; // 회원 소셜번호
	private String snsEmail;
	private String snsGit;
	private String snsTwitt;
	private String snsFbook;
	private String snsHome;
	
	// 프로필 이미지
	private MemberImage profileImg;	// 프로필 이미지
	private List<PostCategory> categoryList;
	
	
//	private List<SNS> memberSns; // SNS VO 만들고 리스트로 얻어오기?
	
	public Blog() {	}

	
	
	
	
	public MemberImage getProfileImg() {
		return profileImg;
	}





	public void setProfileImg(MemberImage profileImg) {
		this.profileImg = profileImg;
	}





	public List<PostCategory> getCategoryList() {
		return categoryList;
	}





	public void setCategoryList(List<PostCategory> categoryList) {
		this.categoryList = categoryList;
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




	public int getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getTagCode() {
		return tagCode;
	}

	public void setTagCode(int tagCode) {
		this.tagCode = tagCode;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}





	@Override
	public String toString() {
		return "Blog [blogNo=" + blogNo + ", blogName=" + blogName + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", intro=" + intro + ", categoryCode=" + categoryCode + ", categoryName=" + categoryName
				+ ", tagCode=" + tagCode + ", tagName=" + tagName + ", memberSnsNo=" + memberSnsNo + ", snsEmail="
				+ snsEmail + ", snsGit=" + snsGit + ", snsTwitt=" + snsTwitt + ", snsFbook=" + snsFbook + ", snsHome="
				+ snsHome + ", profileImg=" + profileImg + ", categoryList=" + categoryList + "]";
	}





	
	
}
