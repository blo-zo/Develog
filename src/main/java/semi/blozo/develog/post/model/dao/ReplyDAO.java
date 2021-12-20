package semi.blozo.develog.post.model.dao;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.blozo.develog.post.model.vo.PostReply;

public class ReplyDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
	private Properties prop;
	
	public ReplyDAO() {

		try {
			prop = new Properties();
			
			String filePath 
			= ReplyDAO.class.getResource("/semi/blozo/develog/sql/post-query2.xml").getPath();     
			// -> SQL이 작성된 XML 파일의 경로
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/** 포스트 댓글 조회 DAO
	 * @param postNo
	 * @param conn
	 * @return prList
	 * @throws Exception
	 */
	public List<PostReply> selectPostReplyList(int postNo, Connection conn) throws Exception{

		List<PostReply> prList = new ArrayList<PostReply>();
		
		try {
			
			String sql = prop.getProperty("selectPostReplyList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PostReply reply = new PostReply();
				
				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
	            reply.setReplyCreateDate(rs.getString("REPLY_CREATE_DT"));
	            reply.setReportCount(rs.getInt("REPORT_COUNT"));
	            reply.setPostNo(rs.getInt("POST_NO"));
	            reply.setMemberNo(rs.getInt("MEMBER_NO"));
	            reply.setMemberName(rs.getString("MEMBER_NM"));
	            reply.setReplyStatusCode(rs.getInt("REPLY_STATUS_CD"));
	            reply.setReplyStatusName(rs.getString("REPLY_STATUS_NM"));
	            
	            prList.add(reply);
				
			}
			
		}finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return prList;
	}
	
	
	
	
}
