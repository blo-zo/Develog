package semi.blozo.develog.post.model.dao;

import static semi.blozo.develog.common.JDBCTemplate2.*;

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
			= ReplyDAO.class.getResource("/semi/blozo/develog/sql/reply-query.xml").getPath();     
			// -> SQL이 작성된 XML 파일의 경로
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * @param postNo
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<PostReply> selectReplyList(int postNo, Connection conn) throws Exception{

		List<PostReply> rList = new ArrayList<PostReply>();
		
		try {
			
			String sql = prop.getProperty("selectReplyList");
			
			
		}finally {
			
		}
		
		return rList;
	}
	
	
	
	
}
