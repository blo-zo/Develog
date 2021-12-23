package semi.blozo.develog.board.model.vo;

public class Category {
	private int categoryCode;
	private String categoryName;
	private int blogNo;
	
	
	public Category() {}


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
		return "Category [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", blogNo=" + blogNo + "]";
	}


	
}
