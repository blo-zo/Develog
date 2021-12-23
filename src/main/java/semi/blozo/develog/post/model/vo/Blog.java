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
	
//	private List<SNS> memberSns; // SNS VO 만들고 리스트로 얻어오기?
	
	public Blog() {	}

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
				+ ", tagCode=" + tagCode + ", tagName=" + tagName + "]";
	}
	
	
	
}
