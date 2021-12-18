package edu.kh.semi.post.model.vo;

public class Blog {

	private int blogNo;
	private String blogName;
	private int memberNo;
	
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

	@Override
	public String toString() {
		return "Blog [blogNo=" + blogNo + ", blogName=" + blogName + ", memberNo=" + memberNo + "]";
	}
	
	
}
