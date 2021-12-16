<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</style>
</head>
<body>
	<header id="header">
		<div id="logout">Logout</div>
		<nav id="nav">
			<div><a href="${pageContext.servletContext.contextPath}/admin/member" class="category" >Member</a>
      </div>
      <%-- 절대 경로 에서는 맨 앞에 '/'가 없어야 한다. --%>
      <div><a href="${pageContext.servletContext.contextPath}/admin/post" class="category" >Post</a></div>
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
						<td>&nbsp;</td>
						<th>No</th>
						<th>회원번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성일</th>
						<th>답장여부</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox"></td>
						<td>1</td>
						<td>1</td>
						<td class="enquiry-title" data-bs-toggle="modal"
							data-bs-target="#exampleModal">Title1</td>
						<td>블로그 통계는 어떻게 보나요?</td>
						<td>2021-11-20</td>
						<td>답장</td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>2</td>
						<td>2</td>
						<td>Title2</td>
						<td>회원가입은 어떻게 하나요?</td>
						<td>2021-11-01</td>
						<td>미답장</td>
					</tr>
					<tr>
						<td><input type="checkbox"></td>
						<td>3</td>
						<td>3</td>
						<td>Title3</td>
						<td>회원가입은 어떻게 하나요?</td>
						<td>2021-09-01</td>
						<td>답장</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>

			</table>
			<div class="button-area">
				<div class="button"
					style="background-color: #3278FE; color: white; width: 70px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;">
					답장여부</div>


			</div>
			<div id="search-area">
				<input type="text" placeholder="검색어를 입력해주세요"> <img
					id="search" src="image/search-solid.svg"
					style="width: 20px; height: 20px;"> <select name="" id="">
					<option value="">회원번호</option>
					<option value="">제목</option>
					<option value="">내용</option>
					<option value="">작성일</option>
					<option value="">답장여부</option>
				</select>
			</div>
			<div id="paging">

				<a href=""><span>이전</span></a> <a href=""><span>1</span></a> <a
					href=""><span>2</span></a> <a href=""><span>3</span></a> <a href=""><span>4</span></a>
				<a href=""><span>5</span></a> <a href=""><span>...</span></a> <a
					href=""><span>maxNum</span></a> <a href=""><span>다음</span></a>
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
							<div class="info">
								userid@email.com <br> 2021-11-10 <br> 미답장
							</div>
						</div>
						<div>
							<div class="content">&nbsp;내용</div>
							<textarea class="content" name="" id="" cols="100" rows="10"
								readonly style="resize: none; outline: none;"></textarea>
						</div>
						<br>
						<div class="modal-button"
							style="background-color: #3278FE; color: white; width: 70px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px; float: right;">
							답장</div>
						<div id="modal-button2" style="text-align: right; display: none;">
							<div
								style="background-color: #3278FE; color: white; width: 70px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px; display: inline-block;">
								보내기</div>
							<div
								style="background-color: #3278FE; color: white; width: 70px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px; display: inline-block;">
								이전으로</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		></script>
	<script>
		const button = document.getElementsByClassName("modal-button")
		const textarea = document.getElementsByTagName("textarea");
		const button2 = document.getElementById("modal-button2")
		button[0].addEventListener("click", function() {
			textarea[0].removeAttribute("readonly")
			this.style.display = "none";
			button2.style.display = "block";
		})
	</script>
</body>
</html>