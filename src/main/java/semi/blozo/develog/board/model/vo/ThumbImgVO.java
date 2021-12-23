package semi.blozo.develog.board.model.vo;

public class ThumbImgVO {
	

	private int thumbImgNo;
	private String thumbImgPath;
	private String thumbImgName;
	private String thumbImgOriginal;
	private int postNo;					// 게시글 번호
	
	public ThumbImgVO() {}

	
	public int getThumbImgNo() {
		return thumbImgNo;
	}


	public void setThumbImgNo(int thumbImgNo) {
		this.thumbImgNo = thumbImgNo;
	}


	public String getThumbImgPath() {
		return thumbImgPath;
	}


	public void setThumbImgPath(String thumbImgPath) {
		this.thumbImgPath = thumbImgPath;
	}


	public String getThumbImgName() {
		return thumbImgName;
	}

	public void setThumbImgName(String thumbImgName) {
		this.thumbImgName = thumbImgName;
	}


	public String getThumbImgOriginal() {
		return thumbImgOriginal;
	}


	public void setThumbImgOriginal(String thumbImgOriginal) {
		this.thumbImgOriginal = thumbImgOriginal;
	}


	public int getPostNo() {
		return postNo;
	}


	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}



	@Override
	public String toString() {
		return "ThumbImgVO [thumbImgNo=" + thumbImgNo + ", thumbImgPath=" + thumbImgPath + ", thumbImgName="
				+ thumbImgName + ", thumbImgOriginal=" + thumbImgOriginal + ", postNo=" + postNo + "]";
	}

	
	
	
}
