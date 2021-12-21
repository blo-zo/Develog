package semi.blozo.develog.board.model.vo;

public class PostImageVO {
	
	private int	postImgNo;
	private String postImgPath;
	private String postImgName;
	private String postImgOriginal;
	private int postImgLevel;
	
	private int postThumbImgNo;
	private int postThumbImgName;
	private int postNo;
	
	public PostImageVO() {}

	


	public int getPostImgNo() {
		return postImgNo;
	}




	public void setPostImgNo(int postImgNo) {
		this.postImgNo = postImgNo;
	}




	public String getPostImgPath() {
		return postImgPath;
	}




	public void setPostImgPath(String postImgPath) {
		this.postImgPath = postImgPath;
	}




	public String getPostImgName() {
		return postImgName;
	}




	public void setPostImgName(String postImgName) {
		this.postImgName = postImgName;
	}




	public String getPostImgOriginal() {
		return postImgOriginal;
	}




	public void setPostImgOriginal(String postImgOriginal) {
		this.postImgOriginal = postImgOriginal;
	}




	public int getPostImgLevel() {
		return postImgLevel;
	}




	public void setPostImgLevel(int postImgLevel) {
		this.postImgLevel = postImgLevel;
	}




	public int getPostThumbImgNo() {
		return postThumbImgNo;
	}




	public void setPostThumbImgNo(int postThumbImgNo) {
		this.postThumbImgNo = postThumbImgNo;
	}




	public int getPostThumbImgName() {
		return postThumbImgName;
	}




	public void setPostThumbImgName(int postThumbImgName) {
		this.postThumbImgName = postThumbImgName;
	}




	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}




	@Override
	public String toString() {
		return "PostImageVO [postImgNo=" + postImgNo + ", postImgPath=" + postImgPath + ", postImgName=" + postImgName
				+ ", postImgOriginal=" + postImgOriginal + ", postImgLevel=" + postImgLevel + ", postThumbImgNo="
				+ postThumbImgNo + ", postThumbImgName=" + postThumbImgName + ", postNo=" + postNo + "]";
	}
	
	
	
}
