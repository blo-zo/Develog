package semi.blozo.develog.board.model.vo;

public class TagVO {
	private int tagCode;
	private String tagName;
	private int postNo;
	
	
	public TagVO() {}


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
		return "TagVO [tagCode=" + tagCode + ", tagName=" + tagName + ", postNo=" + postNo + "]";
	}

	
	
 	
}
