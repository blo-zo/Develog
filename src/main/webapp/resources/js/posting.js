// 섬머노트 기본
$('#summernote').summernote({
	  height: 900,                     // 에디터 높이
	  focus: true,                      // 에디터 로딩후 포커스를 맞출지 여부
	  lang: "ko-KR"						// 한글 설정   
});


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
                alert("댓글 삽입 성공");
                $("#replyContent").val(""); // 작성한 댓글 내용 삭제 

                selectReplyList(); // 댓글 조회 함수를 호출하여 댓글 화면 다시 만들기
            } else{
                alert("댓글 삽입 실패");

            }
        },
        error : function(req, status, error){
            alert("댓글 삽입 실패");
            console.log(req.responseText);
        }});

}