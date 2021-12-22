package semi.blozo.develog.post.model.vo;

import java.util.List;

public class Post {
	
	private int postNo;
	private String postTitle;
	private String postContent;
	
	private String createDate;
	private String modifyDate;
	
	private int readCount;			// 조회 수
	private int reportCount;		// 신고 수
	private int favoriteCount;		// 좋아요 수
	// 변수명 이야기 해보고 likeCount로 변경하기
	
	private int blogNo;			// 블로그 번호
	private int categoryCode;		// 카테고리 코드
	private int postStatusCode;
	
	private int memberNo;
	private String memberName;
	private String blogTitle;
	private String intro;	// 한 줄 소개
	private String categoryName;
	private String postStatusName;
	
	private List<PostImage> postImgList;
	
	public Post() { 	}

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

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
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

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPostStatusName() {
		return postStatusName;
	}

	public void setPostStatusName(String postStatusName) {
		this.postStatusName = postStatusName;
	}

	public List<PostImage> getPostImgList() {
		return postImgList;
	}

	public void setPostImgList(List<PostImage> postImgList) {
		this.postImgList = postImgList;
	}

	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", postTitle=" + postTitle + ", postContent=" + postContent + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", readCount=" + readCount + ", reportCount="
				+ reportCount + ", favoriteCount=" + favoriteCount + ", blogNo=" + blogNo + ", categoryCode="
				+ categoryCode + ", postStatusCode=" + postStatusCode + ", memberNo=" + memberNo + ", memberName="
				+ memberName + ", blogTitle=" + blogTitle + ", intro=" + intro + ", categoryName=" + categoryName
				+ ", postStatusName=" + postStatusName + ", postImgList=" + postImgList + "]";
	}

	
	
	
}
