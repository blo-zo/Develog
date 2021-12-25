package semi.blozo.develog.post.model.vo;

public class PostImage {
	
	private int postImgNo;
	private String postImgPath;
	private String postImgName;
	private String postImgDate;
	private String postImgOriginal;
	private int postImgLevel;
	private int postNo;
	
	
	public PostImage() {	}


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


	public String getPostImgDate() {
		return postImgDate;
	}


	public void setPostImgDate(String postImgDate) {
		this.postImgDate = postImgDate;
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


	public int getPostNo() {
		return postNo;
	}


	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}


	@Override
	public String toString() {
		return "PostImage [postImgNo=" + postImgNo + ", postImgPath=" + postImgPath + ", postImgName=" + postImgName
				+ ", postImgDate=" + postImgDate + ", postImgOriginal=" + postImgOriginal + ", postImgLevel="
				+ postImgLevel + ", postNo=" + postNo + "]";
	}


	
	
	
}
