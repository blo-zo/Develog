console.log("adminReply.js");

function replyStatusChange() {
    const textarea = document.getElementsByTagName("textarea")[1];
    const content = textarea.value;
    console.log(content);
    let replyNo = []
    for (const items of checkBox) {
        if (items.checked) {
            replyNo.push(items.parentElement.nextElementSibling.innerText)

        }
    }
    replyNo.push(content)
    console.log(replyNo);
    $.ajax({
        url: contextPath + "/admin/reply/blind",
        traditional: true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
        data: { "replyNo": replyNo },
        type: "GET",
        async: false, // 비동기 방식을 동기 방식으로 변환 // 결과적으로 페이지를 새로고침하게 해준다.
        success: function (message) {
            location.reload()
            alert(message)
        },

        error: function (req, status, error) {
            console.log("ajax 실패");
            console.log(req.responseText);
            console.log(status);
            console.log(error);
        }

    })
}

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

function blindContent(replyNo){

    const container = $(".container").eq(0); //jQuery js랑 섞여서 문제가 생겼다 
    console.log(container);
    $.ajax({
        url : contextPath + "/admin/reply/blindContent",// 절대 경로로 해야 하나?
        data : {"replyNo" : replyNo},
        type : "GET",
        dataType : "JSON",
        success : function(reply){
            
            container.html("")
            console.log(reply);// 여기서 memberNo는 DELETE_POST_NO 즉, 고유번호를 가리킨다.
                const str = 'No. <span>'+reply.replyNo+'</span>' +' ('+reply.replyCreateDate+')'+ '  :  ' +reply.replyContent+ '<span onclick="restoreReply(this)" class="removeViolation" style="float:right;">복구</span><span style="display:none">'+reply.postNo+'</span>'
                const div = $('<div class="col">')
                            .html(str)
                container.append(div)
           
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
            console.log(status);
            console.log(error);
     }

    })
}

function restoreReply(e){
    const replyNo = e.nextElementSibling.innerText
    console.log(replyNo);
    $.ajax({
        url : contextPath + "/admin/reply/restoreReply",
        traditional : true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
        data : {"replyNo" : replyNo},
        type : "GET",
        async : false, // 동기 방식으로 변환
        success : function(){
            alert("댓글이 복구 되었습니다.")
            location.reload()
                  },
                    
        error : function(req, status, error){
                    console.log("ajax 실패");
                    console.log(req.responseText);
                    console.log(status);
                    console.log(error);
                }
                    
    })

}