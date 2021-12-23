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

function postStatusChange() {
    const textarea = document.getElementsByTagName("textarea")[1];
    const content = textarea.value;
    console.log("함수 확인");
    let postNo = []
    for (const items of checkBox) {
        if (items.checked) {
            postNo.push(items.parentElement.nextElementSibling.innerText)

        }
    }
    postNo.push(content)
    console.log(postNo);
    $.ajax({
        url: contextPath + "/admin/post/remove",
        traditional: true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
        data: { "postNo": postNo },
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

function removeContent(postNo){

    const container = $(".container").eq(0); //jQuery js랑 섞여서 문제가 생겼다 
    console.log(container);
    $.ajax({
        url : contextPath + "/admin/post/removeContent",// 절대 경로로 해야 하나?
        data : {"postNo" : postNo},
        type : "GET",
        dataType : "JSON",
        success : function(post){
            container.html("")
            console.log(post);// 여기서 memberNo는 DELETE_POST_NO 즉, 고유번호를 가리킨다.
                const str = 'No. <span>'+post.postNo+'</span>' +' ('+post.createDate+')'+ '  :  ' +post.postContent+ '<span onclick="restorePost(this)" class="removeViolation" style="float:right;">복구</span> <span style="display:none">'+post.memberNo+'</span>'
                const div = $('<div class="col">')
                            .html(str)
                container.append(div)
           
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
     }

    })
}

function restorePost(e){
    const postNo = e.previousElementSibling.innerText
    console.log(postNo);
    $.ajax({
        url : contextPath + "/admin/post/restorePost",
        traditional : true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
        data : {"postNo" : postNo},
        type : "GET",
        async : false, // 동기 방식으로 변환
        success : function(){
            alert("게시글이 복구 되었습니다.")
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