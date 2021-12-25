package semi.blozo.develog.post.model.vo;

public class PostReply {
	
	private int replyNo;
	private String replyContent;  
	private String replyCreateDate;
	
	
	private int replyStatusCode;
	private String replyStatusName;
	
	private int postNo;
	private int memberNo;  
	private String memberName;
	
	
	public PostReply() {	}
	
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyCreateDate() {
		return replyCreateDate;
	}
	public void setReplyCreateDate(String replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}
	public int getReplyStatusCode() {
		return replyStatusCode;
	}
	public void setReplyStatusCode(int replyStatusCode) {
		this.replyStatusCode = replyStatusCode;
	}
	public String getReplyStatusName() {
		return replyStatusName;
	}
	public void setReplyStatusName(String replyStatusName) {
		this.replyStatusName = replyStatusName;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
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
	@Override
	public String toString() {
		return "PostReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate="
				+ replyCreateDate
				+ ", replyStatusCode=" + replyStatusCode + ", replyStatusName=" + replyStatusName + ", postNo=" + postNo
				+ ", memberNo=" + memberNo + ", memberName=" + memberName + "]";
	}
	

}
