<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${contextPath}/resources/css/mainPage.css">
	<style>
	@import url('https://fonts.googleapis.com/css2?family=Titillium+Web:ital,wght@0,200;0,300;0,400;0,600;0,700;0,900;1,200;1,300;1,400;1,600;1,700&display=swap');
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
          <form action="search" id="search-form" onsubmit="document.getElementById('search-icon').onclick">
            <input type="text" id="search-input" name="searchInput"  placeholder="검색어를 입력해주세요.">
              <svg id="search-icon" onclick="document.getElementById('search-form').submit()" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M23.809 21.646l-6.205-6.205c1.167-1.605 1.857-3.579 1.857-5.711 0-5.365-4.365-9.73-9.731-9.73-5.365 0-9.73 4.365-9.73 9.73 0 5.366 4.365 9.73 9.73 9.73 2.034 0 3.923-.627 5.487-1.698l6.238 6.238 2.354-2.354zm-20.955-11.916c0-3.792 3.085-6.877 6.877-6.877s6.877 3.085 6.877 6.877-3.085 6.877-6.877 6.877c-3.793 0-6.877-3.085-6.877-6.877z"/></svg>
          </form>
        </div>
        
        <!-- x버튼을 누르면 p태그 요소(this)가 없어지게 -->
        <!-- prepend 활용 -->
       <!--  <div class="recent-search">
          <p>
            <span id="recent-search"></span> 
            <span class="search-delete">X</span>
          </p>
          
  
          <p>
            <span id="search-delete-all">전체 삭제</span>
          </p>
  
        </div> -->
        
      </div>
    </div>
  </div>
    <header class="jun-header">
        <div class="px-3 py-2">
            <div class="container">
      
              <!-- 로고 -->
              <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="${contextPath}" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto">
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
						<P class="memberName" style="color: black;">${sessionScope.loginMember.memberNm}</P>						
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
            <div class="idcheckbox" id="idcheckbox" style="text-align: left">
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
  <script>
    const contextPath = '${contextPath}'
  </script>
   <script src="${contextPath}/resources/js/search.js"></script>
  
</header>
  <main>
    <div class="px-3 py-2">
      <div class="container">

      
        
	<c:choose>
	<c:when test='${empty searchPost}'><!--검색 결과 없을 경우  -->
	 <div style="margin: auto; color: red; font-size: 20px; width: 1200px;">
	  
		<p style="text-align: center;">
		
		검색 결과 없습니다.
		</p>
		<p></p>
	 </div>
		<div class="cards-area">
        
          <section class="card-bord">
       
  	 		<c:forEach items="${allList}" var="post">
  	 
          <div class="wrapper-card">
            
   
   
                 <a href="${contextPath}/blog/${post.memberName}/view?pno=${post.postNo}" class="wrapper-a">
   
                     
                     <div class="card">
                         
                         <div class="image"><img src="${contextPath}${post.postImg.postImgPath}${post.postImg.postImgName}"  id="img-size"></div>  
                         
                         <div class="title-box">
                             ${post.postTitle}
                         </div>  
                         <div class="user-div sub-div">
                             
                             <span class="by_name">by <b>${post.memberName}</b></span><div class="likes"><svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path>
                             </svg>${post.favoriteCount}</div>
                         </div>
                         
                     </div>
                 </a>
             </div>
      
      
    
  	 	</c:forEach>
   		</section>
   		
        </div>
	
	
	
	
	
	</c:when>
	<c:otherwise>
	
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
          
          
       

        </div>
	
	
	
	
	
        <!-- 카드 -->
        <div class="cards-area">
        
          <section class="card-bord">
       
  	 		<c:forEach items="${searchPost}" var="post">
  	 
          <div class="wrapper-card">
            
   
   
                 <a href="${contextPath}/blog/${post.memberName}/view?pno=${post.postNo}" class="wrapper-a">
   
                     
                     <div class="card">
                         
                         <div class="image"><img src="${contextPath}${post.postImg.postImgPath}${post.postImg.postImgName}"  id="img-size"></div>  
                         
                         <div class="title-box">
                             ${post.postTitle}
                         </div>  
                         <div class="user-div sub-div">
                             
                             <span class="by_name">by <b>${post.memberName}</b></span><div class="likes"><svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path>
                             </svg>${post.favoriteCount}</div>
                         </div>
                         
                     </div>
                 </a>
             </div>
      
      
    
  	 	</c:forEach>
   		</section>
   		
        </div>
   		
	</c:otherwise>
	</c:choose>
      </div>
      
	
      
     

           
        


    </div>

  </main>




  


  <!-- Bootstrap5 -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

  <script src="${contextPath}/resources/js/search.js"></script>

	<c:if test="${!empty sessionScope.message}">
        <script>
		$(function(){
			alert("${message}");
			
		});        
        
        </script>
        <c:remove var="message" scope = "session"/>
        </c:if>

</body>
</html>