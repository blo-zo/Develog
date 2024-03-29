package semi.blozo.develog.post.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.blozo.develog.common.XSS;
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


	/** 댓글 입력 Service
	 * @param reply
	 * @param secretReply 
	 * @return result
	 * @throws Exception
	 */
	public int insertPostReply(PostReply reply, String secretReply) throws Exception{

		Connection conn = getConnection();
		
		// XSS 방지 처리
		reply.setReplyContent( XSS.replaceParameter(reply.getReplyContent()) );
		
		// 개행 문자 처리
		reply.setReplyContent( reply.getReplyContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>") );
		
		int result = 0;
		
		if(secretReply.equals("false")) {
			
			result = dao.insertPostReply(reply, conn);
			
		}else {
			
			result = dao.insertSecretReply(reply, conn);
			
		}
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 댓글 수정 Service
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(int replyNo, String replyContent) throws Exception{

		Connection conn = getConnection();
		
		// XSS
		replyContent = XSS.replaceParameter(replyContent);
		// 개행 문자
		replyContent = replyContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		int result = dao.updateReply(replyNo, replyContent, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 댓글 삭제
	 * @param replyNo
	 * @return
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.deleteReply(replyNo, conn);
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 댓글 신고하기
	 * @param replyNo
	 * @param memberNo
	 * @param reportReplyContent
	 * @return result
	 * @throws Exception
	 */
	public int reportReply(int replyNo, int memberNo, String reportReplyContent) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.reportReply(replyNo, memberNo, reportReplyContent, conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	
	
	
	
	
	
	
	
	
}
