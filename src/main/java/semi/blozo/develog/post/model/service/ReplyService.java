package semi.blozo.develog.post.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.post.model.dao.ReplyDAO;
import semi.blozo.develog.post.model.vo.PostReply;

public class ReplyService {

	
	private ReplyDAO dao = new ReplyDAO();

	
	/** 댓글 조회 Service
	 * @param postNo
	 * @return prList
	 * @throws Exception
	 */
	public List<PostReply> selectPostReplyList(int postNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<PostReply> prList = dao.selectPostReplyList(postNo, conn);
		
		close(conn);
		
		return prList;
	}
	
	
	
	
	
	
	
	
	
}
