const signUpCheckObj = {
    "email": false,
    "name": false,
    "pw": false,
    "pw1": false
}


/* 이메일 유효성 중복확인 */

$("#searchPw-email").on("input", function () {

    const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
    const inputEmail = $(this).val();

    if (inputEmail.length == 0) {
        $("#checkEmail").text("");
        signUpCheckObj.email = false;
    } else if (regExp.test(inputEmail)) {

        $.ajax({

            url: "emailDupCheck",
            type: "GET",
            data: { "inputEmail": inputEmail },
            success: function (result) {
                if (result != 0) {
                    $("#checkEmail").text("등록된 이메일입니다.").css("color", "green");
                    signUpCheckObj.email = true;

			
                } else {
                    $("#checkEmail").text("등록되지 않은 이메일입니다.").css("color", "red");
                    signUpCheckObj.email = false;
                }
            },
            error: function (request, status, error) {


            },
            complite: function () {

            }
        });


		
    } else {
        $("#checkEmail").text("잘못된 이메일 형식입니다.").css("color", "red");
        signUpCheckObj.email = false;
    }

});

function emailput(){
	const inputEmail = $("#searchPw-email")
    if(signUpCheckObj.email == true){
        $.ajax({
            url : "sendMail/sendMail",
            data : {"inputEmail" : inputEmail},

            success: function(){

            }



        });
		alert("임시번호가 전송되었습니다.");
	}else if(signUpCheckObj.email == false){
		alert("이메일을 확인해주세요")
	}
	
}