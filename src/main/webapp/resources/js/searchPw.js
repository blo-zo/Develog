const searchCheckObj = {
    "email": false,
    "pw": false,
	"pw1": false,
    "temp": false
}

function searchValidate(){
	

	
	for (key in searchCheckObj) {
        if (!searchCheckObj[key]) {
            let message;

            switch (key) {
                case "email": message = "이메일을 확인해주세요."; break;
                case "temp": message = "인증번호를 확인해주세요"; break;
                case "pw": message = "비밀번호 형식이 올바르지않습니다."; break;
                case "pw1": message = "새 비밀번호 형식이 올바르지않습니다."; break;

            }
            alert(message);

          
            return false;
        }
        
    } 
};

            


// 이메일 전송 인증	번호
function checkEmail(){
	$("#searchPw-email").val("");
	const email = $("#email1").val();
	const inputEmail = $("#searchPw-email");
	
	$("#searchPw-email").val($("#email1").val());
	
	
	if( searchCheckObj.email == true){
		$.ajax({
			url : "../sendMail/sendmail",
			type : "GET",
			data : {"email" : email},
			success : function(result){
				if(result != null){
					
					
					
					
					
					$("#sub_btn2").on("click" , function(){
						const pw = $("#temp").val();
						if($("#temp").val().trim().length == 0){
							searchCheckObj.temp = false;
							$("#checkPwd").text("");
							
							alert("인증번호를 입력해주세요.");
							
						}else if(pw == result){
							
							$("#checkPwd").text("인증번호가 일치합니다.").css("color","green");
							searchCheckObj.temp = true;
							
							alert("인증번호가 일치합니다.");
							
						}else{
							$("#checkPwd").text("인증번호가 일치하지않습니다.").css("color","red");
							
							searchCheckObj.temp = false;
							alert("인증번호가 일치하지않습니다.");
							
						}
						
					});
				}else{
					alert("인증번호를 다시 보내주세요");
				}
				
				
				}
			})
		}
};

$("#temp").on("input",function(){
	const temp = $("temp").val();
	if(temp.trim().length == 0 ){
		searchCheckObj.temp = false;
	}else{
		
	}	
	
});



/* 이메일 여부확인 */
function regEmailCheck() {
	
    const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
    const inputEmail = $("#email1").val();

    if (inputEmail.length == 0) {
        $("#checkEmail").text("");
        searchCheckObj.email = false;
    } else if (regExp.test(inputEmail)) {

        $.ajax({

            url: "emailDupCheck",
            type: "GET",
            data: { "inputEmail": inputEmail },
            success: function (result) {
                if (result != 0) {
                   
                    searchCheckObj.email = true;
				 	alert("인증번호가 전송되었습니다.");
					checkEmail();
					
                } else {
                
				 	alert("등록되지 않은 이메일입니다.");
                    searchCheckObj.email = false;
					return false;
					
                }
            },
            error: function (request, status, error) {
			

            },
            complite: function () {

            }
        });


		
    } else {
       
		alert("이메일을 확인해주세요");
		return false;
    ;
    }

	
	$("#exampleModal1").modal('hide');
};


/* 비밀번호 유효성 검사 */
$("#newPassword").on("input", function () {

    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
    const inputPwd = $(this).val();

    if (inputPwd.length == 0) {
        $("#checkPwd1").text("");
        searchCheckObj.pw = false;
    } else if (regExp.test(inputPwd)) {
        searchCheckObj.pw = true;
    } else {
        searchCheckObj.pw = false;
    }

});




// ---------------------------------------비밀번호 유효성검사-------------------------------------------------------------



$("#newPassword, #newPassword1").on("input", function () {
    const pw = $("#newPassword").val();
    const pw1 = $("#newPassword1").val();
    const checkPw1 = $("#checkPw1");


    if (pw1.trim().length == 0) {
        checkPw1.text("");
        searchCheckObj.pw1 = false;



    } else if (pw == pw1) {
        searchCheckObj.pw1 = true;
    } else {
        searchCheckObj.pw1 = false;
    }

});

  

