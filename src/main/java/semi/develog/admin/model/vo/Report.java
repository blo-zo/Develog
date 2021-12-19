package semi.develog.admin.model.vo;

public class Report {
	private int reportNo;
	private String reportContent;
	private String createDate;
	private int targetNo;
	private int memberNo;
	private int reportStatusCode;
	private String reportType;
	
	// 신고 상태 테이블
	private String reportStatusName;
	public String getReportStatusName() {
		return reportStatusName;
	}
	public void setReportStatusName(String reportStatusName) {
		this.reportStatusName = reportStatusName;
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
	public int getTargetNo() {
		return targetNo;
	}
	public void setTargetNo(int targetNo) {
		this.targetNo = targetNo;
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
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	
	
	
	
}
