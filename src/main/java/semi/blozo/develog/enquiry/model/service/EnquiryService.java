package semi.blozo.develog.enquiry.model.service;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.common.XSS;
import semi.blozo.develog.enquiry.model.dao.EnquriyDAO;
import semi.blozo.develog.enquiry.model.vo.Enquiry;
import semi.blozo.develog.enquiry.model.vo.Pagination;


public class EnquiryService {
	private EnquriyDAO dao = new EnquriyDAO();

	
	/** 페이징 처리용 객체 생성
	 * @param cp
	 * @return pagination
	 * @throws Exception
	 */
	public Pagination getPagination(int cp , int memberNo)throws Exception {
		// 전체 게시글 수 필요 -> Connection 얻어오기 DBCP 에서~
		Connection conn = getConnection();
		// 전체 게시글 조회 DAO 호출
		int listCount =  dao.getListCount(conn , memberNo);
		
			
		close(conn);
		
		
		
		return new Pagination(listCount,cp);
	}

	
	
	/** 문의 목록 조회
	 * @param pagination
	 * @return enquriyList
	 * @throws Exception
	 */
	public List<Enquiry> selectEnquiryList(Pagination pagination , int memberNo)throws Exception {
		Connection conn = getConnection();
		List<Enquiry> enquriyList = dao.selectEnquiryList(pagination, memberNo , conn);
		
		close(conn);
		return enquriyList;
	}



	/** 문의사항 상세조회
	 * @param enquiryNo
	 * @return enquiry
	 * @throws Exception
	 */
	public Enquiry selectEnquiry(int enquiryNo)throws Exception{
		Connection conn = getConnection();
		Enquiry enquiry = dao.selectEnquiry(enquiryNo , conn);
		
		close(conn);
		
		
		return enquiry;
	}



	/** 문의사항 등록
	 * @param enquiryTitle
	 * @param enquiryContent
	 * @return result
	 */
	public int insertEnquiry(String enquiryTitle, String enquiryContent , int memberNo)throws Exception {
		Connection conn = getConnection();
		enquiryTitle = XSS.replaceParameter(enquiryTitle);
		enquiryTitle = enquiryTitle.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		enquiryContent = XSS.replaceParameter(enquiryContent);
		enquiryContent = enquiryContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		
		int result = dao.insertEnquiry(enquiryTitle , enquiryContent , memberNo,conn);
		
		if(result>0) { 
			
			commit(conn);}
		else {		   rollback(conn);}
		
		
		close(conn);
		return result;
	}



}
