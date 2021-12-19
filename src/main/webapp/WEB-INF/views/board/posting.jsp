<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>posting</title>
<link rel="stylesheet" href="../resources/css/postingPage.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   
    <!-- include libraries(jQuery, bootstrap) --> 
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
     
     <!-- include summernote css/js-->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet"> 
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
   <!--  <link rel="stylesheet" href="/css/summernote/summernote-lite.css"> -->

	
</head>


<body>

	<form action="insert" method="post" enctype="multipart/form-data" role="form" onsubmit="return boardValidate();">
	<div class="wrapper">
		<div class="write-area" style="max-width: 80%;">
			<div class="head-title">
				<textarea id="head-textarea" class="head-textarea" name="postTitle"
					placeholder="제목을 입력하세요"></textarea>
			</div>
			<!-- head title -->

			<div class="line"></div>

			<div class="content">
				<textarea id="summernote" name="postContent" style="height: fit-content;"></textarea>
			</div>
			<!-- content end -->

			<div class="tag-area">
				<input type="text" class="inputTag" id="inputTag" name="inputTag"
					placeholder="태그를 입력하세요">

				<!-- 바뀌면 생성될 태그 -->
				<!-- <div class="post-tags">
                            
                    <div class="post-tag">
                        <span>샘플 태그 1</span>
                    </div>

                    <div class="post-tag">
                        <span>샘플 태그 2</span>
                    </div>

                    <div class="post-tag">
                        <span>샘플 태그 3</span>
                    </div>

                    <div class="post-tag">
                        <span>샘플 태그 4</span>
                    </div>

                </div> -->

			</div>
			<!-- tag-area -->
		</div>
		<!-- write area -->

	




		<footer style="display: flex;">
			<button class="out-area" onclick="location.href='#' ">
				<div class="out-image" id="out-image">
					<img src="../resources/images/arrow.png"  class="img-arrow" id="img-arrow">
				</div>
				<span class="out-span" id="out-span">나가기</span>
			</button>
			<!-- /out-area -->

			<div class="btn-area">
				<button class="btn-temp">임시저장</button>
				<button class="btn-pre-submit" id="btn-pre-submit" type="button">출간하기</button>
			</div>

		</footer>
	</div>
	<!-- wrapper -->

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
				<div class="thumb-img">
					<img src="https://via.placeholder.com/350x200" alt="샘플이미지">
				</div>
				<div class="thumb-text">
					<textarea class="thumb-textarea" id="" rows="6"
						placeholder="내용을 입력하세요"></textarea>
				</div>
			</div>
			<!-- /thumbnail-area -->

			<div class="modalLine"></div>

			<div class="set-area">
				<div class="open">

					<div class="open-title">
						<h4>공개 설정</h4>
					</div>
					<div class="open-btns">
						<button class="all-btn" name="postStatusCode" value="001">
							<img src="../resources/images/earth.png" class="img-earth" alt="">
							<p>전체 공개</p>
						</button>
						<button class="lock-btn">
							<img src="../resources/images/padlock.png" class="img-lock" alt="">
							<p>비공개</p>
						</button>
					</div>
				</div>
				<div class="category">
					<div class="category-title">
						<h4>카테고리 설정</h4>
					</div>
					<div class="category-input">
						<div class="sort-post dropstart border" data-bs-toggle="dropdown"
							aria-expanded="false">
							<select name="categoryCode">
							<option value="">선택하세요</option>
							<option value="001">일상</option>
								<option value="002">영어</option>
							</select>
							<!-- <button type="button" class="btn dropdown-toggle"
								data-bs-toggle="dropdown" aria-expanded="false">정렬 방식</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">날짜(오름차순)</a></li>
								<li><a class="dropdown-item" href="#">날짜(내림차순)</a></li>
							</ul> -->
						</div>
					</div>

				</div>
				<div class="set-btns">
					<button class="btn-cancel" >취소</button>

					<button class="btn-submit">출간하기</button>
				</div>

			</div>

		</div>
	</div>
</form>
	
	
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> 
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<sript src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="https://unpkg.com/js-offcanvas@1.2.8/dist/_js/js-offcanvas.pkgd.min.js"></script> 
<link href="https://unpkg.com/js-offcanvas@1.2.8/dist/_css/prefixed/js-offcanvas.css" rel="stylesheet">


<script src="../resources/js/posting.js"></script>
</body>
</html>