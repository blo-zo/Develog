<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Enquiry Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/adminCss.css">
<style>
.enquiry-title:hover {
	color: #3278FE;
	cursor: pointer;
}

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

.report-content{
	width: 200px;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	display: inline-block;
	
}

#button-area > div{
	float: right;
	background-color: #3278FE; color: white; width: 70px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;
	margin-right: 10px;
	cursor: pointer;
}
</style>
</head>
<body>
	<header id="header">
		<div id="logout"
		onclick="location.href='${pageContext.servletContext.contextPath}/admin/logout'"
		>Logout</div>
		<nav id="nav">
			<div><a href="${pageContext.servletContext.contextPath}/admin/member" class="category" >Member</a>
      </div>
      <%-- 절대 경로 에서는 맨 앞에 '/'가 없어야 한다. --%>
      <div><a href="${pageContext.servletContext.contextPath}/admin/post" class="category" >Post</a></div>
	  <div><a href="${pageContext.servletContext.contextPath}/admin/post" class="category" >Reply</a></div>
      <div><a href="${pageContext.servletContext.contextPath}/admin/statistics" class="category">Statistics</a></div>
      <div><a href="${pageContext.servletContext.contextPath}/admin/report" class="category" >Report</a></div>
      <div><a href="${pageContext.servletContext.contextPath}/admin/enquiry" class="category" style="color: #3278FE;" >1:1 Enquiry</a></div>
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
						<th>제목</th>
						<th>내용</th>
						<th>작성일</th>
						<th>답장여부</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty enquiryList }">
							<td colspan="11">게시글이 존재하지 않습니다.</td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${enquiryList}" var="enquiry">
								<tr>
									<td>${enquiry.enquiryNo}</td>
									<td>${enquiry.memberNo}</td>
									<td>${enquiry.memberName}</td>
									<td>${enquiry.enquiryTitle}</td>
									<td>
									<span class="report-content"
									data-bs-toggle="modal"
									data-bs-target="#exampleModal"
									onclick="enquiryDetailContent(this)">
										${enquiry.enquiryContent}
									</span>
									</td>
									<td>${enquiry.createDate}</td>
									<c:choose>
										<c:when test="${enquiry.parentEnquiry == 0}">
											<td>미답장</td>
										</c:when>
										<c:otherwise>
											<td>답장 완료</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>

			</table>
			<form action="enquiry?cp=1" method="GET" onsubmit="return refresh()">
				<div id="search-area">
					<input type="text" name="searchWord" placeholder="검색어를 입력해주세요">
					<img id="search" src="image/search-solid.svg" style="width: 20px; height: 20px;">
					<select name="searchTag" onchange="changeSelect()">
						<option value="enquiryNo">문의 번호</option>
						<option value="memberNo">회원 번호</option>
						<option value="nickname">닉네임</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="createDate">작성일</option>
					</select>
					<select  name="orderTag" onchange="changeSelect()">
						<option value="">답장 정렬</option>
						<option value="answer">답장 완료</option>
						<option value="noAnswer">미답장</option>

					</select>
				</div>
			</form>
      <div class="my-5">
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${pagination.startPage != 1}">
			<li>
				<a class="page-link" href="enquiry?cp=1&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&lt;&lt;</a>
			</li>
			<li>
				<a class="page-link" href="enquiry?cp=${pagination.prevPage}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&lt;</a>
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
							<a class="page-link" href="enquiry?cp=${i}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pagination.endPage != pagination.maxPage}">
			<li>
				<a class="page-link" href="enquiry?cp=${pagination.nextPage}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&gt;</a>
			</li>
			<li>
				<a class="page-link" href="enquiry?cp=${pagination.maxPage}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&gt;&gt;</a>
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
					<span style="font-size: 50px; font-weight: bold;">1:1 문의</span>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<section id="modal-section">
						<div>
							<div class="info"></div>
							<div class="info input-modal">
								<!-- userid@email.com <br> 2021-11-10 <br> 미답장 -->
							</div>
						</div>
						<div>
							<div class="content">&nbsp;내용</div><!-- readonly 위치를 변경하니 적용이 됬다. -->
							<textarea readonly class="content input-modal" name="" id="" cols="100" rows="10"
								 style="resize: none; outline: none;"></textarea> 
						</div>
						<br>
						<div id="button-area" style="float: right;">
							<div class="modal-button">
								답장</div>
							<div class="modal-button" style="display: none;"  onclick="enquirySend()">
								보내기
							</div>
							<div class="modal-button" style="display: none;">
								이전으로
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		const button = document.getElementsByClassName("modal-button")
		const textarea = document.getElementsByTagName("textarea");
		let content = ""
		button[0].addEventListener("click", function() {
			textarea[0].removeAttribute("readonly")
			content = textarea[0].value
			textarea[0].value = ""
			this.style.display = "none";
			button[1].style.display = "block";
			button[2].style.display = "block";
		})
		button[2].addEventListener("click", function() {
			textarea[0].readOnly = true
			console.log(content);
			textarea[0].value = content

			this.style.display = "none"
			button[1].style.display = "none"
			button[0].style.display = "block"
		
		})
		button[1].addEventListener("click", function(){

		})

		let enquiryNo;
		const inputModal = document.getElementsByClassName("input-modal");
		// function enquiryModal(e){
		// 	enquiryNo = e.previousElementSibling.previousElementSibling.previousElementSibling.innerText
		// }

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
	<script>
		const contextPath = "${pageContext.servletContext.contextPath}"
	</script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/modal.js"></script>
	
</body>
</html>