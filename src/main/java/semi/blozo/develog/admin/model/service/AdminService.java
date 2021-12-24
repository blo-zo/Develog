package semi.blozo.develog.admin.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.admin.model.dao.AdminDAO;
import semi.blozo.develog.admin.model.vo.Enquiry;
import semi.blozo.develog.admin.model.vo.Member;
import semi.blozo.develog.admin.model.vo.Pagination;
import semi.blozo.develog.admin.model.vo.Post;
import semi.blozo.develog.admin.model.vo.Report;

public class AdminService {
	
	private AdminDAO dao = new AdminDAO();
	
	public List<Member> selectMember(Pagination pagination) throws Exception {

		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectMember(pagination, conn);
		
		conn.close();
		
		return memberList;
	}

	public Pagination getPagination(int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.memberListCount(conn);
		
		conn.close();
		
		return new Pagination(listCount, cp);
	}

	public List<Post> selectPost(String searchWord, String searchTag, String orderTag, Pagination pagination) throws Exception {
		Connection conn = getConnection();
		
		List<Post> listPost = dao.selectPost(pagination, searchWord, searchTag, orderTag, conn);
		
		conn.close();
		
		return listPost;
	
	}
	
	public List<Report> selectReport(String searchWord, String searchTag, Pagination pagination) throws Exception {
		Connection conn = getConnection();
		
		List<Report> listReport = dao.selectReport(searchWord, searchTag, pagination, conn);
		
		conn.close();
		
		return listReport;
	}

	public List<Enquiry> selectEnquiry(String searchWord, String searchTag, String orderTag, Pagination pagination) throws Exception {
		Connection conn = getConnection();
		
		List<Enquiry> enquiryList = dao.selectEnquiry(searchWord, searchTag, orderTag, pagination, conn);
		
		conn.close();
		
		return enquiryList;
	}

	public int selectPostViews() throws Exception {
		Connection conn = getConnection();
		
		int cumulativeViews = dao.selecPostViews(conn);
				
		conn.close();
		
		return cumulativeViews;
	}

	public int selectMembers() throws Exception {
		Connection conn = getConnection();
		
		int cumulativeMembers = dao.selectMembers(conn);
				
		conn.close();
		
		return cumulativeMembers;
	}

	public int selectPosts() throws Exception {
		Connection conn = getConnection();
		
		int cumulativePosts = dao.selectPosts(conn);
				
		conn.close();
		
		return cumulativePosts;
	
	}

	public int selectDailyViews() throws Exception {
		Connection conn = getConnection();
		
		int dailyViews = dao.selectDailyViews(conn);
				
		conn.close();
		
		return dailyViews;
	
	
	}

	public int selectDailyMembers() throws Exception {
		Connection conn = getConnection();
		
		int dailyMembers = dao.selectDailyMembers(conn);
				
		conn.close();
		
		return dailyMembers;
	}

	public int selectDailyPosts() throws Exception {
		Connection conn = getConnection();
		
		int dailyPosts = dao.selectDailyPosts(conn);
				
		conn.close();
		
		return dailyPosts;
	}

	public Report selectDetailReport(int reportNo) throws Exception {
		Connection conn = getConnection();
		
		Report report = dao.selectDetailReport(reportNo, conn);
		
		conn.close();
	
		return report;
	}

	public Enquiry selectDetailEnquiry(int enquiryNo) throws Exception {
		Connection conn = getConnection();
		
		Enquiry enquiry = dao.selectDetailEnquiry(enquiryNo, conn);
		
		conn.close();
	
		return enquiry;
	}

	public List<Post> selectListCounts() throws Exception {
		Connection conn = getConnection();
		
		List<Post> listCounts = dao.selectListCounts(conn);
		
		conn.close();
		
		return listCounts;
	
	}

	public List<Member> selectMemberSearch(String searchWord, String searchTag, Pagination pagination) throws Exception {
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectMemberSearch(searchWord, searchTag, pagination, conn);
		
		conn.close();
		
		return memberList;
	
	}

	public int updateViolationPlus() throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateViolationPlus(conn);
		
		if(result > 0) conn.commit();
		else	       conn.rollback();
		
		return result;
		
	
	}
	
	public int updateViolationMinus() throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateViolationMinus(conn);
		
		if(result > 0) conn.commit();
		else	       conn.rollback();
		
		return result;
		
	
	}

	public Member adminLogin(String adminPw) throws Exception {

		Connection conn = getConnection();

		Member adminMember = dao.adminLogin(adminPw, conn);

		conn.close();

		return adminMember;

	}

	public int insertViolationPlus(int memberNo, String content) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.insertViolationPlus(memberNo, content, conn);
		
		if(result > 0) conn.commit();
		else		   conn.rollback();
		
		conn.close();
		
		return result;
	
	}

	public List<Report> selectViolation(int memberNo) throws Exception {

		Connection conn = getConnection();
		
		List<Report> violationList = dao.selectViolation(memberNo, conn);
		
		conn.close();
		
		return violationList;
	}

	public int deleteViolation(int violationNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.deleteViolation(violationNo, conn);
		
		// 트랜잭션 제어 깜빡했내
		if(result >0) conn.commit();
		else		  conn.rollback();
		
		conn.close();
		
		return result;
	
	}

	public Pagination getSearchPagination(String searchWord, String searchTag, int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.memberSearchListCount(searchWord, searchTag, conn);
		
		conn.close();
		
		return new Pagination(listCount, cp);
	}

	public int insertDeletePost(int postNo, String content) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updatePostStatus(postNo, conn);
		if(result >0) {
			result = dao.insertDeletePost(postNo, content, conn);			
		}
		if(result >0) conn.commit();
		else		  conn.rollback();
		
		conn.close();
		
		return result;
	
	}

	public Post selectDeletePost(int postNo) throws Exception {
		Connection conn = getConnection();
		
		Post removeContent = dao.selectDeletePost(postNo, conn);
		
		conn.close();
		
		return removeContent;
	
	
	}

	public int deletePostContent(int postNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deletePostContent(postNo, conn);
		
		if(result >0 ) {
			result = dao.updateResotrePostStatus(postNo, conn);
			if(result>0) {
				conn.commit();				
			}
		}
		else		   conn.rollback();
	
		return result;
	}

	public Pagination getPaginationPost(String searchWord, String searchTag, String orderTag, int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.postListCount(searchWord, searchTag, orderTag, conn);
		
		conn.close();
		
		return new Pagination(listCount, cp);
	}

	public int insertEnquiry(Enquiry enquiry, String content) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertEnquiry(enquiry, content, conn);
		
		if(result >0) conn.commit();
		else		  conn.rollback();
		
		conn.close();
		
		return result;
	
	}

	public Pagination getPaginationEnquiry(String searchWord, String searchTag, String orderTag, int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.enquiryListCount(searchWord, searchTag, orderTag, conn);
		
		conn.close();
		
		return new Pagination(listCount, cp);
	}

	public Pagination getPaginationReport(String searchWord, String searchTag, int cp) throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.reportListCount(searchWord, searchTag, conn);
		
		conn.close();
		
		return new Pagination(listCount, cp);
	}




}
