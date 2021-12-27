const signUpCheckObj = {
    "email": false,
    "name": false,
    "pw": false,
    "pw1": false
}
 
/* 유효성 검사 */
function validate() {
    for (key in signUpCheckObj) {
        if (!signUpCheckObj[key]) {
            let message;

            switch (key) {
                case "email": message = "이메일형식이 올바르지않습니다."; break;
                case "name": message = "이름형식이 올바르지않습니다."; break;
                case "pw": message = "비밀번호 형식이 올바르지않습니다."; break;
                case "pw1": message = "비밀번호 형식이 올바르지않습니다."; break;

            }
            alert(message);

            // document.getElementById(key).focus();
            return false;
        }
        
    } 
    if(confirm("회원가입 하시겠습니까?")){
        
        return;
    }else{
        return false;
    }

  

};
/* 닉네임 유효성 중복 확인 */
$("#signUp-name").on("input", function () {

    const inputName = $(this).val();  // 입력 받은 이름
    const regExp = /^[가-힣]{2,10}$/;

    if (inputName.length == 0) {
        $("#checkName").text("");
        signUpCheckObj.name = false;
    } else if (regExp.test(inputName)) {

        $.ajax({

            url: "nameDupCheck",
            type: "GET",
            data: { "inputName": inputName },
            success: function (result) {
                if (result == 0) {
                    $("#checkName").text("사용가능한 닉네임입니다.").css("color", "green");
                    signUpCheckObj.name = true;
                } else {
                    $("#checkName").text("중복되는 닉네임입니다.").css("color", "red");
                    signUpCheckObj.name = false;
                }
            },
            error: function (request, status, error) {


            },
            complite: function () {

            }
        });
    } else {
        $("#checkName").text("닉네임은 한글로 두글자이상 5글자이하로 작성해주세요.").css("color", "red");
        signUpCheckObj.name = false;
    }

});



/* 이메일 유효성 중복확인 */
$("#signUp-email").on("input", function () {

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
                if (result == 0) {
                    $("#checkEmail").text("사용가능한 이메일입니다.").css("color", "green");
                    signUpCheckObj.email = true;
                } else {
                    $("#checkEmail").text("이메일이 중복됩니다.").css("color", "red");
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

/* 비밀번호 유효성 검사 */
$("#pwd1").on("input", function () {

    const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
    const inputPwd1 = $(this).val();

    if (inputPwd1.length == 0) {
        $("#checkPwd1").text("");
        signUpCheckObj.pw1 = false;
    } else if (regExp.test(inputPwd1)) {
        $("#checkPwd1").text("알맞은 비밀번호 형식입니다.").css("color", "green");
        signUpCheckObj.pw1 = true;
    } else {
        $("#checkPwd1").text("잘못된 비밀번호 형식입니다.").css("color", "red");
        signUpCheckObj.pw1 = false;
    }

});
// ---------------------------------------비밀번호 유효성검사-------------------------------------------------------------

// 비밀번호 유효성 검사
// 영어 대/소문자, 숫자, 특수문자(!,@,#,-,_) 6~20글자 
$("#signUp-pw").on("input", function () {

    const regExp = /^[a-zA-z\d\!\@\#\-\_]{6,20}$/;
    const inputPw = $(this).val();
    const checkPw = $("#checkPw");
    if (inputPw.trim().length == 0) {
        checkPw.innerText = "";
        signUpCheckObj.pw = false;
    } else if (regExp.test(inputPw)) {
        $("#checkPw").text("알맞은 비밀번호 형식입니다.").css("color", "green");
        signUpCheckObj.pw = true;
    } else {
        $("#checkPw").text("잘못된 비밀번호 형식입니다.").css("color", "red");
        signUpCheckObj.pw = false;
    }

});

$("#signUp-pw1, #signUp-pw").on("input", function () {
    const pw = $("#signUp-pw").val();
    const pw1 = $("#signUp-pw1").val();
    const checkPw1 = $("#checkPw1");


    if (pw1.trim().length == 0) {
        checkPw1.text("");
        signUpCheckObj.pw1 = false;



    } else if (pw == pw1) {
        checkPw1.text("비밀번호가 일치합니다").css("color", "green");
        signUpCheckObj.pw1 = true;
    } else {
        checkPw1.text("비밀번호가 일치하지않습니다.").css("color", "red");
        signUpCheckObj.pw1 = false;
    }

});


function signUpSmi(){
    if(confirm("로그아웃 하시겠습니까")){
        return;

    }else{
        return false;
    }
}

