<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Post Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/adminCss.css">
</head>
<body>
	<header id="header">
		<div id="logout"
		onclick="location.href='${pageContext.servletContext.contextPath}/admin/logout'"
		>Logout</div>
		<nav id="nav">
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/member"
					class="category">Member</a>
			</div>
			<%-- 절대 경로 에서는 맨 앞에 '/'가 없어야 한다. --%>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/post"
					class="category" style="color: #3278FE;">Post</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/statistics"
					class="category">Statistics</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/report"
					class="category">Report</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/enquiry"
					class="category">1:1 Enquiry</a>
			</div>
		</nav>
	</header>
	<main id="main">
		<section id="section-table">

			<table id="admin-table">
				<thead>
					<tr>
						<td>&nbsp;&nbsp;</td>
						<th>No</th>
						<th>글제목</th>
						<th>글 내용</th>
						<th>회원번호</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>작성일</th>
						<th>신고수</th>
						<th>경고수</th>
						<th>글 상태</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty postList }">
							<td colspan="11">게시글이 존재하지 않습니다.</td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${postList}" var="post">
								<tr>
									<td class="check"><input type="checkbox"></td>
									<td>${post.postNo}</td>
									<td>${post.postTitle}</td>
									<td>${post.postContent}</td>
									<td>${post.memberNo}</td>
									<td>${post.readCount}</td>
									<td>${post.likeCount}</td>
									<td>${post.createDate}</td>
									<td>${post.reportCount}</td>
									<td>${post.violationCount}</td>
									<td>${post.postStatusName}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>

			</table>
			<div class="button-area">
				<div class="button" data-bs-toggle="modal"	data-bs-target="#postModal1"
					style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;">
					경고
				</div>
				<div class="button" onclick="postStatusChange()"
					style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;">
					삭제
				</div>
			</div>
			<div id="search-area">
				<input type="text" placeholder="검색어를 입력해주세요"> <img
					id="search" src="image/search-solid.svg"
					style="width: 20px; height: 20px;"> <select name="" id="">
					<option value="">회원번호</option>
					<option value="">이메일</option>
					<option value="">닉네임</option>
					<option value="">가입일</option>
					<option value="">가입상태</option>
				</select>
			</div>
      <div class="my-5">
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${pagination.startPage != 1}">
			<li>
				<a class="page-link" href="post?cp=1">&lt;&lt;</a>
			</li>
			<li>
				<a class="page-link" href="post?cp=${pagination.prevPage}">&lt;</a>
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
							<a class="page-link" href="post?cp=${i}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pagination.endPage != pagination.maxPage}">
			<li>
				<a class="page-link" href="post?cp=${pagination.nextPage}">&gt;</a>
			</li>
			<li>
				<a class="page-link" href="post?cp=${pagination.maxPage}">&gt;&gt;</a>
			</li>
			</c:if>

		</ul>
	</div>
		</section>
	</main>
	<!-- Modal -->
	<div class="modal" id="postModal1" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<span style="font-size: 30px; font-weight: bold;">경고 내용</span>
			<button type="button" class="btn-close" data-bs-dismiss="modal"
			aria-label="Close"></button>
					
		</div>
		<div class="modal-body">
			<section id="modal-section">
				
			</section>
				</div>
			</div>
		</div>
	</div>
	<script>
		const checkBox = document.getElementsByClassName("check")
		
	  	let memberNo = []
  		for(const items of checkBox){
      	if(items.checked){
          memberNo.push(items.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText) 
          
                  }
  		}
	</script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/modal.js"></script>
</body>
</html>