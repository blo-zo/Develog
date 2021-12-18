package edu.kh.semi.post.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.post.model.dao.PostDAO;
import edu.kh.semi.post.model.vo.Post;
import edu.kh.semi.post.model.vo.PostImage;
import edu.kh.semi.post.model.vo.PostPagination;

public class PostService {
	
	
	private PostDAO dao = new PostDAO();

	
	/** 블로그 페이지 처리용 객체
	 * @param cp
	 * @param blogTitle
	 * @return PostPagination
	 * @throws Exception
	 */
	public PostPagination getPostPagination(int cp, String blogTitle) throws Exception{
		
		Connection conn = getConnection();
		
		int blogListCount = dao.getBlogListCount(conn, blogTitle);
		
		close(conn);
		
		return new PostPagination(blogListCount, cp);
	}


	/** 블로그 포스트 목록 조회
	 * @param blogPostPagination
	 * @param blogTitle 
	 * @return postList
	 * @throws Exception
	 */
	public List<Post> selectBlogPostList(PostPagination blogPostPagination, String blogTitle) throws Exception{

		Connection conn = getConnection();
		List<Post> postList = dao.selectBlogPostList(blogPostPagination, blogTitle, conn);
		
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
		
		// 포스트 이미지 조회
		
		
		// 조회수
		if(post != null /* && post.getMemberNo() != memberNo */) {
			
			int result = dao.plusReadCount(postNo, conn);
			if(result > 0) {
				commit(conn);
				post.setReadCount(post.getReadCount() + 1);
			}else rollback(conn);
			
		}
		
		close(conn);
		
		return post;
	}

	
	

	
	
	
	
	
}
