package edu.kh.semi.post.model.vo;

public class PostImage {
	
	private int postImgNo;
	private String postImgPath;
	private String postImgName;
	private String postImgOriginal;
	private String postImgLevel;
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


	public String getPostImgOriginal() {
		return postImgOriginal;
	}


	public void setPostImgOriginal(String postImgOriginal) {
		this.postImgOriginal = postImgOriginal;
	}


	public String getPostImgLevel() {
		return postImgLevel;
	}


	public void setPostImgLevel(String postImgLevel) {
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
				+ ", postImgOriginal=" + postImgOriginal + ", postImgLevel=" + postImgLevel + ", postNo=" + postNo
				+ "]";
	}
	
	
	
}
