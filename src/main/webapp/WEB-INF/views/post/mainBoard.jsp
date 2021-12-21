<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />


<title>게시판</title>
<link rel="stylesheet" href="${contextPath}/resources/css/board-style.css">

	
	<div class="container my-5">
		<h1>게시판</h1>
		
		<button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = '${contextPath}/blog/insert';">글쓰기</button>
		<div class="list-wrapper">
			<table class="table table-hover table-striped my-5" id="list-table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>

				
				<%-- 게시글 목록 출력 --%>
				<tbody>
					<c:choose>
					<c:when test="${empty postListAll}">
					<%-- 조회된 게시글 목록이 없을 때 --%>
					<tr>
					<td colspan="6">게시글이 존재하지 않습니다</td>
					</tr>
					</c:when>
					
					<c:otherwise>
					
					<c:forEach items = "${postListAll}" var = "post">
					<%-- 조회된 게시글 목록이 있을 때 --%>
					<tr>
					<%-- 글번호 --%>
					<td>${post.postNo}</td>
					<%-- 카테고리명 --%>
					<td>${post.categoryCode}</td>
					<%-- 글제목 --%>
					<td class="boardTitle">
					<a href="#">
					<a href="blog/view?blog=${post.blogTitle}&pno=${post.postNo}"> 
							${post.postTitle}
					</a>
					</td>
					
					<%-- 작성자 --%>
					<td>
						<a href="blog/${post.blogTitle}">
						<input type="hidden" name="blog" value="${param.blog}">
							${post.memberNo}
						</a>
					</td>
					
					<%-- 조회수 --%>
					<td>${post.readCount}</td>
					
					<%-- 작성일 --%>
					<td>${post.createDate}</td>
					</tr>
					</c:forEach>
					</c:otherwise>					
					</c:choose>
				</tbody>
			</table>
		</div>


		<%-- 로그인이 되어있는 경우에만 글쓰기 버튼 노출 --%>
		<%-- <c:if test="${!empty loginMember }"> --%>
			<%-- <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = "${contextPath}/blog/insert';">글쓰기</button> --%>
		<%-- </c:if> --%>


		
		
		


		<!-- 검색창 -->
		<div class="my-5">

			<form action="list" method="GET" class="text-center" id="searchForm">

				<select name="sk" class="form-control" style="width: 100px; display: inline-block;">
					<option value="title">글제목</option>
					<option value="content">내용</option>
					<option value="titcont">제목+내용</option>
					<option value="writer">작성자</option>
				</select> <input type="text" name="sv" class="form-control" style="width: 25%; display: inline-block;">
				<button class="form-control btn btn-primary" style="width: 100px; display: inline-block;">검색</button>
			</form>

		</div>
	</div>


</body>
</html>
