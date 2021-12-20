<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Admin Member Page</title>
	<%-- 여러 군데에서 쓰는 css 같은 경우는 절대 경로 --%>
	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/adminCss.css">
</head>
<body>
	${loginAdmin}
  <header id="header">
    <div id="logout" 
	
	onclick="location.href='${pageContext.servletContext.contextPath}/admin/logout'">
      Logout
    </div>
    <nav id="nav">
      <div><a href="${pageContext.servletContext.contextPath}/admin/member" class="category" style="color: #3278FE;" >Member</a>
      </div>
      <%-- 절대 경로 에서는 맨 앞에 '/'가 없어야 한다. --%>
      <div><a href="${pageContext.servletContext.contextPath}/admin/post" class="category" >Post</a></div>
      <div><a href="${pageContext.servletContext.contextPath}/admin/statistics" class="category">Statistics</a></div>
      <div><a href="${pageContext.servletContext.contextPath}/admin/report" class="category" >Report</a></div>
      <div><a href="${pageContext.servletContext.contextPath}/admin/enquiry" class="category">1:1 Enquiry</a></div>
    </nav>
</header>
<main id="main">
	
	<section id="section-table">
		
		<form action="member" Method="POST" id="mainForm"> <!-- window.location.href : 현재 주소 -->
			<table id="admin-table">
				<thead>
					<tr>
						<td>&nbsp;&nbsp;</td>
						<th>회원번호</th>
						<th>이메일</th>
						<th>닉네임</th>
						<th>가입일</th>
						<th>신고횟수</th>
						<th>경고횟수</th>
						<th>회원상태</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty memberList }">
							<td colspan="8">게시글이 존재하지 않습니다.</td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${memberList}" var="member">
								<tr>
									<td><input class="check" name="check" type="checkbox" ></td>
									<td>${member.memberNo }</td>
									<td>${member.memberEmail }</td>
									<td>${member.memberName }</td>
									<td>${member.enrollDate }</td>
									<td>2</td>
									<td>${member.violationCount}</td>
									<td>${member.statusName}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>

			</table>
			<div class="button-area">
        <div class="button" onclick="warningPlus(), location.reload()"
          style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;">
          경고 +</div>
		  <div class="button" onclick="warningMinus(), location.reload()"
          style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;">
          경고 -</div>
         </form>
      </div>
	  <form action="member?cp=1" method="GET" onsubmit="return refresh()">
		  <div id="search-area">
			 <input type="text" name="searchWord" placeholder="검색어를 입력해주세요">
			 <img id="search" src="image/search-solid.svg" style="width: 20px; height: 20px;">
			 <select name="searchTag">
			   <option value="no">회원 번호</option>
			   <option value="emai;">이메일</option>
			   <option value="name">닉네임</option>
			   <option value="enrollDate">가입일</option>
			   <option value="">신고횟수</option>
			   <option value="6">경고 횟수</option>
			   <option value="7">회원 상태</option>
			 </select>
		   </div>
	  </form>
      <div class="my-5">
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${pagination.startPage != 1}">
			<li>
				<a class="page-link" href="member?cp=1">&lt;&lt;</a>
			</li>
			<li>
				<a class="page-link" href="member?cp=${pagination.prevPage}">&lt;</a>
			</li>
			
			</c:if>
			<%-- 페이지네이션 번호 목록 --%>
			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1" var="i">
				<c:choose>
					<c:when test="${i == pagination.currentPage}">
						<li>
							<a class="page-link" style="color:black; font-weight:bold;">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a class="page-link" href="member?cp=${i}&searchWord=${searchWord}&searchTag=${searchTag}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pagination.endPage != pagination.maxPage}">
			<li>
				<a class="page-link" href="member?cp=${pagination.nextPage}&searchWord=${searchWord}&searchTag=${searchTag}">&gt;</a>
			</li>
			<li>
				<a class="page-link" href="member?cp=${pagination.maxPage}">&gt;&gt;</a>
			</li>
			</c:if>

		</ul>
	</div>
    </section>
  </main>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
  <script>
	  const inputSearch = document.getElementById("search-area").firstElementChild
	  const warningBtn = document.getElementsByClassName("button");
	  const contextPath = "${pageContext.servletContext.contextPath}"
	  const checkBox = document.getElementsByClassName("check")
	  console.log(checkBox[0].checked);
	  console.log(checkBox[0].parentElement.nextElementSibling.innerText);
	  console.log(checkBox.checked);

	  inputSearch.addEventListener("keyup", function(){
		  if(e.key =="Enter"){
			  refresh1()
		  }
	  })

	  function refresh1(){
		  return true
	  }
  </script>
  <script src="${pageContext.servletContext.contextPath}/resources/js/modal.js"></script>
</body>
</html>