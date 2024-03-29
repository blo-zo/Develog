<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Reply Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/css/adminCss.css">
<style>
	.remove-status:hover{
		cursor : pointer;
		color: #3278FE;
	}
	.col{
			font-size: 25px;
		}
	.removeViolation:hover{
			cursor: pointer;
	}

	.postContent{
		text-decoration: none !important;
		color: black;
	}

	.postContent:hover{
		cursor : pointer;
		color: #3278FE;
	}

</style>
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
					class="category" >Post</a>
			</div>
			<div><a href="${pageContext.servletContext.contextPath}/admin/reply" class="category" style="color: #3278FE;" >Reply</a></div>
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
						<th>닉네임</th>
						<th>내용</th>
						<th>회원번호</th>
						<th>작성일</th>
						<th>신고수</th>
						<th>글 번호</th>
						<th>댓글 상태</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty listReply }">
							<td colspan="11">게시글이 존재하지 않습니다.</td>
						</c:when>
						<c:otherwise>
							<c:forEach items="${listReply}" var="reply">
							<tr>
									<td><input class="check" name="check" type="checkbox" ></td>
									<td>${reply.replyNo}</td>
									<td>${reply.memberName}</td>
									<td>
										<a  class="postContent" style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width: 200px; display: inline-block; text-align: center;"
										href="${pageContext.servletContext.contextPath}/blog/${reply.memberName}/view?pno=${reply.postNo}">
										${reply.replyContent}
									</a>
									</td>
									<td>${reply.memberNo}</td>
									<td>${reply.replyCreateDate}</td>
									<td>${reply.reportCount}</td>
									<td>${reply.postNo}</td>
									<c:choose>
										<c:when test="${reply.replyStatusName eq '블라인드'}">
											<td class="remove-status" onclick="blindContent(${reply.replyNo})"
												data-bs-toggle="modal"	data-bs-target="#postModal3">
												${reply.replyStatusName}
											</td>
										</c:when>
										<c:otherwise>
											<td>${reply.replyStatusName}</td>
										</c:otherwise>
									</c:choose>
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
				<div class="button" data-bs-toggle="modal"	data-bs-target="#postModal2"
					style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px;">
					상태변경
				</div>
			</div>
			<form action="reply?cp=1" method="GET" onsubmit="return refresh()">
				<div id="search-area">
					<input type="text" name="searchWord" placeholder="검색어를 입력해주세요">
					<img id="search" onclick="submit()" src="${pageContext.servletContext.contextPath}/resources/images/admin/search-solid.svg" style="width: 20px; height: 20px;">
					<select name="searchTag" onchange="changeSelect()">
						<option value="no">댓글 번호</option>
						<option value="memberNo">회원번호</option>
						<option value="memberName">닉네임</option>
						<option value="content">내용</option>
						<option value="createDate">작성일</option>
						<option value="postNo">글번호</option>
						<option value="status">댓글상태</option>
					</select>
					<select  name="orderTag" onchange="changeSelect()">
						<option value="">신고수 정렬</option>
						<option value="ascReports">신고수 오름</option>
						<option value="descReports">신고수 내림</option>
					</select>
				</div>
			</form>
      <div class="my-5">
		<ul class="pagination" style="justify-content: center;">
			<c:if test="${pagination.startPage != 1}">
			<li>
				<a class="page-link" href="reply?cp=1&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&lt;&lt;</a>
			</li>
			<li>
				<a class="page-link" href="reply?cp=${pagination.prevPage}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&lt;</a>
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
							<a class="page-link" href="reply?cp=${i}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">${i}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pagination.endPage != pagination.maxPage}">
			<li>
				<a class="page-link" href="reply?cp=${pagination.nextPage}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&gt;</a>
			</li>
			<li>
				<a class="page-link" href="post?cp=${pagination.maxPage}&searchWord=${searchWord}&searchTag=${searchTag}&orderTag=${orderTag}">&gt;&gt;</a>
			</li>
			</c:if>

		</ul>
	</div>
		</section>
	</main>
	<!-- blind modal -->
	<div class="modal" id="postModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span style="font-size: 30px; font-weight: bold;">경고 내용</span>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
							<div class="content" input-modal style="float: right;">
								<textarea name="" id="" cols="90" rows="7"
									style="border: none; margin-left: 1%; outline: none; resize: none;"></textarea>
								<div class="button" onclick="postWarningPlus()"
									style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px; float: right; margin-right: 10px; ">
									경고</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<!-- remove modal -->
	<div class="modal" id="postModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span style="font-size: 30px; font-weight: bold;">블라인드 내용</span>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	
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
							<div class="content" input-modal style="float: right;">
								<textarea name="" id="" cols="90" rows="7"
									style="border: none; margin-left: 1%; outline: none; resize: none;"></textarea>
								<div class="button" onclick="replyStatusChange()"
									style="background-color: #3278FE; color: white; width: 60px; height: 37px; border-radius: 5px; text-align: center; line-height: 35px; float: right; margin-right: 10px; ">
									블라인드</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<!-- remove content modal -->
	<div class="modal" id="postModal3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span style="font-size: 30px; font-weight: bold;">블라인드 내용</span>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<section id="modal-section">
						<!-- <div>
						<div class="info input-modal">
						</div>
						<div class="info input-modal">
						</div>
					</div> -->
						<div>
							<div class="content" input-modal>
								<div class="container">
								</div>
	
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		const contextPath = "${pageContext.servletContext.contextPath}"
		const checkBox = document.getElementsByClassName("check")
		
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
	<script src="${pageContext.servletContext.contextPath}/resources/js/adminReply.js"></script>
</body>
</html>