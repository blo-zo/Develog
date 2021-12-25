<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${post.postTitle}</title>
	<!-- Bootstrap5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/post.css">
	
</head>
	
	<!-- header include -->
	<jsp:include page="blogHeader.jsp"/>
	<%-- include할 jsp 파일 경로 작성 --%>
	
<!-- 카테고리 오프캔버스 -->
  <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbar">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="category-menu-title" style="font-weight: bold; font-size: 30px; cursor:pointer;" onclick="location.href='${contextPath}/main'">
        Develog
      </h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="list-group category-list">
          <li class="list-group-item category">전체 포스트</li>
          <li class="list-group-item category">카테고리 1</li>
          <li class="list-group-item category">카테고리 2</li>
          <li class="list-group-item category">카테고리 3</li>
          <li class="list-group-item category">카테고리 4</li>
        </ul>
        
        <c:if test="${post.memberNo == loginMember.memberNo}">
        
	        <div id="controller-menu" style="text-align: right; margin: 20px; margin-top: 50px;">
	
	          <div class="category-control">
	
	            <button class="category-control-btn" id="add-category-btn">
	              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6 13h-5v5h-2v-5h-5v-2h5v-5h2v5h5v2z"/></svg>
	            </button>
	            <label for="add-category-btn" style="cursor: pointer;">카테고리 추가</label>
	
	          </div>
	
	          <div class="category-control">
	          
	            <button class="category-control-btn" id="remove-category-btn">
	              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6 13h-12v-2h12v2z"/></svg>
	            </button>
	            <label for="remove-category-btn" style="cursor: pointer;">카테고리 삭제</label>
	          
	          </div>
	
	        </div>
        
        </c:if>
        
        
    </div>  
  </div>
	
	
	  <!-- 본문 -->
	  <main style="width: 100%;">
	
	    <div class="" style="width:1200px;  display: flex; margin: auto; margin-top: 150px;" >
	      
	      <!-- 왼쪽 사이드 공간 (빈칸) -->
	      <div class="blog-left-side-area"></div>
	      
	      <!-- 포스트 부분 (게시글) -->
	      <div class="blog-post-content">
	        
	        <!-- 포스트 상단 (제목 등) -->
	        <div class="post-header">
	        
	          <div class="post-title">${post.postTitle}</div>
	          
	          <div style="display: flex; justify-content: space-between; align-items: center;">
	            <div class="post-menu">
	            
					<table style="margin: 5px;">
		                <tr>
		                  <td colspan="2">
		                    By.<a href="${contextPath}/blog/${post.memberName}"><span id="post-author">${post.memberName}</span></a> 
		                  </td>
		                </tr>
		                <tr>
		                  <td>작성일</td>
		                  <td>${post.createDate}</td>
		                </tr>
		                <tr>
		                  <c:if test="${post.createDate != post.modifyDate }">
			                  <td>마지막 수정일</td>
			                  <td>${post.modifyDate}</td>
		                  </c:if>
		                </tr>
	                </table>

	            </div>
	            <c:choose>
	            	<c:when test="${loginMember.memberNo == post.memberNo}">
			            <div class="post-menu2">
			              	<span onclick="updateForm();">수정</span> /
		              		<span onclick="deletePost();">삭제</span>
			            </div>
	            	</c:when>
	            	
	            	<c:otherwise>
			            <div class="post-menu2">
			            	<span onclick="reportPost(${post.postNo})">신고하기</span>
		            	</div>
	            	</c:otherwise>
	            </c:choose>
            	
	          </div>
	
	        </div>
	
	        <!-- 포스트 중간(게시글 내용) -->
	        <div class="post-body" style="min-height: 200px; word-break:break-all;">
				
				${post.postContent}
				
	        </div>
	
	        <!-- 게시글 하단 태그 버튼, 이전 글 다음 글 버튼 -->
	        <div class="post-body2">
	          
	          <div class="post-tags">
	            
	            <c:forEach items="${tagList}" var="tag">
	            
		            <div class="post-tag" onclick="location.href='#'">
		              <span>${tag.tagName}</span>
		            </div>
	            
	            </c:forEach>
	
	          </div>
	
	          <div class="post-prev-next-btn">
	            <div class="post-prev-btn">이전 글</div>
                <div class="goList">
	              <a href="${contextPath}/blog/${post.memberName}">목록으로</a>
                </div>
	            <div class="post-next-btn">다음 글</div>
	          </div>
	
	        </div>
	
	
	        <!-- 포스트 하단(프로필,댓글) -->
	        <div class="post-bottom">
	          
	          <!-- 게시글 프로필 영역 -->
	          <div class="post-profile-area">
	            <div class="post-profile-photo">
	              <img id="post-profile-img" src="https://via.placeholder.com/200x200" alt="">
	            </div>
	            <div class="post-profile-text">
	              <h1>${post.memberName}</h1>
	              <p>
	              	<c:choose>
	              		<c:when test="${post.intro == '한 줄 소개'}">
	              		</c:when>
	              		
	              		<c:otherwise>
			             	 ${post.intro}
	              		</c:otherwise>
	              	</c:choose>
             	 </p>
	            </div>
	          </div>
	
	
			  <!-- 댓글 영역 -->
			  <jsp:include page="reply.jsp"/>
	
	
	        </div>
	        
	        
	        
	      </div>
	
	      <!-- 오른쪽 사이드 영역 태그 목록, 목차, 좋아요, 공유 -->
	      <div class="blog-right-side-area">
	        
	
	        <!-- 사이드 태그 목록  -->
	        
	        <div id="blog-tag-list">
	          <span>태그목록</span>
	          <hr class="hr-small">
	          <div class="blog-tag-list-box">
	    
	          	<c:choose>
          		
	          		<c:when test="${!empty tagListAll}">
	          			<c:forEach items="${tagListAll}" var="tag">
	          				<c:if test="${tag.postStatusCode eq 500 }">
								<a href="#"><span>${tag.tagName}</span></a>
	          				</c:if>
						</c:forEach>
	          		</c:when>
	          		
	          		<c:otherwise>
	          			<p style="color:#323232; opacity:0.8; font-size:15px;"> 태그가 없습니다. </p>
	          		</c:otherwise>
          		
          		</c:choose>
	          </div>
	        </div>
	
	        
	        <!-- 글 목차 -->
	        <!-- 게시글의 특정 요소를 얻어서 거기로 이동하는 a태그로 만들거나 -->
	        <!-- h 태그 -->
	        <!-- 특정 요소를 찾아 스크롤을 이동시키는 JS로 사용한다. -->
	
	        <div class="favorite-share">
	
	          <div class="favorite-share-box">
	            
	            <!-- 좋아요 아이콘 -->
	            <div class="favorite-share-area">
	              <div class="favorite-share-icon" id="like-btn">
					<c:choose>
						
						<c:when test="${!empty likeYN}">
							<img class="liked" src="${contextPath}/resources/images/KYJ/filledHeart.svg">
						</c:when>
						
						<c:otherwise>
							<img src="${contextPath}/resources/images/KYJ/emptyHeart.svg">
						</c:otherwise>
							              
					</c:choose>
	              </div>
	              
	            </div>
	
	            <!-- 좋아요 수 -->
	            <div class="favorite-share-area">
	              <span class="favorite-count">${post.favoriteCount}</span>
	            </div>
	
	            <!-- 공유하기 아이콘 -->
	            <div class="favorite-share-area">
	              <div class="favorite-share-icon" id="share-btn">
	                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M5 7c2.761 0 5 2.239 5 5s-2.239 5-5 5-5-2.239-5-5 2.239-5 5-5zm11.122 12.065c-.073.301-.122.611-.122.935 0 2.209 1.791 4 4 4s4-1.791 4-4-1.791-4-4-4c-1.165 0-2.204.506-2.935 1.301l-5.488-2.927c-.23.636-.549 1.229-.943 1.764l5.488 2.927zm7.878-15.065c0-2.209-1.791-4-4-4s-4 1.791-4 4c0 .324.049.634.122.935l-5.488 2.927c.395.535.713 1.127.943 1.764l5.488-2.927c.731.795 1.77 1.301 2.935 1.301 2.209 0 4-1.791 4-4z"/></svg>
	              </div>
	            </div>
	          
	          </div>
	
	          
	          <div class="post-index">
	  
	            <p style="font-weight: bold;">목차</p>
	            
	            <a href="#">
	              <p class="post-index-text">1. 가위 바위 보 게임</p>
	            </a>
	  
	            <a href="#">
	              <p class="post-index-text">2. 다른 글자 찾기 게임</p>
	            </a>
	  
	            <a href="#">
	              <p class="post-index-text">3. 짝 맞추기 게임</p>
	            </a>
	            
	            <a href="#">
	              <p class="post-index-text">4. 메모리 게임</p>
	            </a>
	  
	          </div>
	
	        </div>
	
	
	      </div>
	
	    </div>
	
	
	    <!-- 추천 게시글 -->
	
	    <!-- 높이 문제 해결해야함 -->
	    <div class="relative-post-box" style="width: 1200px; height: 600px; margin: auto; margin-top: 50px;">
	
	      <div style="font-size: 50px; font-weight: bold; text-align: center; margin-bottom: 50px;">추천 게시글</div>
	
	      <!-- 추천 게시글 카드 -->
	      <div class="" style="width: 100%; min-height: 400px;">
	      
	        <section class="card-bord">
	          <div class="wrapper-card">
	            <a href="" class="wrapper-a">
	              <div class="card-small">
	
	                <div class="image"><img src="https://via.placeholder.com/280x150" id="img-size"></div>  
	                    
	                <div class="title-box">
	                  <p  class="title">제목입니다</p>
	                </div>  
	
	                <div class="user-div sub-div">
	                  <span class="by_name">by <b>Author</b></span>
	                  <div class="likes">
	                    <svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path></svg>
	                    n
	                  </div>
	                </div>
	                          
	              </div>
	            </a>
	          </div>
	        </section>
	
	
	        <section class="card-bord">
	          <div class="wrapper-card">
	            <a href="" class="wrapper-a">
	              <div class="card-small">
	
	                <div class="image"><img src="https://via.placeholder.com/280x150" id="img-size"></div>  
	                    
	                <div class="title-box">
	                  <p  class="title">제목입니다</p>
	                </div>  
	
	                <div class="user-div sub-div">
	                  <span class="by_name">by <b>Author</b></span>
	                  <div class="likes">
	                    <svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path></svg>
	                    n
	                  </div>
	                </div>
	                          
	              </div>
	            </a>
	          </div>
	        </section>
	
	
	        <section class="card-bord">
	          <div class="wrapper-card">
	            <a href="" class="wrapper-a">
	              <div class="card-small">
	
	                <div class="image"><img src="https://via.placeholder.com/280x150" id="img-size"></div>  
	                    
	                <div class="title-box">
	                  <p  class="title">제목입니다</p>
	                </div>  
	
	                <div class="user-div sub-div">
	                  <span class="by_name">by <b>Author</b></span>
	                  <div class="likes">
	                    <svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path></svg>
	                    n
	                  </div>
	                </div>
	                          
	              </div>
	            </a>
	          </div>
	        </section>
	
	        <section class="card-bord">
	          <div class="wrapper-card">
	            <a href="" class="wrapper-a">
	              <div class="card-small">
	
	                <div class="image"><img src="https://via.placeholder.com/280x150" id="img-size"></div>  
	                    
	                <div class="title-box">
	                  <p  class="title">제목입니다</p>
	                </div>  
	
	                <div class="user-div sub-div">
	                  <span class="by_name">by <b>Author</b></span>
	                  <div class="likes">
	                    <svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path></svg>
	                    
	                  </div>
	                </div>
	                          
	              </div>
	            </a>
	          </div>
	        </section>
	
	      
	      </div>
	
	      <button class="" id="more-post-btn" style="border: none; border-radius: 4px; background-color: #EEEEEE; width: 100px; height: 40px; display: block; margin: auto; margin-top: 50px;">더 보기 + </button>
	
	    </div>
	
	
	
	  </main>
	  
	  
	  <!-- ********************* 신고하기 모달 ***************************** -->
	  
	  <div class="modal" id="reportPostModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      	<div class="modal-dialog modal-lg">
       	  <div class="modal-content">
            <div class="modal-header">
               <span style="font-size: 50px; font-weight: bold;"><img style="width:50px; height:50px; margin-right:5px;" src="${contextPath}/resources/images/KYJ/reportIcon.png"> 신고하기</span>
               <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
	            
            <div class="modal-body">
               <section id="modal-section">
                 <div>
                    <div class="content" input-modal>&nbsp;신고내용</div>
                    <div class="content">
                       <div class="input-modal">
                              <textarea name="" id="" cols="30" rows="10">무야호!</textarea>
                          </div>
                    </div>
                 </div>
               </section>
            </div>
          </div>
        </div>
 	  </div>
 	  
 	  
	  <div class="modal" id="reportReplyModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      	<div class="modal-dialog modal-lg">
       	  <div class="modal-content">
            <div class="modal-header">
               <span style="font-size: 50px; font-weight: bold;"><img style="width:50px; height:50px; margin-right:5px;" src="${contextPath}/resources/images/KYJ/reportIcon.png"> 신고하기</span>
               <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
	            
            <div class="modal-body">
               <section id="modal-section">
                 <div>
                    <div class="content" input-modal>&nbsp;신고내용</div>
                    <div class="content">
                     	<div class="input-modal">
                            <textarea name="reportReplyContent" class="reportReplyContent" cols="30" rows="10">무야호!</textarea>
                     	</div>
						<button class="btn btn-primary">제출</button>
                    </div>
                 </div>
               </section>
            </div>
          </div>
        </div>
 	  </div>
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

		<%-- 수정, 삭제에 사용할 pno , cp 파라미터 --%>
		<form action="#" method="post" name="requestForm" >
		<input type="hidden" name = "cp" value="${param.cp }">
		<input type="hidden" name = "pno" value="${param.pno}">
		<input type="hidden" name = "memberName" value="${post.memberName}">
		</form>
		
<script>

// 전역 변수로 댓글 관련 기능에 사용될 변수를 미리 선언
// -> 이 때 JSP에서만 사용 가능한 EL 값을 전역 변수로 선언하면
//    아래 쪽에 추가된 js파일에서 사용 가능

const contextPath = "${contextPath}";

// 로그인한 회원의 회원 번호, 비로그인 시 "" (빈문자열) (따옴표 안붙이면 아예 빈 공간이라 오류 발생)
const loginMemberNo = "${loginMember.memberNo}";

// 포스트 작성자의 회원번호
const postMemberNo = "${post.memberNo}";

// 현재 게시글 번호
const postNo = "${post.postNo}";

// 현재 게시글 작성자명
const memberName = "${post.memberName}";

// 현재 블로그 번호
const blogNo = "${loginMember.blogNo}";


// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
let beforeReplyRow;

</script>



  <!-- footer include -->
	<jsp:include page="blogFooter.jsp"/>

  <script src="${contextPath}/resources/js/post.js"></script>

</body>
</html>