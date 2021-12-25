package semi.blozo.develog.board.model.vo;

public class TagVO {
	private int tagCode;
	private String tagName;
	private int postNo;
	private int postStatusCode;	// 태그가 작성된 포스트의 상태 번호
	
	
	public TagVO() {}
	
	

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


	public int getPostNo() {
		return postNo;
	}


	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}



	@Override
	public String toString() {
		return "TagVO [tagCode=" + tagCode + ", tagName=" + tagName + ", postNo=" + postNo + ", postStatusCode="
				+ postStatusCode + "]";
	}



	
	
 	
}
