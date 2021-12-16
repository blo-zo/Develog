console.log("연결 확인");
// 로그인 시 유효성 검사
function loginValidate(){

    const memberId = document.getElementById("memberId");
    const memberPw = document.querySelector("#memberPw");

    // 아이디가 입력되지 않은 경우 
    // "아이디를 입력해주세요." 경고창 출력 후
    // 아이디 input으로 focus 이동
 
        if(memberId.value.trim().length == 0){
            alert("아이디를 입력해주세요.")
            memberId.focus();
            return false;
        }

        if(memberPw.value.trim().length == 0){
            alert("비밀번호를 입력해주세요.")
            memberPw.focus();
            return false;
        }
}

// 메인페이지 -> 아이디로 회원정보 조회(AJAX)
document.getElementById("idSearchBtn").addEventListener("click", function(){

    // 아이디를 입력 받을 input 요소 얻어오기
    const searchId = document.getElementById("searchId");

    // 입력 받은 아이디가 없을 경우
    if(searchId.value.trim().length ==0){
        alert("검색할 아이디를 입력해주세요.")
    
    }else{
        $.ajax({
            url : "member/idSearch", // 비동기 통신 요청 주소 (필수)
            data : {"inputId" : searchId.value}, // 파라미터

            dataType : "json", // dataType 속성 : 응답 데이터의 타입을 지정하는 속성
                               // -> JSON (JS 객체 형태 문자열) --> JS Object로 자동 변경
                               // gson은 객체를 받아서 json형태의 문자열을 만든다
                               // json는 JS 객체 형태의 문자열이다 객체가 아니다!
                               // dataType : "json"는 받아온 변수가 json이면 이를 js 객체로 바꿔준다.
            success : function(member){
                // console.log(member); 
                // console.log(member.memberId);
                // console.log(member.memberName);

                const tbody = document.getElementById("idSearchResult");

                tbody.innerHTML = "";

                if(member == null){ // 조회 결과가 없는 경우

                    const tr = document.createElement("tr");
                    const td = document.createElement("td");

                    // const text = document.createTextNode(inputId +"회원이 존재하지 않습니다")
                    td.innerText = searchId.value + "회원이 존재하지 않습니다.";

                    //조립
                    tr.append(td);
                    tbody.append(tr);

                } else { // 조회 결과가 있을 경우

                    for (key in member) {
                        // console.log(key);
                        // console.log(member[key]);

                        switch (key) {
                            case "memberId":
                            case "memberName":
                            case "memberPhone":
                            case "memberEmail":
                            case "memberAddress":

                                const tr = document.createElement("tr");
                                const th = document.createElement("th");
                                const td = document.createElement("td");

                                let title; // 행 제목
                                switch (key) {
                                    case "memberId": title = "아이디"; break;
                                    case "memberName": title = "이름"; break;
                                    case "memberPhone": title = "전화번호"; break;
                                    case "memberEmail": title = "이메일"; break;
                                    case "memberAddress": title = "주소"; break;
                                }

                                th.innerText = title;

                                td.innerText = member[key];

                                // 조립
                                tr.append(th, td);
                                tbody.append(tr)
                        }
                    }

                }

            },
            error : function(request, status, error){
                console.log("ajax 통신 중 오류 발생");
                console.log(request.responseText);
            }
        
        });

    }

})