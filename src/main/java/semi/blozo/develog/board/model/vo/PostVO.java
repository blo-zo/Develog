package semi.blozo.develog.board.model.vo;

import java.util.List;


public class PostVO {
	private int postNo;				// 게시글 번호
	private String postTitle;		// 게시글 제목
	private String postContent;		// 게시글 내용
	
	//sql에서는 date 타입은 맞지만 string으로 작성
	private String createDate;		// 작성일
	private String modifyDate;		// 수정일 -- 처음엔 nullable
	
	private int readCount;
	private int memberNo;
	private int boardStatusCode;
	private int categoryCode;
	
	private int tagCode;		// 태그 코드 
	private String tagName;		// 태그명 
	
	// join하면서 가져온것들(화면에 필요한것들)
	private int blogNo;			// 블로그 번호
	private int categoryName;	// 카테고리 코드
	private int postStatusCode;	// post 상태 코드
	
	
	public PostVO( ) {}


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


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public int getBoardStatusCode() {
		return boardStatusCode;
	}


	public void setBoardStatusCode(int boardStatusCode) {
		this.boardStatusCode = boardStatusCode;
	}


	public int getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}


	public int getBlogNo() {
		return blogNo;
	}


	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}


	public int getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(int categoryName) {
		this.categoryName = categoryName;
	}


	public int getPostStatusCode() {
		return postStatusCode;
	}


	public void setPostStatusCode(int postStatusCode) {
		this.postStatusCode = postStatusCode;
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
		return "PostVO [postNo=" + postNo + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", readCount=" + readCount
				+ ", memberNo=" + memberNo + ", boardStatusCode=" + boardStatusCode + ", categoryCode=" + categoryCode
				+ ", tagCode=" + tagCode + ", tagName=" + tagName + ", blogNo=" + blogNo + ", categoryName="
				+ categoryName + ", postStatusCode=" + postStatusCode + "]";
	}


	
	
	
	
	
	
	
}
