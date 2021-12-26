
/* TAB */
const tabArr = document.getElementsByClassName("tab");

for(let i=0; i < tabArr.length ; i++){
    
    tabArr[i].addEventListener("click", function(){

        for(tab of tabArr){
            tab.classList.remove("select-tab");
        }
        this.classList.add("select-tab");

        contentArr = document.getElementsByClassName("content")

        for(content of contentArr){
            content.style.display = "none";
        }
        contentArr[i].style.display = "block";
		
		// 저장 버튼 없애버리기
		if($(".tab").eq(3).hasClass("select-tab")){
			$(".btn-submit").hide();
		} else if($(".tab").eq(2).hasClass("select-tab")){
			$(".btn-submit").hide();
		} else{
			$(".btn-submit").show();
		}
    });
} 

// 썸네일 영역파일이 클릭되었을때 
$(function() {
	$("#p-choose").on("click", function() {
		$("#memberImg").click();
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
			$(".profileImg > img").addClass('member-img-size');
			$(".profileImg").children("img").attr("src", e.target.result);

			// div 박스.이벤트가 발생한 결과값 뱉어 내라 src="여기에 넣어라"
		}

	}
}


// 이미지 제거버튼 클릭시 디폴트 값으로 변경
$(function() {
	$("#p-delete").on("click", function() {
		changeIMG();
	});
});

function changeIMG(){
	//img 태그의 아이디로 변경 할 때
	$(".profileImg").children("img").attr("src", "../resources/images/common/user.png");
    
}

// 저장
function pofileValidate() {
	console.log("submit");
	document.updateForm.submit();
}

function secession(){
	const secessionCheck = document.getElementById("agree")
	if(secessionCheck.checked){
		if(confirm("정말로 탈퇴하시겠습니까?")){

			$.ajax({
				
					url : contextPath + "/member/secession",
					data : {"memberNo" : memberNo},
					type : "GET",
					success : function(message){
								alert(message)
								if(message == "회원탈퇴를 성공했습니다."){
									location.replace(contextPath)
									
								}else{

								}
							  },
								
					error : function(req, status, error){
								console.log("ajax 실패");
								console.log(req.responseText);
								console.log(status);
								console.log(error);
							}
								
				
			})
		}
	}
}
