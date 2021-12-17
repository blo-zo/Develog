// 로그인 ng시 유효성 검사

function loginValidate(){
    const memberEmail = document.getElementById("email");
    const memberPw = document.getElementById("pw");

    if(memberEmail.value.trim().length == 0){
        alert("아이디를 입력해주세요");
        memberEmail.focus;
        return false;

    }
    if(memberPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요");
        memberPw.focus;
        return false;

    }

}