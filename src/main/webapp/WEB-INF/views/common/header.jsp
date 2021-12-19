<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
>>>>>>> dcf32d051412d6a89fe8c14660b8918114751257
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 프로젝트의 시작 주소를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />


<!DOCTYPE html>
<html lang="ko">
<head>
<<<<<<< HEAD
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Semi Project</title>

<!-- Bootstrap4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- 공용 CSS -->   
<!-- CSS같은 자원의 경로는 주소 경로 -->
<!-- [tip!] : header, footer 같이 여러 주소에 사용되어지는 화면의 자원 주소 경로 지정 시
			  절대 경로를 사용하는 것이 좋다. -->
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">

</head>

<body>
	<!-- navbar을 이용한 header -->
	<header class="header navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">

			<!-- 좌상단 로고 -->
			<a class="navbar-brand" href="${contextPath}">Semi Project</a>

			<!-- 화면 크기가 작아진 경우 나타나는 햄버거 버튼(반응형) -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- 우상단 메뉴 -->
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">

					<li class="nav-item"><a class="nav-link" href="${contextPath}/board/list">Board</a></li>

					<!-- 로그인 버튼 -->
					<%-- Modal 동작 버튼은 data-toggle="modal" 속성과 href 속성값으로 보여질 Modal 영역 id를 작성하면된다. --%>
					
					
					<c:choose>
					<c:when test="${empty sessionScope.loginMember}">
					<%-- 로그인이 되어있지 않을때 --%>
					<li class="nav-item active"><a class="nav-link" data-toggle="modal" href="#login-modal">Login</a></li>
					
					</c:when>
					
					 <c:otherwise>
					<%-- 로그인이 되어있을때 --%>
					<li class="nav-item active"><a class="nav-link" href="${contextPath}/member/myPage">${sessionScope.loginMember.memberName}</a></li>
					<li class="nav-item active"><a class="nav-link" href="${contextPath}/member/logout">Logout</a></li>
																				<!-- /semi/member/logout 요청 (a 태그 모두 get방식)  -->
					 
					 
					 </c:otherwise>					
					 
					</c:choose>

				</ul>
			</div>

		</div>
	</header>

	<!-- Modal : 화면에 불투명한 배경을 씌우고 그 위에 새로 나타나는 창 -->
	<div class="modal fade" id="login-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<%-- 모달 헤더 --%>
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">로그인 모달창</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<%-- 모달 바디 --%>
				<div class="modal-body">

					<!-- form 태그를 이용하여 아이디 비밀번호을 입력 받은 후 서버 제출 -->
					<!-- onsubmit을 이용하여 로그인 시 입력 받은 값이 유효한지 판별 -->
					<form class="form-signin" method="POST" action="${contextPath}/member/login" onsubmit="return loginValidate()">

						<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" value = "${cookie.saveId.value}"> 
						<br>				<!--EL을 이용해서 쿠키 접근  --> 
						<input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호"> <br>

						<div class="checkbox mb-3">
							<label> 
							<%-- 쿠키에 saveId 값이 있을 때 --%>
							<c:if test = "${ !empty cookie.saveId.value}">
								<c:set var = "chk" value = "checked"/>
								<%-- checked 라는 값을 가진 chk 변수 생성 
									c:set	기본적으로  page scope 임
									해당 페이지 어디서든 사용 가능
									== if 문의 지역변수가 아니다!
								--%>
									
							</c:if>
							
							
								<input type="checkbox" name="save" id="save" ${chk}> 아이디 저장
								<%-- EL은 null 을 "" 빈칸으로 표현 --%>
							</label>
						</div>


						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>


						<a class="btn btn-lg btn-secondary btn-block" href="${contextPath}/member/signup">회원가입</a>
					</form>
				</div>

				<%-- 모달 푸터 --%>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div> 
=======
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>홈페이지</title>
    <link  rel="stylesheet"   href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   
    <link rel="stylesheet" href="${contextPath}/resources/css/postingPage.css">
    <link rel="stylesheet" href="${signUp}">
    <link rel = "stylesheet" href = "${searchPw}">
    <link rel = "stylesheet" href = "${updatePw}">
    <style>
       
    </style>
