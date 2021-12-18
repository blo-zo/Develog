package edu.kh.semi.post.model.vo;

public class PostCategory {

	private int categoryCode;
	private String categoryName;
	private int blogNo;
	
	public PostCategory() {	}

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

	public int getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}

	@Override
	public String toString() {
		return "PostCategory [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", blogNo=" + blogNo
				+ "]";
	}
	
	
	
	
}
