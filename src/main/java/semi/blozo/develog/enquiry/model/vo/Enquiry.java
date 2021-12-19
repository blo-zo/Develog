package semi.blozo.develog.enquiry.model.vo;

public class Enquiry {
	private int enquiryNo; // 문의 코드
	private String enquiryTitle; // 문의 제목
	private String enquiryContent; // 문의 내용
	private String createDt; // 작성일
	private String modifyDt; // 수정일
	private String parentEnquriy; // 부모문의 fk 회원번호 memberNo

	
	public Enquiry() {


	}


	


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





	public String getCreateDt() {
		return createDt;
	}





	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}





	public String getModifyDt() {
		return modifyDt;
	}





	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
	}





	public String getParentEnquriy() {
		return parentEnquriy;
	}





	public void setParentEnquriy(String parentEnquriy) {
		this.parentEnquriy = parentEnquriy;
	}





	@Override
	public String toString() {
		return "Enquiry [enquiryNo=" + enquiryNo + ", enquiryTitle=" + enquiryTitle + ", enquiryContent="
				+ enquiryContent + ", createDt=" + createDt + ", modifyDt=" + modifyDt + ", parentEnquriy="
				+ parentEnquriy + "]";
	}





	
	
}
