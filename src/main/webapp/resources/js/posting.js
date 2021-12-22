// 섬머노트 기본
$('#summernote').summernote({
    height: 800,                     // 에디터 높이
    minHeight: 800,             // set minimum height of editor
    maxHeight: 800,             // set maximum height of editor
    focus: true,                      // 에디터 로딩후 포커스를 맞출지 여부
    lang: "ko-KR",					// 한글 설정   

	// 이미지 업로드 이벤트가 발생했을 때 
	callbacks:{
	   onImageUpload: function(files, editor) {
	        // 업로드된 이미지를 ajax를 이용하여 서버에 저장
	        sendFile(files[0], this);
	   }
	}
});




// 섬머노트에서 업로드된 이미지를 ajax를 이용하여 서버로 전송하여 저장하는 함수
function sendFile(file, editor){
   
   form_data = new FormData();
   // FormData : form 태그 내부 값 전송을 위한  k:v 쌍을 쉽게 생성할 수 있는 객체
   
   form_data.append("uploadFile", file);
   // FormData 객체에 새로운 K, V 를 추가
   
   $.ajax({
      url : "insertImage",
      type : "post",
      data : form_data,
      //dataType: "json",
      enctype: "multipart/form-data",
      cache : false,
        contentType : false,
        // contentType : 서버로 전송되는 데이터의 형식 설정
        // 기본값  : application/x-www-form-urlencoded; charset=UTF-8
        // 파일 전송 시 multipart/form-data 형식으로 데이터를 전송해야 하므로
        // 데이터의 형식이 변경되지 않도록 false로 지정.
        
        processData : false,
        // processData : 서버로 전달되는 값을 쿼리스트링으로 보낼경우 true(기본값), 아니면 false
        //            파일 전송 시 false로 지정 해야 함.
        
      success : function(result){
           var contextPath = location.pathname.substring(0, window.location.pathname.indexOf("/",2));
		console.log
         
         // 저장된 이미지를 에디터에 삽입
         //$(editor).summernote('editor.insertImage', contextPath + result.filePath + result.changeFileName);
         $(editor).summernote('editor.insertImage', result);
         

         
      }
      
   });
}


// 글 삽입 유효성 검사
function postValidate() {
	if ($("#head-textarea").val().trim().length == 0) {
		alert("제목을 입력해 주세요.");
		$("#head-textarea").focus();
		return false;
	}
	
	if ($("#summernote").val().trim().length == 0) {
		alert("내용을 입력해 주세요.");
		$("#summernote").focus();
		return false;
	}
	
	
	// 입력된 태그를 form 태그 마지막에 hidden 타입으로 추가
	$(".tags").each(function(index, tag){
		const input = $("<input type='hidden' name='tags'>").val($(tag).text());
		$("[name=insertForm]").append(input);
	})
	
	
	
	document.insertForm.submit();// form태그 name값이 제출이 되게 지정
}




/* 글 삽입 - insert -- 얘는 사용 x*/
/*function createInsert(){
	
	
	$.ajax({
        url : contextPath + "/board/insert",
        data : {
				"postTitle" : $(".head-textarea").val(), 
				"postContent": $("#summernote").val(),
				"categoryCode" : $("select[name='categoryCode'] option:selected").text(),
				"postStatusCode" : 001
				},
        type : "POST",
        success : function(result){
            // console.log(result);

            if(result > 0) {
                alert("글 삽입 성공");
                $("#replyContent").val(""); // 작성한 댓글 내용 삭제 

                selectReplyList(); // 댓글 조회 함수를 호출하여 댓글 화면 다시 만들기
            } else{
                alert("글 삽입 실패");

            }
        },
        error : function(req, status, error){
            alert("글 삽입 실패");
            console.log(req.responseText);
        }});
}
*/

// 1. 입력 버튼이 클릭 되었을 때
document.getElementById("inputTag").addEventListener("click", postTags);

// 2. input태그에서 엔터가 눌러졌을때
document.getElementById("inputTag").addEventListener("keyup", function(e){
    // e : 발생된 이벤트와 관련된 정보가 모두 담겨있음.
    console.log(e.code);
    if(e.key == "Enter"){ //엔터키 입력 시 
        postTags(); // 함 수를 호출하여 입력한 내용을 추가
    }
	else if(e.code == "Backspace"){
        if(e.target.value.length == 0){
            $("#postTags > span:last-child").remove();
        }
    }
});


//3. 1,2 번의 공통 동작을 작성해둔 function 생성
function postTags(){
    // #input-text에 작성된 값을 읽어오기
    const input = document.getElementById("inputTag");

    //입력된 값이 있을 때만 추가
    if(input.value.trim().length != 0){   
        // p태그 형식으로 추가
        document.getElementById("postTags").innerHTML 
            += "<span class='tags post-tag' >#" + input.value +"<b class='del' onclick='deleteTag(this)'>X</b></span>";


        //3) input 태그에 작성된 내용을 삭제
        input.value = "";

        // 4) input에 포커스 맞추기
        input.focus();
    }

}

/* 태그 x버튼 선택했을때*/
function deleteTag(xBtn){
    $(xBtn).parent().remove();
}


/* 게시글 상태 코드*/
$(".postStatusBtn").on("click", function () {
	$(".postStatusBtn").removeClass("active");
	$(this).addClass("active");
	$("input[name='postStatusCode']").val($(this).val());
})


// 글작성 -출간하기 버튼 (모달)
document.getElementById("btn-pre-submit").onclick = function(){
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
$(".modal-content-area .btn-cancel").on("click", function () {
  $(".modal-content-area").removeClass("active");
})


