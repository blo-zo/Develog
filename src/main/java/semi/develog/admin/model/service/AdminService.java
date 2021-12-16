package semi.develog.admin.model.service;

import static semi.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.develog.admin.model.dao.AdminDAO;
import semi.develog.admin.model.vo.Member;
import semi.develog.admin.model.vo.Pagination;
import semi.develog.admin.model.vo.Post;
import semi.develog.admin.model.vo.Report;

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

	public List<Post> selectPost(Pagination pagination) throws Exception {
		Connection conn = getConnection();
		
		List<Post> listPost = dao.selectPost(pagination, conn);
		
		conn.close();
		
		return listPost;
	
	}
	
	public List<Report> selectReport(Pagination pagination) throws Exception {
		Connection conn = getConnection();
		
		List<Report> listReport = dao.listReport(pagination, conn);
		
		conn.close();
		
		return null;
	}

}
