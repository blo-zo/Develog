<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 프로젝트의 시작 주소를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>홈페이지</title>
    <link  rel="stylesheet"   href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/mainPage.css">
    <link rel="stylesheet" href="${signUp}">
    <link rel = "stylesheet" href = "${searchPw}">
    <link rel = "stylesheet" href = "${updatePw}">
    
   	<style>
	@import url('https://fonts.googleapis.com/css2?family=Titillium+Web:ital,wght@0,200;0,300;0,400;0,600;0,700;0,900;1,200;1,300;1,400;1,600;1,700&display=swap');
	</style>
       
   
</head>
<body class="body"">
    <!-- 검색창 오프캔버스 -->
    
    
  <div class="offcanvas offcanvas-top" tabindex="-1" id="offcanvasTop" aria-labelledby="offcanvasTopLabel">
   
    <div class="offcanvas-header">
      <h5 class="offcanvas-title" id="offcanvasLabel"></h5>
      <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>

    <div class="offcanvas-body">
      <div id="search-area">
        <div>
          <form action="${contextPath}/search" id="search-form" onsubmit="document.getElementById('search-icon').onclick">
            <div>
            <input style="width: 360px;" type="text" id="search-input" name="searchInput"  placeholder="검색어를 입력해주세요.">
              <svg id="search-icon" onclick="document.getElementById('search-form').submit()" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
            </div>
          </form>
        </div>
        
        <!-- x버튼을 누르면 p태그 요소(this)가 없어지게 -->
        <!-- prepend 활용 -->
   
        
      </div>
    </div>
  </div>
    <header class="jun-header">
        <div class="px-3 py-2">
            <div class="container">
      
              <!-- 로고 -->
              <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a  style="color: black;" href="${contextPath}" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto">
                  <p style=  "color : #323232;    font-size: 20px; font-family: 'Titillium Web', sans-serif; font-weight: bold">Develog</p>
                </a>
                
                <ul id = "searchList" class="nav col-12 col-lg-auto my-2 justify-content-center align-items-center my-md-0 text-small">
      
                  <!-- 검색 -->
                  <li data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop" id="searchList">
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
           			<a href="${contextPath}/board/insert" class="nav-link">
              	<svg class="bi d-block " xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M14.078 4.232l-12.64 12.639-1.438 7.129 7.127-1.438 12.641-12.64-5.69-5.69zm-10.369 14.893l-.85-.85 11.141-11.125.849.849-11.14 11.126zm2.008 2.008l-.85-.85 11.141-11.125.85.85-11.141 11.125zm18.283-15.444l-2.816 2.818-5.691-5.691 2.816-2.816 5.691 5.689z"/></svg>
            		</a>
          		</li> 
          		
          		      <!--  프로필 --> 
          <li>
            <div class="dropdown">
              <a href="#" class="nav-link d-block text-decoration-none " id="userID" data-bs-toggle="dropdown" aria-expanded="false">
						<P style="margin: 0px; color: black;">${sessionScope.loginMember.memberNm}</P>						
	              </a>
              <ul class="dropdown-menu text-small" aria-labelledby="userID">
                <li><a class="dropdown-item" href="${contextPath}/blog/${loginMember.memberNm}">내 블로그</a></li>
                
                <li><a class="dropdown-item" href="${contextPath}/member/profile">설정</a></li>
           
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
            <div style="width: 500px;">
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
            </div>
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
  <script>
    const contextPath = '${contextPath}'
  </script>
   <script src="${contextPath}/resources/js/search.js"></script>
  
</header>
  <!-- -----------------------------------------------------------모달 끝----------------------------------------- -->  