</head>
<body>
    <!-- 검색창 오프캔버스 -->
  <div class="offcanvas offcanvas-top" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel">
   
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasLabel"></h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>

    <div class="offcanvas-body">
      <div id="search-area">
        <div>
          <input type="search" id="search-input" name="search-input"  placeholder="검색어를 입력해주세요.">
          <a href="#">
            <svg id="search-icon" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
          </a>
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
    <header class="jun-header">
        <div class="px-3 py-2">
            <div class="container">
      
              <!-- 로고 -->
              <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="#" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto">
                  <p>develog</p>
                </a>
                
                <ul class="nav col-12 col-lg-auto my-2 justify-content-center align-items-center my-md-0 text-small">
      
                  <!-- 검색 -->
                  <li data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
                    <a href="#" class="nav-link">
                      <svg class="bi d-block" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
                    </a>
                  </li>
      			
                    
  		<c:choose>
      		<c:when test="${empty sessionScope.loginMember}">
      			
                     <button type="button" class="btn btn-primary login-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" >
                                로그인
                      </button>
      				
      		</c:when>
      		<c:otherwise>
                  <!-- 글쓰기 -->
               <li>
           			<a href="#" class="nav-link">
              	<svg class="bi d-block " xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"/></svg>
            		</a>
          		</li> 
          		
          		      <!--  프로필 --> 
          <li>
            <div class="dropdown">
              <a href="#" class="nav-link d-block text-decoration-none " id="userID" data-bs-toggle="dropdown" aria-expanded="false">
                <img src="images/profile-small.png" width="30" height="30" class="rounded-circle">
              </a>
              <ul class="dropdown-menu text-small" aria-labelledby="userID">
                <li"><a class="dropdown-item" href="#">내 블로그</a></li>
                
                <li><a class="dropdown-item" href="#">새 글작성</a></li>
            
                
                <li><a class="dropdown-item" href="#">임시 글</a></li>
                
               
                <li><a class="dropdown-item" href="#">설정</a></li>
           
                <li><a class="dropdown-item" href="${contextPath}/enquiry/list">1:1문의</a></li>
             
                <li><a class="dropdown-item" href="${contextPath}/member/logout">로그아웃</a></li>
               
              </ul>
            </div>
          </li>
          		
          		
	
      		</c:otherwise>
      
      		</c:choose>
         
                </ul>

		
                </div>
            </div>
        </div>
  <!-- ------------------------------------------------------- 로그인 모달 ---------------------------------------- -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered " style="max-width: inherit">
      <div class="modal-content">
        <div class="modal-header">
            <div class="login-hd">
                <p id="md-title">로그인</p>

                <div class="x-box">
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    
                </div>
            </div>
        </div>
        <div class="modal-body">
         <form action="${contextPath}/member/login" onsubmit = "return loginValidate();" method="post">

           <div class="input-box">
             <div  class="put">
               <div class="img-wrp"><label for="email">
                 <img class="img-type" src="${contextPath}/resources/images/아이디.jpg" alt="">
                </label></div>
                <div class="input-wrp">
                  <input type="email" class="sumit-box" id="email" name="memberEmail" placeholder="이메일을 입력하세요." value = "${cookie.save.value}">
                </div>
              </div>
              <div  class="put">
                <div class="img-wrp"><label for="pw">
                  <img class="img-type" src="${contextPath}/resources/images/비밀번호.jpg" alt="">
                </label></div>
                <div class="input-wrp">
                  <input type="password" class="sumit-box" id="pw" name="memberPw" placeholder="비밀번호를 입력하세요." >
                </div>
              </div>              
              
            </div>
            <div class="signUp-box">
            <div class="idcheckbox" id="idcheckbox">
             <label for="idcheck">이메일 저장</label> 
             <c:if test="${!empty cookie.save.value}">
             <c:set var = "chk" value = "checked"></c:set>
             </c:if>
             <input type="checkbox" name="idcheck" id="idcheck" ${chk}>
            </div>
              <b id="qs">아직 회원가입 안하셨나요?</b>
              <span id="sign-span">            
                <a href="${contextPath}/member/signup"><p>
                  회원가입
                </p>
              </a> 
              <a href="${contextPath}/member/searchpw"><p>
                비밀번호찾기
              </p>
            </a> 
          </span>
          
          </div>
        </div>
        <div class="modal-ft">
            <button id="modal-log" type="submit" class="btn btn-primary">로그인</button>
        </div>      
        </form>   
      </div>
    </div>
  </div>
</header>
  <!-- -----------------------------------------------------------모달 끝----------------------------------------- -->  
>>>>>>> dcf32d051412d6a89fe8c14660b8918114751257
