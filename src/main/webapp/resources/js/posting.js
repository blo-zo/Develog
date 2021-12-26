// 섬머노트 기본
$('#summernote').summernote({
	height: 700,                     // 에디터 높이
	minHeight: 700,             // set minimum height of editor
	maxHeight: 700,             // set maximum height of editor
	// focus: true,                      // 에디터 로딩후 포커스를 맞출지 여부
	lang: "ko-KR",					// 한글 설정   

	// 이미지 업로드 이벤트가 발생했을 때 
	callbacks: {
		onImageUpload: function(files, editor) {
			// 업로드된 이미지를 ajax를 이용하여 서버에 저장
			sendFile(files[0], this);
		}
	}
});




// 섬머노트에서 업로드된 이미지를 ajax를 이용하여 서버로 전송하여 저장하는 함수
function sendFile(file, editor) {
	

	form_data = new FormData();
	// FormData : form 태그 내부 값 전송을 위한  k:v 쌍을 쉽게 생성할 수 있는 객체

	form_data.append("uploadFile", file);
	// FormData 객체에 새로운 K, V 를 추가
	
	$.ajax({
		url: "insertImage",
		type: "post",
		data: form_data,
		enctype: "multipart/form-data",
		cache: false,
		contentType: false,
		processData: false,

		success: function(result) {
			var contextPath = location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
			console.log(result);

			// 저장된 이미지를 에디터에 삽입
			$(editor).summernote('editor.insertImage', result);


		}

	});
}


// 글 삽입 유효성 검사
function postValidate() {
	if ($("#head-textarea").val().trim().length == 0) {
		alert("제목을 입력해주세요.");
		$("#head-textarea").focus();
		return false;
	}

	if ($("#summernote").val().trim().length == 0) {
		alert("내용을 입력해주세요.");
		$("#summernote").focus();
		return false;
	}

	if($(".openBtn.active").length == 0) { // 공개여부의 값이 없다면
		alert("공개여부를 선택해주세요.");
		return false;
	}
	
	if($(".thumb-img-area > img").attr("src", result) == "../resources/images/common/thumbnail.jpg"){
		alert("썸네일을 선택해주세요.");
		
		console.log("e.target.result" + e.target.result);
		console.log("value" + value);
		return false;
	}

	// 입력된 태그를 form 태그 마지막에 hidden 타입으로 추가
	$(".tags").each(function(index, tag) {
		const input = $("<input type='hidden' name='tags'>").val($(tag).text());
		$("[name=insertForm]").append(input);
	})

	document.insertForm.submit();// form태그 name값이 제출이 되게 지정
}



//  input태그에서 키보드가 눌러졌을때
document.getElementById("inputTag").addEventListener("keyup", function(e) {
	// e : 발생된 이벤트와 관련된 정보가 모두 담겨있음.
	console.log(e.code);
	if (e.code == "Enter") { //엔터키 입력 시 
		let input = e.target;
		input.value = XSSCheck(input.value, 1);

		postTags(input); // 함수를 호출하여 입력한 내용을 추가
	}
	else if (e.code == "Space") {
		changeSpace();
	}
	else if (e.code == "Backspace") {
		if (e.target.value.length == 0) {
			$("#postTags > span:last-child").remove();
		}
	}
});










//3. 1,2 번의 공통 동작을 작성해둔 function 생성
function postTags(input) {
	// #input-text에 작성된 값을 읽어오기

	//입력된 값이 있을 때만 추가 (space도 추가된값)
	if (input.value.trim().length != 0) {

		// p태그 형식으로 추가
		document.getElementById("postTags").innerHTML
			+= "<span class='tags post-tag' >#" + input.value + "<b class='del' onclick='deleteTag(this)'>X</b></span>";

		//3) input 태그에 작성된 내용을 삭제
		input.value = "";

		// post-tag가 10개넘어가면 삭제
		if (document.getElementsByClassName("post-tag").length > 10) {
			$("#postTags > span:last-child").remove();
		}

		// 4) input에 포커스 맞추기
		document.getElementById("inputTag").focus();
	}

}


// 화면 XSS처리 
function XSSCheck(str, level) {

	if (level == undefined || level == 0) {
		str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-|\s/g, "");
	} else if (level != undefined && level == 1) {
		str = str.replace(/\</g, "&lt;");
		str = str.replace(/\>/g, "&gt;");
	}
	return str;
}




/* 태그 x버튼 선택했을때*/
function deleteTag(xBtn) {
	$(xBtn).parent().remove();
}

// 공백제거
function changeSpace() {
	const input = document.getElementById("inputTag");
	input.value = input.value.slice(0, -1);
}

/* 게시글 상태 코드*/
$(".postStatusBtn").on("click", function() {
	$(".postStatusBtn").removeClass("active");
	$(this).addClass("active");
	$("input[name='postStatusCode']").val($(this).val());
})


// 글작성 -출간하기 버튼 (모달)
document.getElementById("btn-pre-submit").onclick = function() {
	// 모달 클래스에 active클래스 포함 되있을때 : 모달창 보이고있음
	if ($(".modal-content-area").hasClass("active")) {
		$(".modal-content-area").removeClass("active");
	}
	// 모달 클래스에 active클래스 없을때 : 모달창 안보이고 있음
	else {
		$(".modal-content-area").addClass("active");
	}
};

// 모달 취소 버튼 클릭시
$(".modal-content-area .btn-cancel").on("click", function() {
	$(".modal-content-area").removeClass("active");
})



// 썸네일 클릭과 섬머노트 자체의 클릭 

// 썸네일 영역파일이 클릭되었을때 
$(function() {
	$(".thumb-img-area").on("click", function() {
		$("#thumbImg").click();
	});
});

// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
function loadImg(value) {

	// 파일이 선택된 경우 true
	if (value.files && value.files[0]) { // 확실하게 하기 위해  && value.files[0]

		var reader = new FileReader();

		// 선택된 파일 읽기 시작
		reader.readAsDataURL(value.files[0]);

		// 다 읽은 경우
		reader.onload = function(e) {

			// 사이즈 지정 필요  
			$(".thumb-img-area > img").addClass('thumb-img-size');
			$(".thumb-img-area").children("img").attr("src", e.target.result);

			// div 박스.이벤트가 발생한 결과값 뱉어 내라 src="여기에 넣어라"
		}

	}
}