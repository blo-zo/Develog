package semi.blozo.develog.member.model.dao;
import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import semi.blozo.develog.member.model.vo.Member;
public class MemberProfileDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public MemberProfileDAO() {
		try {
			prop = new Properties();
			String filePath
			= MemberProfileDAO.class.getResource("/semi/blozo/develog/sql/member-query.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	

	
	


	
}
