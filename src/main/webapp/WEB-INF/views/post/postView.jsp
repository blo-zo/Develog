<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	                  <img src="images/profile-small.png" width="30" height="30" class="rounded-circle">
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
	            
<!-- memberName으로 변경 예정 -->
					<table style="margin: 5px;">
		                <tr>
		                  <td colspan="2">
		                    By.<a href="${post.blogTitle}"><span id="post-author">${post.memberNo}</span></a> 
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
	            
	            <div class="post-menu">
	              	<a href="#">통계</a> /
	              	<a href="#">수정</a> /
              		<a href="#">삭제</a>
	            </div>
	            
	            <div class="post-menu" style="display: none;">
	            	<a href="#">신고하기</a>
            	</div>
	            
	            <%-- <c:choose>
		            <!-- 작성자 == 로그인 회원 번호인 경우 -->
	            	<c:when test="${loginMember.memberNo == post.memberNo}">
			            <div class="post-menu">
			              <a href="#">통계</a> /
			              <a href="#">수정</a> /
			              <a href="#">삭제</a>
			            </div>
	            	</c:when>
					
					<c:otherwise>
			            <div class="post-menu">
			            	<a href="#">신고하기</a>
		            	</div>
					</c:otherwise>	            	
	            </c:choose> --%>
	            
            	
	          </div>
	
	        </div>
	
	        <!-- 포스트 중간(게시글 내용) -->
	        <div class="post-body" style="min-height: 200px;">
				
				${post.postContent}
		
	        </div>
	
	        <!-- 게시글 하단 태그 버튼, 이전 글 다음 글 버튼 -->
	        <div class="post-body2">
	          
	          <div class="post-tags">
	            
	            <div class="post-tag" onclick="location.href='#'">
	              <span>샘플 태그 1</span>
	            </div>
	
	            <div class="post-tag" onclick="location.href='#'">
	              <span>샘플 태그 2</span>
	            </div>
	
	            <div class="post-tag" onclick="location.href='#'">
	              <span>샘플 태그 3</span>
	            </div>
	
	            <div class="post-tag" onclick="location.href='#'">
	              <span>샘플 태그 4</span>
	            </div>
	
	          </div>
	
	          <div class="post-prev-next-btn">
	            <div class="post-prev-btn">이전 글</div>
                <div class="goList">
	              <a href="${post.blogTitle}?cp=${param.cp}">목록으로</a>
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
	              <h1>${post.memberNo}</h1>
	              <p>Introduction</p>
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
	          <hr class="">
	    
	          <div class="blog-tag-list-box">
	    
	            <a href="#"><span>#농구</span></a>
	            <a href="#"><span>#숙제</span></a>
	            <a href="#"><span>#종각</span></a>
	            <a href="#"><span>#개발 공부</span></a>
	    
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
	              <div class="favorite-share-icon">
	
	                <!-- 토글로 흰 하트 / 검은 하트로 바뀌게 -->  
	                <!-- 빈 하트 -->
	                <svg width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M12 21.593c-5.63-5.539-11-10.297-11-14.402 0-3.791 3.068-5.191 5.281-5.191 1.312 0 4.151.501 5.719 4.457 1.59-3.968 4.464-4.447 5.726-4.447 2.54 0 5.274 1.621 5.274 5.181 0 4.069-5.136 8.625-11 14.402m5.726-20.583c-2.203 0-4.446 1.042-5.726 3.238-1.285-2.206-3.522-3.248-5.719-3.248-3.183 0-6.281 2.187-6.281 6.191 0 4.661 5.571 9.429 12 15.809 6.43-6.38 12-11.148 12-15.809 0-4.011-3.095-6.181-6.274-6.181"/></svg>
	
	                <!-- 검은색 하트 -->
	                <svg style="display: none;" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 4.248c-3.148-5.402-12-3.825-12 2.944 0 4.661 5.571 9.427 12 15.808 6.43-6.381 12-11.147 12-15.808 0-6.792-8.875-8.306-12-2.944z"/></svg>
	
	              </div>
	              
	            </div>
	
	            <!-- 좋아요 수 -->
	            <div class="favorite-share-area">
	              <span class="favorite-count">0</span>
	            </div>
	
	            <!-- 공유하기 아이콘 -->
	            <div class="favorite-share-area">
	              <div class="favorite-share-icon">
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
	                    n
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


		<form action="#" method="post" name="requestForm" >
		<input type="hidden" name = "cp" value="${param.cp }">
		<input type="hidden" name = "pno" value="${param.pno }">
		</form>




  <!-- Bootstrap5 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

  <script src="${contextPath}/resources/js/post.js"></script>

</body>
</html>