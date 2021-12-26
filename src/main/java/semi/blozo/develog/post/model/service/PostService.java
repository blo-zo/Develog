package semi.blozo.develog.post.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import semi.blozo.develog.board.model.vo.PostVO;
import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.board.model.vo.ThumbImgVO;
import semi.blozo.develog.common.XSS;
import semi.blozo.develog.post.model.dao.PostDAO;
import semi.blozo.develog.post.model.vo.Blog;
import semi.blozo.develog.post.model.vo.MemberImage;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostImage;
import semi.blozo.develog.post.model.vo.PostPagination;
import semi.blozo.develog.post.model.vo.PostReply;

public class PostService {
	
	
	private PostDAO dao = new PostDAO();
	
	
	/** 전체 포스트 조회 (최신순, 트렌딩) Service
	 * @return allPostList
	 * @throws Exception
	 */
	public List<Post> selectPostListAll() throws Exception{
		
		// 최신, 트렌딩 매개변수 넣어서 위치홀더로 지정할 수 있도록
		
		Connection conn = getConnection();
				
		List<Post> allPostList = dao.selectPostListAll(conn);
		
		close(conn);
				
		return allPostList;
	}
	

	
	/** 블로그 페이지 처리용 객체
	 * @param cp
	 * @param blogTitle
	 * @return PostPagination
	 * @throws Exception
	 */
	public PostPagination getPostPagination(int cp, String memberName) throws Exception{
		
		Connection conn = getConnection();
		
		int blogListCount = dao.getBlogListCount(conn, memberName);
		
		close(conn);
		
		return new PostPagination(blogListCount, cp);
	}


	/** 블로그 포스트 목록 조회
	 * @param blogPostPagination
	 * @param blogTitle 
	 * @return postList
	 * @throws Exception
	 */
	public List<Post> selectBlogPostList(PostPagination blogPostPagination, String memberName) throws Exception{

		Connection conn = getConnection();
		List<Post> postList = dao.selectBlogPostList(blogPostPagination, memberName, conn);
		
		for(Post temp : postList) {
			
			// 썸네일 이미지 조회
			PostImage thumbImg = dao.selectThumbImg(temp.getPostNo(), conn);
			temp.setPostImg(thumbImg);
			
		}
		
		close(conn);
		return postList;
	}
	

	/** 포스트 상세 조회
	 * @param postNo
	 * @param memberNo
	 * @return post
	 * @throws Exception
	 */
	public Post selectPost(int postNo, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		// 포스트 조회하기
		Post post = dao.selectPost(postNo, conn);
		
		// 프로필 이미지도 얻어오기
		
		// 조회수
		if(post != null && post.getMemberNo() != memberNo ) {
			
			int result = dao.plusReadCount(postNo, conn);
			
			if(result > 0) {
				
				// 조회수 증가 시간 기록
				int result2 = dao.insertStaticReadCount(postNo, conn);
					
				if(result2 > 0) {
					
					commit(conn);
				}else {
					
					rollback(conn);
				}
				
				post.setReadCount(post.getReadCount() + 1);
				
			}else rollback(conn);
			
		}
		
		close(conn);
		
		return post;
	}


	/** 수정 화면으로 전환하기
	 * @param postNo
	 * @return post
	 * @throws Exception
	 */
	public Post updateView(int postNo) throws Exception{
		
		Connection conn = getConnection();
		
		Post post = dao.selectPost(postNo, conn);
		
		// 줄바꿈 다시 원래대로 돌리기
		post.setPostContent(post.getPostContent().replaceAll("<br>", "\r\n"));
		
		close(conn);
		
		return post;
		
	}


	/** 블로그 조회
	 * @param memberName
	 * @return blog
	 * @throws Exception
	 */
	public Blog selectBlog(String memberName) throws Exception{

		Connection conn = getConnection();
		Blog blog = dao.selectBlog(memberName, conn);
		close(conn);
		return blog;
	}


