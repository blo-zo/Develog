<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- header include -->
	<jsp:include page="blogHeader.jsp"/>
	<%-- include할 jsp 파일 경로 작성 --%>
	
  <!-- 본문 -->
  <main style="width: 100%;">
    <!-- 프로필 영역 -->
    <div>

      <div class="blog-profile-area">
        <div class="blog-profile-photo">
          <img id="blog-profile-img" src="https://via.placeholder.com/200x200" alt="">
        </div>
        <div class="blog-profile-text">
          <h1>${param.blogMemberName}</h1>
          <p>${param.blogIntro}</p>
        </div>
      </div>

      <!-- 소셜 계정 정보 영역 -->
      <div class="social-account-area">
	  <%-- 소셜 정보 링크 삽입 c:when 사용해서 값이 있으면 보여주기 --%>
          <a href="#"><img class="social-icon" src="${contextPath}/resources/images/common/hompage.png"></a>
          <a href="#"><img class="social-icon" src="${contextPath}/resources/images/common/facebook.png"></a>
          <a href="#"><img class="social-icon" src="${contextPath}/resources/images/common/twitter.png"></a>
          <a href="#"><img class="social-icon" src="${contextPath}/resources/images/common/github.png"></a>
          <a href="#"><img class="social-icon" src="${contextPath}/resources/images/common/mail.png"></a>

      </div>

    </div>

    <hr class="hr-large">

    <div class="" style="width:1200px;  display: flex; margin: auto; margin-top: 10px;" >
    
      <!-- 블로그 왼쪽 영역 -->
      <div class="blog-left-side-area"></div>

      <!-- 블로그 중앙 영역 -->
      <div class="blog-content">
      
        <!-- 블로그 본문 헤더 -->
        <div class="blog-content-header">
          
          <div class="blog-search-statistic-box">
  
            <!-- 블로그 전체 통계 -->
            <div>
              <a href="#"><span>전체 통계 조회</span></a>
            </div>
  
  
            <!-- 블로그 검색창(ajax) -->
            
            <form action="#">
              <input type="text" id="search-blog-post" name="search-blog-post" size="15" placeholder="블로그 게시글 검색하기" autocomplete="off">
              <button style="background-color: #fdfdfd;" id="post-search-btn">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
              </button>
            </form>
          </div>
  
          <div class="sort-post dropstart border" data-bs-toggle="dropdown" aria-expanded="false">
            <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              정렬 방식
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">날짜(오름차순)</a></li>
              <li><a class="dropdown-item" href="#">날짜(내림차순)</a></li>
            </ul>
          
          </div>
  
          <div class="blog-post-label">
            <div class="blog-post-board">
                <span>게시글</span>
            </div>
  
          </div>
  
          <!-- 블로그 포스트 박스 -->
          <div class="blog-post-area">
            
  			<c:choose>
  				<c:when test="${empty postList}">
  					<div style="min-height:200px;">등록된 포스트가 없습니다.</div>
  				</c:when>
  				
  				<c:otherwise>
  				
  					<c:forEach items="${postList}" var="post">
  					
			            <!-- 블로그 본문 내용 -->
			            <div class="card blog-post-card">
			              <a href="${post.memberName}/view?pno=${post.postNo}&cp=${blogPostPagination.currentPage}" 
			              onclick="sendBlog(event, ${post.postNo}, '${post.memberName}', '${post.intro}', '${post.blogTitle}')" class="card-link">
			  
			                <div class="card-img-top blog-post-img">
			                  <!-- 이미지 영역 -->
			                  <img src="https://via.placeholder.com/800x350" alt="">
			                </div>
			    
			                <div class="card-body blog-post-body border">
			                
			                  <!-- 제목 -->
			                  <h2 class="cart-title">${post.postTitle}</h2>
			                  
			                  <!-- 내용 4~5줄 영역 -->
			                  <p class="card-text">${post.postContent}</p>
			                </div>
			    
			                <div class="card-footer blog-post-footer border">
			                  
			                  <!-- 작성자, 좋아요, 날짜 영역 -->
			                  <div class="blog-post-like">
			                    <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 24 24"><path d="M12 9.229c.234-1.12 1.547-6.229 5.382-6.229 2.22 0 4.618 1.551 4.618 5.003 0 3.907-3.627 8.47-10 12.629-6.373-4.159-10-8.722-10-12.629 0-3.484 2.369-5.005 4.577-5.005 3.923 0 5.145 5.126 5.423 6.231zm-12-1.226c0 4.068 3.06 9.481 12 14.997 8.94-5.516 12-10.929 12-14.997 0-7.962-9.648-9.028-12-3.737-2.338-5.262-12-4.27-12 3.737z"/></svg>
			                    <span>${post.favoriteCount}</span>
			                  </div>
			                  
			                  <div class="blog-post-author">
			                    by. <span>${post.memberName} </span>
			                  </div>
			                  <div class="blog-post-date">
			                    <span>${post.createDate}</span>
			                  </div>
			  
			                </div>
			  
			              </a>
			            </div>
			            
  					</c:forEach>
		            
  				</c:otherwise>
  			</c:choose>
        
    	</div>
    	
    	
    	<!-- 페이지 -->
    	<div class="pageSelector">
			<ul class="pagination" style="justify-content:center;">
				<c:if test="${blogPostPagination.startPage != 1}">
					<li><a class = "page-link" href = "${post.memberName}?cp=1">&lt;&lt;</a></li>
					<li><a class = "page-link" href = "${post.memberName}?cp=${blogPostPagination.prevPage}">&lt;</a></li>
				</c:if>
				
				<%-- 페이지네이션 번호 목록 --%>
				<c:forEach begin="${blogPostPagination.startPage}"  end="${blogPostPagination.endPage}" step="1" var="i">
					<c:choose>
						<c:when test="${i==blogPostPagination.currentPage}">
							<li><a class = "page-link" style="color: black; font-weight: bold">${i}</a></li>
						</c:when>
						
						<c:otherwise>
							<li><a class = "page-link" href = "${post.memberName}?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
					<c:if test="${blogPostPagination.endPage != blogPostPagination.nextPage}">
						<li><a class = "page-link" href = "${post.memberName}?cp=${blogPostPagination.nextPage}">&gt;</a></li>
						<li><a class = "page-link" href = "${post.memberName}?cp=${blogPostPagination.maxPage}">&gt;&gt;</a></li>
					</c:if>

			</ul>
		</div>
      	
   	   </div>
   	   
 	 </div>




      <!-- 블로그 오른쪽 영역 -->
      <div class="blog-right-side-area">
      
      
      
      
        <!-- 사이드 태그 목록  -->
        <div id="blog-tag-list">
          <span>태그 목록</span>
          <hr class="hr-small">

          <div class="blog-tag-list-box">

            <a href="#"><span>#농구</span></a>
            <a href="#"><span>#숙제</span></a>
            <a href="#"><span>#종각</span></a>
            <a href="#"><span>#개발 공부</span></a>

          </div>

        </div>

      </div>



    </div>
    
    
    <!-- 블로그 -->
	<form action="${contextPath}/blog/${post.memberName}/view" method="post" name="sendBlogInfo" >
	<input type="hidden" name = "blogMemberName" value="">
	<input type="hidden" name = "blogIntro" value="">
	<input type="hidden" name = "blogTitle" value="">
	<input type="hidden" name = "cp" value="${blogPostPagination.currentPage}">
	<input type="hidden" name = "pno" value="">
	</form>


  </main>
  
  
  <%-- 전역 변수로 이름 지정해서 블로그 주소 넘기기 --%>
  <script>
  	const postMemberName = "${post.memberName}";
    const contextPath = "${contextPath}";
  </script>

  

  <!-- footer include -->
	<jsp:include page="blogFooter.jsp"/>

  <script src="${contextPath}/resources/js/post.js"></script>
  
  <%-- session 에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
  
</body>
</html>