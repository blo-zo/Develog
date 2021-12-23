function postWarningPlus(){
    const textarea = document.getElementsByTagName("textarea")[0];
    const content = textarea.value;
    
    // 아 이게 있어야 실행이 되는 거네 왜냐하면 클릭한 순간에 함수가 발동하니까
    let memberNo = []
  	for(const items of checkBox){
    if(items.checked){
         memberNo.push(items.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerText) 
          
                 }
  	}
      console.log(memberNo);
     memberNo.push(content)
    console.log(memberNo);
     $.ajax({
      url : contextPath + "/admin/member/warningPlus",
      traditional : true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
      data : {"memberNo" : memberNo},
      type : "GET",
      async : false, // 비동기 방식을 동기 방식으로 변환 // 결과적으로 페이지를 새로고침하게 해준다.
      success : function(message){
        location.reload()
        alert(message)
        },
                  
      error : function(req, status, error){
                  console.log("ajax 실패");
                  console.log(req.responseText);
                  console.log(status);
                  console.log(error);
              }
                  
  })

}