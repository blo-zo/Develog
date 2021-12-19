package semi.blozo.develog.admin.model.vo;

public class Enquiry {
	private int enquiryNo;
	private String enquiryTitle;
	private String enquiryContent;
	private String createDate;
	private String modifyDate;
	private int memberNo;
	private int parentEnquiry;
	
	
	public int getEnquiryNo() {
		return enquiryNo;
	}
	public void setEnquiryNo(int enquiryNo) {
		this.enquiryNo = enquiryNo;
	}
	public String getEnquiryTitle() {
		return enquiryTitle;
	}
	public void setEnquiryTitle(String enquiryTitle) {
		this.enquiryTitle = enquiryTitle;
	}
	public String getEnquiryContent() {
		return enquiryContent;
	}
	public void setEnquiryContent(String enquiryContent) {
		this.enquiryContent = enquiryContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getParentEnquiry() {
		return parentEnquiry;
	}
	public void setParentEnquiry(int parentEnquiry) {
		this.parentEnquiry = parentEnquiry;
	}
	
	
}