	/** 포스트 삭제 service
	 * @param postNo
	 * @return result
	 * @throws Exception
	 */
	public int deletePost(int postNo) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.deletePost(postNo, conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	// --------------------------- 좋아요 --------------------------------------
	
	/** 좋아요 service
	 * @param memberNo
	 * @param postNo
	 * @return likePost
	 * @throws Exception
	 */
	public int likePost(int memberNo, int postNo) throws Exception{

		Connection conn = getConnection();
		
		int likePost = 0;
		
		try {
			
			// 좋아요 증가(삽입)
			likePost = dao.likePost(postNo, memberNo, conn);
			
		}catch(SQLException e) {

			// 좋아요 취소(삭제)
			likePost = dao.likeCancel(postNo, memberNo, conn);
			
		}
		
		if(likePost > 0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return likePost;
	}



	/** 좋아요 수 조회하기
	 * @param memberNo
	 * @param postNo
	 * @return postLikeCount
	 * @throws Exception
	 */
	public int selectPostLikeCount(int memberNo, int postNo) throws Exception{

		Connection conn = getConnection();
		
		int postLikeCount = dao.selectPostLikeCount(memberNo, postNo, conn);

		close(conn);
		
		return postLikeCount;
	}



	/** 좋아요 결과 포스트에 반영하기
	 * @param postNo
	 * @param postLikeCount 
	 * @return likeCount
	 * @throws Exception
	 */
	public int setLikeCount(int postNo, int postLikeCount) throws Exception{

		Connection conn = getConnection();
		
		int likeCount = dao.setLikeCount(postNo, postLikeCount, conn);
		
		if(likeCount > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return likeCount;
	}


	
	/** 회원이 좋아한 포스트인지 확인하기
	 * @param postNo
	 * @param memberNo
	 * @return likeYN
	 * @throws Exception
	 */
	public int likedPost(int postNo, int memberNo) throws Exception{

		Connection conn = getConnection();
		
		int likeYN = dao.likedPost(postNo, memberNo, conn);
		
		close(conn);
		
		return likeYN;
	}



	/** 수정폼 전환 시 기존 태그 목록 조회
	 * @param postNo
	 * @return tagList
	 * @throws Exception
	 */
	public List<TagVO> selectTagList(int postNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<TagVO> tagList = dao.selectTagList(postNo, conn);
		
		// 태그이름 맨 뒤 X 제거
		for(int i = 0; i < tagList.size(); i++) {
			
			String tagName = tagList.get(i).getTagName();
			tagName = tagName.substring(0, tagName.length()-1);
			tagList.get(i).setTagName(tagName);
			
		}
		
		close(conn);
		
		return tagList;
	}



	/** 포스트 수정 Service
	 * @param postVO
	 * @param tagVOList
	 * @param thumbImg2
	 * @return result
	 * @throws Exception
	 */
	public int updatePost(PostVO postVO, List<TagVO> tagVOList, PostImage thumbImg) throws Exception{

		Connection conn = getConnection();
		
		// 포스트 제목 XSS 방지 (글 내용은 SummerNote 사용으로 XSS 처리 안함)
		postVO.setPostTitle(XSS.replaceParameter( postVO.getPostTitle()) );
		
		// 글 부분 수정하기
		int result = dao.updatePost(postVO, conn);
		
		int postNo = postVO.getPostNo();
		
		int finalResult = 0;
		
		if(result > 0) {	// 글 부분 수정 성공한 경우
			
			Boolean flag = true;
		   
			// 태그 수정하기
			// 너무 어렵다.. 
			// 1번 방법) 수정태그가 더 적으면? -> 삭제, 더 많으면 -> 삽입
			// 2번 방법) 한 번 싹 지웠다가 다시 삽입하기 =>  채택
			
			
			try {
				result = dao.deleteTag(postNo, conn); // 포스트에 있는 태그 모두 삭제
				
				// 기존에 태그가 없는 경우 0행 삭제함 -> 어떻게 고쳐야할까?
				
				for(TagVO tagVO : tagVOList) {
					tagVO.setTagName(XSS.replaceParameter( tagVO.getTagName()) );  
					tagVO.setPostNo(postVO.getPostNo());
					
					result = dao.insertTag(tagVO, conn);
					
					if( result != 1) { // 태그 삽입(수정) 실패 시
						rollback(conn);
						
						flag = false;
						break;
					}
				}	// 태그 새로 삽입 끝
				
				
				// 태그가 모두 잘 삽입 된 경우 썸네일 수정 시작
				if(flag) {
					
					// 썸네일 수정
					finalResult = dao.updatePostThumb(thumbImg, conn);
					// finalResult => 최종 수정 결과
					
					if(result > 0)	{	
						
						// 썸네일까지 모두 수정 완료되면 커밋!
						commit(conn);
						
						
					}
					// 썸네일 수정 실패 시
					else rollback(conn);
				}	
				
				
			}catch(SQLException e){	// 0행 혹은 여러 행 삭제가 아닌 예외가 발생한 경우
				rollback(conn);
			}
			
			
		}else {	
			
			// 글 부분 수정 실패
			rollback(conn);
			
		}
		
		close(conn);
		
		return result;
	}



	/** 수정폼 전환 시 썸네일 이미지 조회
	 * @param postNo
	 * @return thumbImg
	 * @throws Exception
	 */
	public PostImage selectThumbImg(int postNo) throws Exception{

		Connection conn = getConnection();
		
		PostImage thumbImg = dao.selectThumbImg(postNo, conn);
		
		return thumbImg;
	}



	/** 블로그 태그 조회
	 * @param blogNo
	 * @return tagList
	 * @throws Exception
	 */
	public List<TagVO> selectBlogTagList(int blogNo) throws Exception{

		Connection conn = getConnection();
		
		List<TagVO> tagList = dao.selectBlogTagList(blogNo, conn);
		
		for(int i = 0; i < tagList.size(); i++) {
			
			String tagName = tagList.get(i).getTagName();
			tagName = tagName.substring(0, tagName.length()-1);
			tagList.get(i).setTagName(tagName);
			
		}
		
		close(conn);
		
		return tagList;
	}



	/** 포스트 신고하기
	 * @param postNo
	 * @param memberNo
	 * @param reportPostContent
	 * @return result
	 * @throws Exception
	 */
	public int reportPost(int postNo, int memberNo, String reportPostContent) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.reportPost(postNo, memberNo, reportPostContent, conn);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}



	/** 프로필 이미지 조회
	 * @param blogNo
	 * @return profileImg
	 * @throws Exception
	 */
	public MemberImage selectProfImg(int blogNo) throws Exception{

		Connection conn = getConnection();
		MemberImage profileImg = dao.selectProfImg(blogNo, conn);
		close(conn);
		return profileImg;
	}
	
	
	



	
	

	
	
	
	
	
}
