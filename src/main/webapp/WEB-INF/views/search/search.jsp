<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 프로젝트의 시작 주소를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>
	<c:choose>
		<c:when test="${empty searchInput}">
			검색페이지
		</c:when>
		
		<c:otherwise>
			${searchInput}
		</c:otherwise>
	</c:choose>
</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${contextPath}/resources/css/search.css">
</head>
<body>
  ${searchPost}
	<!-- 헤더 -->
  <header class="jun-header">

    <div class="px-3 py-2">
      <div class="container">

        <!-- 로고 -->
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
          <a href="search" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto">
            <img id="main-logo" src="${contextPath}/resources/images/common/Logo.png">
          </a>
          
          <ul class="nav col-12 col-lg-auto my-2 justify-content-center align-items-center my-md-0 text-small">

            <!-- 검색 -->
            <li data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
              <a class="nav-link" style="cursor:pointer;">
                <svg class="bi d-block" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
              </a>
            </li>

            <!-- 글쓰기 -->
            <li>
              <a href="#" class="nav-link">
                <svg class="bi d-block " xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"/></svg>
              </a>
            </li>
            
            <!-- 프로필 -->
            <li>
              <div class="dropdown">
                <a href="#" class="nav-link d-block text-decoration-none " id="userID" data-bs-toggle="dropdown" aria-expanded="false">
                  <img src="${contextPath}/resources/images/common/profile-small.png" width="30" height="30" class="rounded-circle">
                </a>
                <ul class="dropdown-menu text-small" aria-labelledby="userID">
                  <li><a class="dropdown-item" href="#">메뉴 1</a></li>
                  <li><a class="dropdown-item" href="#">메뉴 2</a></li>
                  <li><a class="dropdown-item" href="#">메뉴 3</a></li>
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
          <form action="search?=${searchInput}" method="get">
            <input type="text" id="search-input" name="search-input"  placeholder="검색어를 입력해주세요." autocomplete="off" maxlength="20">
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

  <!-- 헤더 부분 끝 -->

  <main>
    <div class="px-3 py-2">
      <div class="container">

        <div class="search-page-header">

          <!-- 검색어 결과 화면 -->
          <!-- 검색어 20글자 제한하기... -->
          <div id="search-result">
            <div class="search-result-large">
              <span>"${searchInput}"</span>
            </div>
        
            <div class="search-result-number">
              총 <span>"${searchResultCount}"</span>개의 게시글을 찾았습니다.
            </div>
        
          </div>
          
          <hr class="hr">
          
          <!-- 정렬 메뉴 -->
          <div class="sort-post dropstart border" data-bs-toggle="dropdown" aria-expanded="false">
            <button type="button" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              정렬 방식
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">날짜 ▲ </a></li>
              <li><a class="dropdown-item" href="#">날짜 ▼ </a></li>
              <li><a class="dropdown-item" href="#">조회수 ▲ </a></li>
              <li><a class="dropdown-item" href="#">조회수 ▼ </a></li>
            </ul>
          
          </div>

        </div>
        


        <!-- 카드 -->
        <div class="cards-area">
        
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

          <div style="width: 1200px; height: 50px; clear: both;"></div>

        
        </div>        
        

      </div>

    </div>

  </main>




  


  <!-- Bootstrap5 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

  <script src="${contextPath}/resources/js/search.js"></script>



</body>
</html>