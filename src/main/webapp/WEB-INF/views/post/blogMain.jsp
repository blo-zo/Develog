<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${blog.blogName}</title>
	<!-- Bootstrap5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/post.css">
	<style>
		@import url('https://fonts.googleapis.com/css2?family=Titillium+Web:ital,wght@0,200;0,300;0,400;0,600;0,700;0,900;1,200;1,300;1,400;1,600;1,700&display=swap');
	</style>
</head>

	<!-- header include -->
	<jsp:include page="blogHeader.jsp"/>
	<%-- include할 jsp 파일 경로 작성 --%>
	
	<!-- 카테고리 오프캔버스 -->
  <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbar">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="category-menu-title" style="color:#323232; font-family: 'Titillium Web', sans-serif; font-weight: bold; font-size: 30px; cursor:pointer;" onclick="location.href='${contextPath}/main'">
        Develog
      </h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="list-group" >
          <li class="list-group-item category" onclick="location.href='${contextPath}/blog/${blog.memberName}'">전체 포스트</li>
        </ul>
        
        <ul class="list-group category-list list-box">
        
        	<c:forEach items="${categoryList}" var="category">
        		
        		<c:if test="${category.categoryName != '없음'}">
		          <li class="list-group-item category userCategory" onclick="findOrDelete(this,${category.categoryCode},'${category.categoryName}');">${category.categoryName}</li>
        		</c:if>
        	
        	</c:forEach>
        
        </ul>
        
        <c:if test="${blog.memberNo == loginMember.memberNo}">
        
	        <div id="controller-menu" style="text-align: right; margin: 20px; margin-top: 50px;">
	
	          <div class="category-control">
	
	            <button class="category-control-btn" id="add-category-btn" onclick="addCategory()">
	              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm6 13h-5v5h-2v-5h-5v-2h5v-5h2v5h5v2z"/></svg>
	            </button>
	            <label for="add-category-btn" style="cursor: pointer;">카테고리 추가</label>
	
	          </div>
	
	          <div class="category-control">
	          
	            <button class="category-control-btn" id="remove-category-btn" onclick="deleteCategory()">
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
    <!-- 프로필 영역 -->
    <div>

      <div class="blog-profile-area">
        <div class="blog-profile-photo">
          <img id="blog-profile-img" src="${contextPath}${profileImg.memberImgPath}${profileImg.memberImgName}" alt="">
        </div>
        <div class="blog-profile-text">
          <h1>${blog.memberName}</h1>
          <p>${blog.intro}</p>
        </div>
      </div>
      

      <!-- 소셜 계정 정보 영역 -->
      <div class="social-account-area">
      	
      	<c:choose>
	  		<c:when test="${(blog.snsHome =='홈페이지 주소를 입력하세요.') || (empty blog.snsHome)}">
	  		</c:when>
      		<c:otherwise>
	  			<a href="${blog.snsHome}"><img class="social-icon" src="${contextPath}/resources/images/common/hompage.png"></a>
      		</c:otherwise>
      	</c:choose>
	  	
	  	<c:choose>
	  		<c:when test="${(blog.snsFbook =='http://www.facebook.com/') || (empty blog.snsFbook)}">
	  		</c:when>
      		<c:otherwise>
	  			<a href="${blog.snsFbook}"><img class="social-icon" src="${contextPath}/resources/images/common/facebook.png"></a>
      		</c:otherwise>
      	</c:choose>
	  	
	  	<c:choose>
	  		<c:when test="${(blog.snsTwitt =='Twitter 계정을 입력하세요.') || (empty blog.snsTwitt)}">
	  		</c:when>
      		<c:otherwise>
	  			<a href="https://twitter.com/${blog.snsTwitt}"><img class="social-icon" src="${contextPath}/resources/images/common/twitter.png"></a>
      		</c:otherwise>
      	</c:choose>
	  		
	  	<c:choose>
	  		<c:when test="${(blog.snsGit =='Github 계정을 입력하세요.') || (empty blog.snsGit)}">
	  		</c:when>
      		<c:otherwise>
	  			<a href="https://github.com/${blog.snsGit}"><img class="social-icon" src="${contextPath}/resources/images/common/github.png"></a>
      		</c:otherwise>
      	</c:choose>
	  	
	  	<c:choose>
	  		<c:when test="${(blog.snsEmail =='이메일을 입력해주세요.') || (empty blog.snsEmail)}">
	  		</c:when>
      		<c:otherwise>
	  			<a href="${blog.snsEmail}"><img class="social-icon" src="${contextPath}/resources/images/common/mail.png"></a>
      		</c:otherwise>
      	</c:choose>
	  		
          
          
          
          
          

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
  
            <!-- 블로그 검색창(ajax) -->
            
            <%-- 
            
            <form action="#">
              <input type="text" id="search-blog-post" name="search-blog-post" size="15" placeholder="블로그 게시글 검색하기" autocomplete="off">
              <button style="background-color: #fdfdfd;" id="post-search-btn">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
              </button>
            </form>
            
            --%>
            
          </div>
  		  
  		  
  		  <%-- 
  		  
          <div class="sort-post dropstart border" data-bs-toggle="dropdown" aria-expanded="false">
            <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              정렬 방식
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">날짜(오름차순)</a></li>
              <li><a class="dropdown-item" href="#">날짜(내림차순)</a></li>
            </ul>
          
          </div>
          
          --%>
  
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
  					
  						<c:choose>
  							
  							<%-- 로그인 회원과 포스트 작성자가 같고, 상태가 비공개인 경우 --%>
  							
  							<c:when test="${(post.postStatusCode == 503) && (loginMember.memberNo == post.memberNo)}">
  							
		  						<!-- 블로그 본문 내용 -->
					            <div class="card blog-post-card">
					              <a href="${contextPath}/blog/${post.memberName}/view?pno=${post.postNo}&cp=${blogPostPagination.currentPage}" class="card-link">
					  
					                <div class="card-img-top blog-post-img">
					                  <!-- 썸네일 이미지 영역 -->
					                  <img src="${contextPath}${post.postImg.postImgPath}${post.postImg.postImgName}" alt="">
					                </div>
					    
					                <div class="card-body blog-post-body border">
					                
					                  <!-- 제목 -->
					                  <h2 class="cart-title">${post.postTitle}</h2>
					                  
					                  <!-- 내용 4~5줄 영역 -->
					                  <%--
					                  <p class="card-text blog-main-text">${post.postContent}</p>
					                  --%>
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
  								
  							</c:when>
  							
  							<%-- 비공개글을 읽을 수 없는 사람 --%>
  							<c:when test="${(post.postStatusCode == 503) && (loginMember.memberNo != post.memberNo)}">
  								
  								<!-- 블로그 본문 내용 -->
					            <div class="card blog-post-card" style="opacity:0.8;">
					              <a onclick="alert('비공개 포스트 입니다.');" style="cursor:pointer;">
					  
					                <div class="card-img-top blog-post-img">
					                  <!-- 이미지 영역 -->
					                  <img src="${contextPath}/resources/images/KYJ/secretPostIMG.png" alt="">
					                </div>
					    
					                <div class="card-body blog-post-body border">
					                
					                  <!-- 제목 -->
					                  <h2 class="cart-title" style="text-align:center;">비공개 포스트입니다.</h2>
					                  
					                  <!-- 내용 4~5줄 영역 -->
					                  <%--
					                  <p class="card-text blog-main-text">${post.postContent}</p>
					                  --%>
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
  								
  							</c:when>
  							
  							<%-- 관리자 블라인드 + 본인 게시글인 경우 --%>
  							<c:when test="${post.postStatusCode == 502 && post.memberNo == loginMember.memberNo}">
  								
  								<!-- 블로그 본문 내용 -->
					            <div class="card blog-post-card">
					              <a href="${contextPath}/blog/${post.memberName}/view?pno=${post.postNo}&cp=${blogPostPagination.currentPage}" class="card-link">
					  
					                <div class="card-img-top blog-post-img">
					                  <!-- 이미지 영역 -->
					                  <img src="${contextPath}${post.postImg.postImgPath}${post.postImg.postImgName}" alt="">
					                </div>
					    
					                <div class="card-body blog-post-body border">
					                
					                  <!-- 제목 -->
					                  <h2 class="cart-title">${post.postTitle}</h2>
					                  
					                  <!-- 내용 4~5줄 영역 -->
					                 <%--
					                  <p class="card-text blog-main-text">${post.postContent}</p>
					                  --%>
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
					            
  							</c:when>
  							
  							
  							<%-- 관리자 삭제글인 경우 --%>
  							<c:when test="${post.postStatusCode == 502}">
  								
  								<!-- 블로그 본문 내용 -->
					            <div class="card blog-post-card" style="opacity:0.8;">
					              <a onclick="alert('관리자가 블라인드 처리한 포스트입니다.');" style="cursor:pointer;">
					  
					                <div class="card-img-top blog-post-img">
					                  <!-- 이미지 영역 -->
					                  <img src="${contextPath}/resources/images/KYJ/blindPostIMG.png" alt="">
					                </div>
					    
					                <div class="card-body blog-post-body border">
					                
					                  <!-- 제목 -->
					                  <h2 class="cart-title" style="color:red; text-align:center;">관리자에의해 블라인드 처리되었습니다.</h2>
					                  
					                </div>
					  
					              </a>
					            </div>
  								
  							</c:when>
  							
  							
  							<c:otherwise>
  							
  								<%-- 일반 포스트 --%>
								<!-- 블로그 본문 내용 -->
					            <div class="card blog-post-card">
					              <a href="${contextPath}/blog/${post.memberName}/view?pno=${post.postNo}&cp=${blogPostPagination.currentPage}" class="card-link">
					  
					                <div class="card-img-top blog-post-img">
					                  <!-- 이미지 영역 -->
					                  <img src="${contextPath}${post.postImg.postImgPath}${post.postImg.postImgName}" alt="">
					                </div>
					    
					                <div class="card-body blog-post-body border">
					                
					                  <!-- 제목 -->
					                  <h2 class="cart-title">${post.postTitle}</h2>
					                  
					                  <!-- 내용 4~5줄 영역 -->
					                 <%--
					                  <p class="card-text blog-main-text">${post.postContent}</p>
					                  --%>
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
  								
  							</c:otherwise>
  						
  						</c:choose>
  					
			            
			            
  					</c:forEach>
		            
  				</c:otherwise>
  			</c:choose>
        	
        	<%-- ------------------------------------------포스트 카드 부분 끝------------------------------------------------------------ --%>
        	
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

      </div>



    </div>
    
    


  </main>
  
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
const blogNo = "${blog.blogNo}";

const blogMemberName = "${blog.memberName}";



// 수정 전 댓글 요소를 저장할 변수 (댓글 수정 시 사용)
let beforeReplyRow;

</script>

  

  <!-- footer include -->
	<jsp:include page="blogFooter.jsp"/>

  <script src="${contextPath}/resources/js/post.js"></script>
  
  <%-- session 에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
  
</body>
</html>