<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${param.blog}</title>
	<!-- Bootstrap5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/post.css">
	
	
</head>
<body>
	
  <header class="jun-header">

    <div class="px-3 py-2">
      <div class="container">

        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <!-- 카테고리 메뉴 오프캔버스 -->
          <a class="d-flex align-items-center my-2 my-lg-0 me-lg-auto" data-bs-toggle="offcanvas" href="#offcanvasNavbar" role="button" aria-controls="offcanvasNavbar">
            <svg id="category-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M24 6h-24v-4h24v4zm0 4h-24v4h24v-4zm0 8h-24v4h24v-4z"/></svg>
          </a>
          
          
          <ul class="nav col-12 col-lg-auto my-2 justify-content-center align-items-center my-md-0 text-small">

            <!-- 검색 -->
            <li data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
              <a href="#" class="nav-link">
                <svg class="bi d-block" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
              </a>
            </li>

            <!-- 글쓰기 -->
            <%-- 로그인 상태일 경우에만 글쓰기 버튼 생성 --%>
            <li>
              <a href="${contextPath}/blog/insert" class="nav-link">
                <svg class="bi d-block " xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"/></svg>
              </a>
            </li>
            
            <!-- 프로필 -->
            <%-- 내블로그 -> loginMember.blotTitle로 설정하기 memberVO에 blogTitle 변수 얻기 --%>
            <li>
              <div class="dropdown">
                <a href="#" class="nav-link d-block text-decoration-none " id="userID" data-bs-toggle="dropdown" aria-expanded="false">
                  <img src="${contextPath}/resources/images/common/profile-small.png" width="30" height="30" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="userID">
                  <li><a class="dropdown-item" href="${contextPath}/blog/sample">내 블로그</a></li>
                  <li><a class="dropdown-item" href="#">새 글 작성</a></li>
                  <li><a class="dropdown-item" href="#">임시 글</a></li>
                  <li><a class="dropdown-item" href="#">설정</a></li>
                  <li><a class="dropdown-item" href="#">1:1 문의</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item" href="#">로그 아웃</a></li>
                </ul>
              </div>
            </li>
            
          </ul>

        </div>
      </div>
    </div>

  </header>

  <!-- 검색창 오프캔버스 -->
  <div class="offcanvas offcanvas-top search-offcanvas" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel">
   
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasLabel"></h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>

    <div class="offcanvas-body search-offcanvas-body">
      <div id="search-area">
        <div>
          <form action="../searchPage/search(member).html" method="get">
            <input type="text" id="search-input" name="search-input"  placeholder="검색어를 입력해주세요." autocomplete="off">
            <button id="search-btn">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
            </button>
          </form>
        </div>
        
        <!-- x버튼을 누르면 p태그 요소(this)가 없어지게 -->
        <!-- prepend 활용 -->
        <div class="recent-search">
          <p>
            <span>최근 검색어 1</span> 
            <span class="search-delete">X</span>
          </p>
          
          <p>
            <span>최근 검색어 2</span> 
            <span class="search-delete">X</span>
          </p>
          
          <p>
            <span>최근 검색어 3</span> 
            <!-- <button type="button">X</button> -->
            <span class="search-delete">X</span>
          </p>

          <p>
            <span id="search-delete-all">전체 삭제</span>
          </p>
  
        </div>
        
      </div>
    </div>
  </div>


  <!-- 카테고리 오프캔버스 -->
  <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbar">
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="category-menu-title">Category</h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <ul class="list-group category-list">
          <a href="#"><li class="list-group-item category">카테고리 1</li></a>
          <a href="#"><li class="list-group-item category">카테고리 2</li></a>
          <a href="#"><li class="list-group-item category">카테고리 3</li></a>
          <a href="#"><li class="list-group-item category">카테고리 4</li></a>
        </ul>
      </div>  
    </div>
  </div>


  <!-- 본문 -->
  <main style="width: 100%;">
    <!-- 프로필 영역 -->
    <div>

      <div class="blog-profile-area">
        <div class="blog-profile-photo">
          <img id="blog-profile-img" src="https://via.placeholder.com/200x200" alt="">
        </div>
        <div class="blog-profile-text">
          <h1>ID</h1>
          <p>Introduction</p>
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
			              <a href="${post.memberName}/view?pno=${post.postNo}&cp=${blogPostPagination.currentPage}" class="card-link">
			  
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
<!-- memberName으로 변경 예정 -->
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
			<ul class="pagination">
				<c:if test="${blogPostPagination.startPage != 1}">
					<li><a class = "page-link" href = "${post.blogTitle}?cp=1">&lt;&lt;</a></li>
					<li><a class = "page-link" href = "${post.blogTitle}?cp=${blogPostPagination.prevPage}">&lt;</a></li>
				</c:if>
				
				<%-- 페이지네이션 번호 목록 --%>
				<c:forEach begin="${blogPostPagination.startPage}"  end="${blogPostPagination.endPage}" step="1" var="i">
					<c:choose>
						<c:when test="${i==blogPostPagination.currentPage}">
							<li><a class = "page-link" style="color: black; font-weight: bold">${i}</a></li>
						</c:when>
						
						<c:otherwise>
							<li><a class = "page-link" href = "${post.blogTitle}?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
					<c:if test="${pagination.endPage != pagination.nextPage}">
						<li><a class = "page-link" href = "${post.blogTitle}?cp=${blogPostPagination.nextPage}">&gt;</a></li>
						<li><a class = "page-link" href = "${post.blogTitle}?cp=${blogPostPagination.maxPage}">&gt;&gt;</a></li>
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


  </main>

  

  <!-- Bootstrap5 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

  <script src="${contextPath}/resources/js/post.js"></script>
  
  <%-- session 에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
  
</body>
</html>