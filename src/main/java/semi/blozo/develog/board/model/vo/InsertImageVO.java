package semi.blozo.develog.board.model.vo;

public class InsertImageVO {
	private int imgNo;
	private String imgPath;
	private String imgName;
	private String imgOriginal;
	private int imgLevel;
	private int postNo;

	
	public InsertImageVO() {}


	public int getImgNo() {
		return imgNo;
	}


	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}


	public String getImgPath() {
		return imgPath;
	}


	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}


	public String getImgName() {
		return imgName;
	}


	public void setImgName(String imgName) {
		this.imgName = imgName;
	}


	public String getImgOriginal() {
		return imgOriginal;
	}


	public void setImgOriginal(String imgOriginal) {
		this.imgOriginal = imgOriginal;
	}


	public int getImgLevel() {
		return imgLevel;
	}


	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}


	public int getPostNo() {
		return postNo;
	}


	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}


	@Override
	public String toString() {
		return "InsertImageVO [imgNo=" + imgNo + ", imgPath=" + imgPath + ", imgName=" + imgName + ", imgOriginal="
				+ imgOriginal + ", imgLevel=" + imgLevel + ", postNo=" + postNo + "]";
	}
	
	
	
}
