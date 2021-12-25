<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application" />


<title>포스트 수정하기</title>


<link rel="stylesheet" href="${contextPath}/resources/css/posting.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   
    <!-- include libraries(jQuery, bootstrap) --> 
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
     
     <!-- include summernote css/js-->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet"> 
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
  <%--  <link rel="stylesheet" href="${contextPath}/css/summernote/summernote-lite.css">  --%>
<style>
	.thumb-img-area > img{
	  opacity:0.7;
	  max-width: 350px;
	  max-height: 200px;
	  object-fit: cover;
	}
</style>
</head>

<body>

	<form action="update" enctype="multipart/form-data" name="insertForm" method="post"  role="form" onsubmit="return false">
	<div class="wrapper">
		<div class="write-area" style="max-width: 80%;">
			<div class="head-title">
				<textarea id="head-textarea" class="head-textarea" name="postTitle" placeholder="제목을 입력하세요" maxlength="200" >${post.postTitle}</textarea>
			</div><!-- head title -->

			<div class="line"></div>

			<textarea class="summernote" id="summernote" name="postContent" style=" height: fit-content;">${post.postContent}</textarea>
			<!-- content end -->

			<div class="tag-area">
                <div class="postTags" id="postTags">
                	
                	<c:forEach items="${tagList}" var="tag">
                		<span class="tags post-tag">${tag.tagName}<b class="del" onclick="deleteTag(this)">X</b></span>
                	</c:forEach>
                
                </div>  <!-- 태그 생성될 영역 -->
                <input type="text" class="inputTag" id="inputTag" placeholder="태그를 입력하세요" maxlength="15" >
            </div> <!-- tag-area -->
            
		</div>
		<!-- write area -->

		<footer>
            <button type="button" class="out-area" onclick="location.href='${contextPath}/blog/${loginMember.memberNm}/view/?pno=${post.postNo}&cp=${param.cp}'">
                <div id="out-image">
                    <img src="${contextPath}/resources/images/boardIcon/arrow.png" id="img-arrow" >
                </div>
                 <span id="out-span" >나가기</span>
            </button> <!-- /out-area -->
            
            <div class="btn-area">
                <button class="btn-pre-submit" id="btn-pre-submit" type="button"> 
                    <a style=" color: white; text-decoration : none;">수정하기</a>
                </button>
            </div>    
    
        </footer>
	</div><!-- wrapper -->

	<!-- 
        지금은 보이고 안보이고 정도만 작성
        나중에 움직이는걸로 바꾸고 싶으면 transform 했던거 찾으면 된다 
    -->


	<!-- modal slide up -->
	<div class="modal-content-area">
		<div class="modal-content-box">

			<div class="thumbnail-area">
				<div class="thumbnail-title">
					<h4>썸네일 설정</h4>
				</div>
				<div class="thumb-img-area">
					
					<c:choose>
						<c:when test="${empty thumbImg}">
							<%-- 디폴트 이미지 --%>
							<img name="thumbimg" src="${contextPath}/resources/images/KYJ/emptyImage.png" style="opacity:0.7;">
						</c:when>
						<c:otherwise>
							<img name="thumbimg" src="${contextPath}${thumbImg.postImgPath}${thumbImg.postImgName}" alt="썸네일" style="">
						</c:otherwise>
					</c:choose>
					
				</div>
				<!--썸네일 값 -->
				<div id="fileArea">
					 <input type="file" name="img" onchange="loadImg(this)" id="thumbImg"> 
					
				</div>
			</div> <!-- /thumbnail-area -->

			<div class="modalLine"></div>

			<div class="set-area">
				<div class="open">
 
					<div class="open-title">
						<h4>공개 설정</h4>
					</div>
					<div class="open-btns" >
						<button class="all-btn postStatusBtn openBtn" name="openBtn" value="500" type="button">
							<img src="${contextPath}/resources/images/boardIcon/earth.png" class="img-earth" alt="">
							<p>전체 공개</p>
						</button>
						<button class="lock-btn postStatusBtn openBtn" name="openBtn"  value="502" type="button">
							<img src="${contextPath}/resources/images/boardIcon/padlock.png" class="img-lock" alt="">
							<p>비공개</p>
						</button>
						<!-- 상태코드 넘어가는 input -->
						<input type="hidden" name="postStatusCode" >
					</div>
				</div>
				<div class="category">
					<div class="category-title">
						<h4>카테고리 설정</h4>
					</div>
					<div class="category-input">
						<div class="sort-post dropstart border" data-bs-toggle="dropdown" aria-expanded="false">
							<select name="categoryCode" id="categoryCode">
								
								<c:forEach items = "${category}" var="c">
								
									<option value="${c.categoryCode}">${c.categoryName}</option>
								
								</c:forEach> 
							
							</select>
						</div>
					</div>

				</div>
				<div class="set-btns">
					<button class="btn-cancel" >취소</button>
					<button class="btn-submit" onclick="return postValidate();">수정하기</button>
				</div>

			</div>

		</div>
	</div>
	
	<%-- 업데이트 진행 시 사용할 글번호 --%>
	<input type="hidden" name="pno" value="${post.postNo}">
	
	
</form>
	
	
 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<sript src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="https://unpkg.com/js-offcanvas@1.2.8/dist/_js/js-offcanvas.pkgd.min.js"></script> 
<link href="https://unpkg.com/js-offcanvas@1.2.8/dist/_css/prefixed/js-offcanvas.css" rel="stylesheet">

<script src="${contextPath}/resources/js/posting.js"></script>
</body>
</html>