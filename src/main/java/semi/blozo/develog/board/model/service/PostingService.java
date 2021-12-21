package semi.blozo.develog.board.model.service;


import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;

import semi.blozo.develog.board.model.dao.PostingDAO;
import semi.blozo.develog.board.model.vo.Category;
import semi.blozo.develog.board.model.vo.PostImageVO;
import semi.blozo.develog.board.model.vo.PostVO;
import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.common.XSS;

public class PostingService {
	
	private PostingDAO dao = new PostingDAO();

	/** 게시글 삽입 ( post + tagList)
	 * @param postVO
	 * @param tagList 
	 * @return result
	 * @throws Exception
	 */
	public int insertPost(PostVO postVO, List<TagVO> tagVOList) throws Exception{
		Connection conn = getConnection();
		
		// 다음 게시글 번호를 미리 지정
		int postNo = dao.nextPostNo(conn);
		postVO.setPostNo(postNo);
		
		
		// 2-1) XSS 방지 처리
		postVO.setPostTitle(XSS.replaceParameter( postVO.getPostTitle()) );
		postVO.setPostContent(XSS.replaceParameter( postVO.getPostContent()) );
		
//		tagList.setTagName(XSS.replaceParameter( tagList.getTagName()) );
	
		
		// 2-2) 개행문자 -> <br> 태그로 변경
		String content = postVO.getPostContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		postVO.setPostContent(content); 
				
		// 2-3) 2-1,2-2를 거친 게시글 삽입 진행
		int result = dao.insertPost(postVO, conn);
		
		if(result > 0) {
		
			Boolean flag = true;
		   
		   for(TagVO tagVO : tagVOList) {
	        	 
			   result = dao.insertTag(tagVO.getTagName(), postVO.getPostNo(), conn);
	        	 
			   if(result == 0) {
				   rollback(conn);
				   
				   flag = false;
	               break;
			   }
		   }
		   if (flag) {
			   commit(conn);
			   result = postNo;
		   }
	   }

		
		
//		if(result>0) {
//			commit(conn);		
//			result = postNo;
//		} else {
//			rollback(conn);
//		}
				
		return result;
	}

	
	
	
	/** 카테고리 조회
	 * @return
	 * @throws Exception
	 */
	public List<Category> selectCategory() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
