package semi.blozo.develog.admin.model.vo;

public class Post {
	private int postNo;
	
	private  String postTitle;
	private  String postContent;
	private String createDate;
	private int readCount;
	private int reportCount;
	private int violationCount;
	private int likeCount;
	private String modifyDate;
	private int blogNo;
	private int categoryCode;
	private int postStatusCode;
	private String postStatusName;
	
	// POST_READ_COUNT TABLE
	private String readCountDate;
	
	// member table
	private int memberNo;
	private String memberName;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getReadCountDate() {
		return readCountDate;
	}
	public void setReadCountDate(String readCountDate) {
		this.readCountDate = readCountDate;
	}
	public String getPostStatusName() {
		return postStatusName;
	}
	public void setPostStatusName(String postStatusName) {
		this.postStatusName = postStatusName;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getReportCount() {
		return reportCount;
	}
	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}
	public int getViolationCount() {
		return violationCount;
	}
	public void setViolationCount(int violationCount) {
		this.violationCount = violationCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getPostStatusCode() {
		return postStatusCode;
	}
	public void setPostStatusCode(int postStatusCode) {
		this.postStatusCode = postStatusCode;
	}
	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", postTitle=" + postTitle + ", createDate=" + createDate + ", readCount="
				+ readCount + ", reportCount=" + reportCount + ", violationCount=" + violationCount + ", likeCount="
				+ likeCount + ", modifyDate=" + modifyDate + ", blogNo=" + blogNo + ", categoryCode=" + categoryCode
				+ ", postStatusCode=" + postStatusCode + "]";
	}

	
}

