package semi.develog.admin.model.vo;

public class Report {
	private int reportNo;
	private String reportContent;
	private String createDate;
	private int postNo;
	private int replyNo;
	private int memberNo;
	private int reportStatusCode;
	
	
	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public Report() {
	}
	
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public int getReportStatusCode() {
		return reportStatusCode;
	}
	public void setReportStatusCode(int reportStatusCode) {
		this.reportStatusCode = reportStatusCode;
	}
	
	
	
	
}
