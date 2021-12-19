package semi.board.model.service;


import static semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;

import semi.board.model.dao.PostingDAO;
import semi.board.model.vo.PostImageVO;
import semi.board.model.vo.PostVO;
import semi.common.XSS;

public class PostingService {
	
	private PostingDAO dao = new PostingDAO();

	public int insertPost(PostVO postVO, List<PostImageVO> imgList) throws Exception{
		Connection conn = getConnection();
		
		int postNo = dao.nextPostNo(conn);
		
		postVO.setPostNo(postNo);
		
		// 2-1) XSS 방지 처리
		postVO.setPostTitle(XSS.repalceParameter( postVO.getPostTitle()) );
		postVO.setPostContent(XSS.repalceParameter( postVO.getPostContent()) );
		
		// 2-2) 개행문자 -> <br> 태그로 변경
		String content = postVO.getPostContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		postVO.setPostContent(content); 
				
		// 2-3) 2-1,2-2를 거친 게시글 삽입 진행
		int result = dao.insertPost(postVO, conn);
		
		if(result > 0) {
			
			for(PostImageVO img : imgList) {
				
				img.setPostNo(postNo);
				
				result = dao.insertPostImage(img, conn);
				
				if(result == 0) {
					rollback(conn);
					break;
				}
			}
			
			if(result>0) {
				commit(conn);		
				result = postNo;
			} else {
				rollback(conn);
			}
			
		} else { // 게시글등록 실패시
			rollback(conn); 
		
		}  
		
				
		return postNo;
	}


	
	
	
}
