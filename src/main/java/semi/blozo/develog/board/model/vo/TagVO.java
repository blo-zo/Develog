package semi.blozo.develog.board.model.vo;

public class TagVO {
	private int tagCode;
	private String tagName;
	
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

	@Override
	public String toString() {
		return "TagVO [tagCode=" + tagCode + ", tagName=" + tagName + "]";
	}
	
	
 	
}
