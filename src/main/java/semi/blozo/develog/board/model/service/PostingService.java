package semi.blozo.develog.board.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;

import semi.blozo.develog.board.model.dao.PostingDAO;
import semi.blozo.develog.board.model.vo.Category;
import semi.blozo.develog.board.model.vo.ThumbImgVO;
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
		
		// 2-2) 개행문자 -> <br> 태그로 변경
		String content = postVO.getPostContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		postVO.setPostContent(content); 
				
		// 2-3) 2-1,2-2를 거친 게시글 삽입 진행
		int result = dao.insertPost(postVO, conn);
		System.out.println("post result : " + result);
		System.out.println("post postNo : " + postNo);
		
		
		
		if(result > 0) {
		
			Boolean flag = true;
		   
		   for(TagVO tagVO : tagVOList) {
			   tagVO.setTagName(XSS.replaceParameter( tagVO.getTagName()) );  
			   tagVO.setPostNo(postNo);
			   System.out.println("tagVO : " +tagVO);
			   result = dao.insertTag(tagVO, conn);
	        	 
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
				
		return result;
	}

	
	
	
	/** 카테고리 조회
	 * @param blogNo 
	 * @return category
	 * @throws Exception
	 */
	public List<Category> selectCategory(int blogNo) throws Exception{
		Connection conn = getConnection();
		
		List<Category> category = dao.selectCategory(blogNo, conn);
		
		close(conn);
		
		return category;
	}

	
}
