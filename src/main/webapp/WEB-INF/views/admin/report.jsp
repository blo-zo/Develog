<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Report Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/adminCss.css">
<style>
#modal-section {
	width: 95%;
	height: 100%;
	margin: auto;
	margin-bottom: 70px;
}

#modal-section>div {
	clear: both;
	width: 100%;
	overflow: auto;
	/* border-bottom: 1px dashed black; */
}

.modal-header {
	padding-right: 5%;
	padding-left: 5%;
}

#modal-section>div:nth-child(2) {
	/* height: 30%; */
	padding: 0px;
	margin: 0px;
}

.info {
	font-size: 23px;
	box-sizing: border-box;
	float: left;
	/* height: 100px; */
	width: 50%;
	margin-top: 30px;
	padding-left: 20px;
	/* border: 1px dashed red; */
}

.info:nth-child(1) {
	text-align: left;
}

.info:nth-child(2) {
	text-align: right;
}

section>div:nth-child(3) {
	height: 40%;
}

section>div:nth-child(4) {
	height: 20%;
}

.content {
	margin-top: 30px;
	box-sizing: border-box;
	float: left;
	height: 100%;
	/* border: 1px dashed blue; */
}

.content:nth-child(1) {
	width: 20%;
	font-size: 40px;
	font-weight: bold;
}

.content:nth-child(2) {
	width: 80%;
}

.content:nth-child(2)>div {
	width: 97%;
	height: 190px;
	border: 1px solid black;
	float: right;
	margin-top: 0.5em;
	padding: 7px;
}
/* css hover 기능을 몰라서 고생했내 */
.report-content:hover {
	color: #3278FE;
	cursor: pointer;
}
</style>
</head>
<body>
<body>
	<header id="header"
	onclick="location.href='${pageContext.servletContext.contextPath}/admin/logout'"
	>
		<div id="logout">Logout</div>
		<nav id="nav">
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/member"
					class="category" >Member</a>
			</div>
			<%-- 절대 경로 에서는 맨 앞에 '/'가 없어야 한다. --%>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/post"
					class="category">Post</a>
			</div>
			<div><a href="${pageContext.servletContext.contextPath}/admin/post" class="category" >Reply</a></div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/statistics"
					class="category">Statistics</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/report"
					class="category" style="color: #3278FE;">Report</a>
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
						<th>No</th>
						<th>회원번호</th>
						<th>닉네임</th>
						<th>대상</th>
						<th>번호</th>
						<th>내용</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty reportList }">
							<td colspan="11">게시글이 존재하지 않습니다.</td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${reportList}" var="report">
								<tr>
									<td>${report.reportNo}</td>
									<td>${report.memberNo}</td>
									<td>${report.memberName}</td>
									<td>${report.reportType}</td>
									<td>${report.targetNo}</td>
									<td class="report-content"
									    data-bs-toggle="modal"
									    data-bs-target="#exampleModal"
										onclick="reportDetailContent(this)">${report.reportContent}</td>
									<td>${report.createDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>

			</table>
			<form action="report?cp=1" method="GET" onsubmit="return refresh()">
				<div id="search-area">
					<input type="text" name="searchWord" placeholder="검색어를 입력해주세요">
					<img id="search" onclick="submit()" src="${pageContext.servletContext.contextPath}/resources/images/admin/search-solid.svg" style="width: 20px; height: 20px;">
					<select name="searchTag" onchange="changeSelect()">
						<option value="no">신고 번호</option>
						<option value="memberNo">회원 번호</option>
						<option value="nickname">닉네임</option>
						<option value="target">대상</option>
						<option value="targetNo">대상 번호</option>
						<option value="content">내용</option>
						<option value="createDate">작성일</option>
					</select>
				</div>
			</form>
      <div class="my-5">
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${pagination.startPage != 1}">
			<li>
				<a class="page-link" href="report?cp=1&searchWord=${searchWord}&searchTag=${searchTag}">&lt;&lt;</a>
			</li>
			<li>
				<a class="page-link" href="report?cp=${pagination.prevPage}&searchWord=${searchWord}&searchTag=${searchTag}">&lt;</a>
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
							<a class="page-link" href="report?cp=${i}&searchWord=${searchWord}&searchTag=${searchTag}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pagination.endPage != pagination.maxPage}">
			<li>
				<a class="page-link" href="report?cp=${pagination.nextPage}&searchWord=${searchWord}&searchTag=${searchTag}">&gt;</a>
			</li>
			<li>
				<a class="page-link" href="report?cp=${pagination.maxPage}&searchWord=${searchWord}&searchTag=${searchTag}">&gt;&gt;</a>
			</li>
			</c:if>

		</ul>
	</div>
		</section>
	</main>
	<!-- Modal -->
	<div class="modal" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<span style="font-size: 50px; font-weight: bold;">신고</span>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<section id="modal-section">
						<div>
							<div class="info input-modal">
								<!-- nn번 게시글 <br> 유형 : <span>홍보</span> -->
							</div>
							<div class="info input-modal">
								<!-- userid@email.com <br> 2021-11-10 <br> 미해결 -->
							</div>
						</div>
						<div>
							<div class="content" input-modal>&nbsp;신고내용</div>
							<div class="content">
								<div class="input-modal"></div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		const contextPath = "${pageContext.servletContext.contextPath}" // 아니 스크립트에 EL표현식이 있으니까 ajax에 Syntax json에러가 나내 ㄷㄷ
	</script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/adminMember.js"></script>
	<script>
			const inputSearch = document.getElementById("search-area").firstElementChild
			inputSearch.addEventListener("keyup", function(){
		  	if(e.key =="Enter"){
			 refresh1()
			  }
	   	 		})

	  		function refresh1(){
			  return true
			  }

			  function changeSelect(){
				const select = document.getElementsByTagName("select")[0]
				const placeholder = document.getElementById("search-area").firstElementChild
				const val = select.options[select.selectedIndex].value
				if(val == 'createDate'){
					placeholder.setAttribute("placeholder", 'YYMMDD - YYMMDD OR YYMMDD')
				}else{
					placeholder.setAttribute("placeholder", '검색어를 입력해주세요')
				}
				}
	</script>
</body>
</body>
</html>