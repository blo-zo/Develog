package semi.blozo.develog.post.model.service;

import static semi.blozo.develog.common.JDBCTemplate2.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.post.model.dao.ReplyDAO;
import semi.blozo.develog.post.model.vo.PostReply;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();

	/**
	 * @param postNo
	 * @return
	 * @throws Exception
	 */
	public List<PostReply> selectReplyList(int postNo) throws Exception{

		Connection conn = getConnection();
		
		List<PostReply> rList = dao.selectReplyList(postNo, conn);
		
		close(conn);
		
		return rList;
	}
	
	
	
	
	
	
}
