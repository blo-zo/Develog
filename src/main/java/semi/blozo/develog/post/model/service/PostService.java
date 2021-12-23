package semi.blozo.develog.post.model.service;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import semi.blozo.develog.post.model.dao.PostDAO;
import semi.blozo.develog.post.model.vo.Blog;
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
		
//		for(Post temp : postList) {
//			
//			List<PostImage> imgList = dao.selectBlogPostImageList(temp.getPostNo(), conn);
//			temp.setPostImgList(imgList);
//			
//		}
		
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
		
		// 포스트 이미지 조회
//		List<PostImage> postImgList = dao.selectPostImageList(postNo, conn);
//		
//		post.setPostImgList(postImgList);
		
		// 조회수
		if(post != null && post.getMemberNo() != memberNo ) {
			
			int result = dao.plusReadCount(postNo, conn);
			if(result > 0) {
				commit(conn);
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
		
		// 이미지 정보 조회
		List<PostImage> postImgList = dao.selectPostImageList(postNo, conn);
		// 이미지를 post에 추가
		post.setPostImgList(postImgList);
		
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
	
	
	



	
	

	
	
	
	
	
}
