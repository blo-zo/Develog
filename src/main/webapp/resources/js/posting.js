// 섬머노트 기본
$('#summernote').summernote({
    height: 800,                     // 에디터 높이
    minHeight: 800,             // set minimum height of editor
    maxHeight: 800,             // set maximum height of editor
    focus: true,                      // 에디터 로딩후 포커스를 맞출지 여부
    lang: "ko-KR"					// 한글 설정   
});



/* 글 삽입 - insert */
function createPost(){
	$.ajax({
        url : contextPath + "/reply/insert",
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


// 1. 입력 버튼이 클릭 되었을 때
document.getElementById("inputText").addEventListener("click", postTags);

// 2. input태그에서 엔터가 눌러졌을때
document.getElementById("inputText").addEventListener("keyup", function(e){
    // e : 발생된 이벤트와 관련된 정보가 모두 담겨있음.
    console.log(e.code);
    if(e.key == "Enter"){ //엔터키 입력 시 
        postTags(); // 함수를 호출하여 입력한 내용을 추가
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
    const input = document.getElementById("inputText");

    //입력된 값이 있을 때만 추가
    if(input.value.trim().length != 0){   
        // p태그 형식으로 추가
        document.getElementById("postTags").innerHTML 
            += "<span class='tags post-tag'>#" + input.value +"<b class='del' onclick='deleteTag(this)'>X</b></span>";

        document.getElementById( "inputText" ).scrollWidth 

        document.getElementById("inputText").scrollTop
            = document.getElementById("inputText").scrollTop;

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

