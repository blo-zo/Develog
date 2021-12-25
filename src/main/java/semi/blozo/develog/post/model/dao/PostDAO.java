package semi.blozo.develog.post.model.dao;

import static semi.blozo.develog.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.blozo.develog.board.model.vo.PostVO;
import semi.blozo.develog.board.model.vo.TagVO;
import semi.blozo.develog.post.model.vo.Blog;
import semi.blozo.develog.post.model.vo.Post;
import semi.blozo.develog.post.model.vo.PostImage;
import semi.blozo.develog.post.model.vo.PostPagination;

public class PostDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
	private Properties prop;

	
	public PostDAO() { // 기본 생성자
	
		// DAO 객체 생성 시 특정 위치에 있는 XML 파일을 읽어와 prop에 저장
		try {
			prop = new Properties();
			
			String filePath 
			= PostDAO.class.getResource("/semi/blozo/develog/sql/post-query2.xml").getPath();     
			// -> SQL이 작성된 XML 파일의 경로
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	/** 게시글 전체 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Post> selectPostListAll(Connection conn) throws Exception{
		
		List<Post> allPostList = new ArrayList<Post>();
		
		try {
			
			String sql = prop.getProperty("selectPostListAll");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// 값 세팅하기
				Post post = new Post();
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(rs.getString("POST_CONTENT"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setCreateDate(rs.getString("CREATE_DT"));
				post.setModifyDate(rs.getString("MODIFY_DT"));
				post.setReadCount(rs.getInt("READ_COUNT"));
				post.setFavoriteCount(rs.getInt("LIKE_COUNT"));
				post.setBlogNo(rs.getInt("BLOG_NO"));
				post.setBlogTitle(rs.getString("BLOG_TITLE"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
				post.setMemberName(rs.getString("MEMBER_NM"));
				allPostList.add(post);
			}
			
		}finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return allPostList;
	}
	

	/** 특정 블로그의 전체 게시물 수 조회
	 * @param conn
	 * @param blogTitle
	 * @return
	 * @throws Exception
	 */
	public int getBlogListCount(Connection conn, String memberName) throws Exception{

		int blogListCount = 0;
		try {
			String sql = prop.getProperty("getBlogListCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				blogListCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return blogListCount;
	}


	/** 블로그 게시글 목록 조회 DAO
	 * @param blogPostPagination
	 * @param blogTitle 
	 * @param conn
	 * @return postList
	 * @throws Exception
	 */
	public List<Post> selectBlogPostList(PostPagination blogPostPagination, String memberName, Connection conn) throws Exception{
		
		List<Post> postList = new ArrayList<Post>();
		
		try {
			
			String sql = prop.getProperty("selectBlogPostList");
			int startRow =(blogPostPagination.getCurrentPage() - 1)* blogPostPagination.getLimit() +1;
			int endRow = startRow + blogPostPagination.getLimit() -1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Post post = new Post();
				
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(rs.getString("POST_CONTENT"));
				post.setCreateDate(rs.getString("CREATE_DT"));
				post.setReadCount(rs.getInt("READ_COUNT"));
				post.setFavoriteCount(rs.getInt("LIKE_COUNT"));
				post.setBlogNo(rs.getInt("BLOG_NO"));
				post.setBlogTitle(rs.getString("BLOG_TITLE"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
				post.setMemberName(rs.getString("MEMBER_NM"));
				post.setIntro(rs.getString("INTRO"));
				post.setCategoryCode(rs.getInt("CATEGORY_CD"));
				post.setCategoryName(rs.getString("CATEGORY_NM"));
				post.setPostStatusCode(rs.getInt("POST_STATUS_CD"));
				post.setPostStatusName(rs.getString("POST_STATUS_NM"));
				
				postList.add(post);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return postList;
	}


	/** 포스트 상세 조회
	 * @param postNo
	 * @param conn
	 * @return post
	 * @throws Exception
	 */
	public Post selectPost(int postNo, Connection conn) throws Exception{

		Post post = null;
		
		try {
			
			String sql = prop.getProperty("selectPost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				post = new Post();
				
				post.setPostNo(rs.getInt("POST_NO"));
				post.setPostTitle(rs.getString("POST_TITLE"));
				post.setPostContent(rs.getString("POST_CONTENT"));
				post.setCreateDate(rs.getString("CREATE_DT"));
				post.setModifyDate(rs.getString("MODIFY_DT"));
				post.setReadCount(rs.getInt("READ_COUNT"));
				post.setFavoriteCount(rs.getInt("LIKE_COUNT"));
				post.setBlogNo(rs.getInt("BLOG_NO"));
				post.setBlogTitle(rs.getString("BLOG_TITLE"));
				post.setMemberNo(rs.getInt("MEMBER_NO"));
				post.setMemberName(rs.getString("MEMBER_NM"));
				post.setIntro(rs.getString("INTRO"));
				post.setCategoryCode(rs.getInt("CATEGORY_CD"));
				post.setCategoryName(rs.getString("CATEGORY_NM"));
				post.setPostStatusCode(rs.getInt("POST_STATUS_CD"));
				post.setPostStatusName(rs.getString("POST_STATUS_NM"));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return post;
	}


	/** 조회수 증가
	 * @param postNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int plusReadCount(int postNo, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("plusReadCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 포스트 이미지 조회 DAO
	 * @param postNo
	 * @param conn
	 * @return postImgList
	 * @throws Exception
	 */
	public List<PostImage> selectPostImageList(int postNo, Connection conn) throws Exception{
		
		List<PostImage> postImgList = new ArrayList<PostImage>();
		
		try {
			
			String sql = prop.getProperty("selectPostImageList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PostImage img = new PostImage();
				
				img.setPostImgNo(rs.getInt(1));
				img.setPostImgPath(rs.getString(2));
				img.setPostImgName(rs.getString(3));
				img.setPostImgDate(rs.getString(4));
				img.setPostImgOriginal(rs.getString(5));
				img.setPostImgLevel(rs.getInt(6));
				img.setPostNo(postNo);
				
				postImgList.add(img);
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return postImgList;
	}


	/** 블로그 조회 dao
	 * @param memberName
	 * @param conn
	 * @return blog정보
	 * @throws Exception
	 */
	public Blog selectBlog(String memberName, Connection conn) throws Exception{
		
		Blog blog = null;
		
		try {
			
			String sql = prop.getProperty("selectBlog");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				blog = new Blog();
				blog.setBlogNo(rs.getInt("BLOG_NO"));
				blog.setBlogName(rs.getString("BLOG_TITLE"));
				blog.setMemberNo(rs.getInt("MEMBER_NO"));
				blog.setMemberName(rs.getString("MEMBER_NM"));
				blog.setIntro(rs.getString("INTRO"));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return blog;
	}


	/** 포스트 삭제 DAO
	 * @param postNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int deletePost(int postNo, Connection conn) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deletePost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	
	
	// -------------------------------- 좋아요 ------------------------------------
	
	
	/** 좋아요 삽입 DAO
	 * @param postNo
	 * @param memberNo
	 * @param conn
	 * @return likePost(int)
	 * @throws Exception
	 */
	public int likePost(int postNo, int memberNo, Connection conn) throws Exception{
		
		int likePost = 0;
		
		try {
			
			String sql = prop.getProperty("likePost");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, memberNo);
			likePost = pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
			
		}
		
		return likePost;
	}
	
	
	
	/** 좋아요 취소 DAO
	 * @param postNo
	 * @param memberNo
	 * @param conn
	 * @return likePost
	 * @throws Exception
	 */
	public int likeCancel(int postNo, int memberNo, Connection conn) throws Exception{

		int likePost = 0;
		
		try {
			
			String sql = prop.getProperty("likeCancel");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, memberNo);
			likePost = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return likePost;
	}
	
	

	/** 좋아요 수 조회 DAO
	 * @param memberNo
	 * @param postNo
	 * @param conn
	 * @return postLikeCount
	 * @throws Exception
	 */
	public int selectPostLikeCount(int memberNo, int postNo, Connection conn) throws Exception{

		int postLikeCount = 0;
		
		try {
			
			String sql = prop.getProperty("selectPostLikeCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			postLikeCount = pstmt.executeUpdate();
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				postLikeCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return postLikeCount;
	}

	/** 포스트 테이블에 좋아요 반영
	 * @param postNo
	 * @param postLikeCount 
	 * @param likePost
	 * @param conn
	 * @return postLike
	 * @throws Exception
	 */
	public int setLikeCount(int postNo, int postLikeCount, Connection conn) throws Exception{
		
		int likeCount = 0;
		
		try {
			
			String sql = prop.getProperty("setLikeCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postLikeCount);
			pstmt.setInt(2, postNo);
			
			likeCount = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return likeCount;
	}

	/** 회원이 좋아한 포스트인지 확인하기
	 * @param postNo
	 * @param memberNo
	 * @param conn
	 * @return likeYN
	 * @throws Exception
	 */
	public int likedPost(int postNo, int memberNo, Connection conn) throws Exception{

		int likeYN = 0;
		
		try {
			
			String sql = prop.getProperty("likedPost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setInt(2, memberNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				likeYN = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return likeYN;
	}

	/** 수정폼 전환 시 기존 태그 조회
	 * @param postNo
	 * @param conn
	 * @return tagList
	 * @throws Exception
	 */
	public List<TagVO> selectTagList(int postNo, Connection conn) throws Exception{

		List<TagVO> tagList = new ArrayList<TagVO>();
		
		try {
			
			String sql = prop.getProperty("selectTagList");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				TagVO tag = new TagVO();
				tag.setTagCode(rs.getInt(1));
				tag.setTagName(rs.getString(2));
				tag.setPostNo(postNo);
				
				tagList.add(tag);
				
			}
			
		}finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return tagList;
	}

	
	
	/** 조회수 증가 시간 입력 DAO
	 * @param postNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int insertStaticReadCount(int postNo, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertStaticReadCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	/** 썸네일 이미지 조회하기
	 * @param postNo
	 * @param conn
	 * @return thumbImg
	 * @throws Exception
	 */
	public PostImage selectThumbImg(int postNo, Connection conn) throws Exception{

		PostImage thumbImg = null;
		
		try {
			
			String sql = prop.getProperty("selectThumbImg");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				thumbImg = new PostImage();
				
				thumbImg.setPostImgNo(rs.getInt("THUMB_IMG_NO"));
				thumbImg.setPostImgPath(rs.getString("THUMB_IMG_PATH"));
				thumbImg.setPostImgName(rs.getString("THUMB_IMG_NM"));
				thumbImg.setPostImgOriginal(rs.getString("THUMB_IMG_ORIGINAL"));
				thumbImg.setPostNo(postNo);
				
			}
			
		}finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return thumbImg;
	}
	
	
	/** 포스트 글부분 수정 DAO
	 * @param postVO
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updatePost(PostVO postVO, Connection conn) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePost");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postVO.getPostTitle());		// 제목
			pstmt.setString(2, postVO.getPostContent());	// 내용
			pstmt.setInt(3, postVO.getCategoryCode());		// 카테고리 코드
			pstmt.setInt(4, postVO.getPostStatusCode());	// 게시글 상태 코드
			pstmt.setInt(5, postVO.getPostNo());			// 게시글 번호
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	/** 태그 수정(새로 삽입하기) DAO
	 * @param tagVO
	 * @param conn
	 * @return 
	 * @throws Exception
	 */
	public int updateTag(TagVO tagVO, Connection conn) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateTag");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tagVO.getTagName());
			pstmt.setInt(2, tagVO.getPostNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 기존 태그 삭제하기
	 * @param postNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int deleteTag(int postNo, Connection conn) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteTag");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 썸네일 이미지 수정
	 * @param thumbImg
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updatePostThumb(PostImage thumbImg, Connection conn) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePostThumb");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, thumbImg.getPostImgName());
			pstmt.setString(2, thumbImg.getPostImgOriginal());
			pstmt.setInt(3, thumbImg.getPostNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	

	


	
	
	
	
	
	
	
	

	
	
	
	
}
