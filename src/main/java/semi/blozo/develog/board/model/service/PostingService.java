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
	 * @param thumbimgList 
	 * @param tagList 
	 * @return result
	 * @throws Exception
	 */
	public int insertPost(PostVO postVO, List<TagVO> tagVOList, List<ThumbImgVO> imgList) throws Exception{
		Connection conn = getConnection();
		
		// 다음 게시글 번호를 미리 지정
		int postNo = dao.nextPostNo(conn);
		postVO.setPostNo(postNo);
		
		// 2-1) XSS 방지 처리
		postVO.setPostTitle(XSS.replaceParameter( postVO.getPostTitle()) );

		// 2-2) 개행문자 -> <br> 태그로 변경
//		String content = postVO.getPostContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
//		postVO.setPostContent(content); 
				
		// 2-3) 2-1,2-2를 거친 게시글 삽입 진행
		int result = dao.insertPost(postVO, conn);
		
		
		if(result > 0) {
			// 플래그 사용이유 혹시 모를 상황방지, 태그 입력중 하나가 잘못되면 못하게
			Boolean flag = true;
		   
			for(TagVO tagVO : tagVOList) {
				tagVO.setTagName(XSS.replaceParameter( tagVO.getTagName()) );  
				tagVO.setPostNo(postNo);
				
				System.out.println(tagVO);
				System.out.println("tagVO의 " +postNo);
				
				
				result = dao.insertTag(tagVO, conn);//결과가 true면 태그가 잘들어갔다
	        	 
				if(result == 0) { // 실패하면 false, for문 멈춘다
				   rollback(conn);
				   
				   flag = false;
	               break;
				}  
		    }
		    // for문이 끝나고 썸네일 과정 진행
			
			// result가 트루일 때 썸네일 넣기
			// 썸네일이 끝나고 나서 결과가 
			
			// 썸네일 이미지  for문
			if(flag) {
				for(ThumbImgVO thumbVO : imgList) {
					thumbVO.setPostNo(postNo);
					
					result = dao.insertThumb(thumbVO, conn);
		        	 
					System.out.println("thumbVO의 " +postNo);
	
					if(result == 0) { 
					   rollback(conn);
					   
					   flag = false;
		               break;
					} else {
						result = postNo; //썸네일 이미지 IF문 안에서 처리하기 
					}
			    }
			}
			
			if(result > 0) commit(conn);
			
		} else {
			rollback(conn);
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
