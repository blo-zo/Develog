package semi.blozo.develog.enquiry.model.service;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

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
	public Pagination getPagination(int cp)throws Exception {
		// 전체 게시글 수 필요 -> Connection 얻어오기 DBCP 에서~
		Connection conn = getConnection();
		// 전체 게시글 조회 DAO 호출
		int listCount =  dao.getListCount(conn);
		
			
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
}
