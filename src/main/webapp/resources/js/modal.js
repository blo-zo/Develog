console.log("js 연결 확인");

function reportDetailContent(e){
    // console.log(e.previousElementSibling);
    const reportNo = e.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.innerText
    console.log(reportNo);
    const inputModal = document.getElementsByClassName("input-modal");
    $.ajax({
        url : contextPath + "/admin/report/modal",// 절대 경로로 해야 하나?
        data : {"reportNo" : reportNo},
        type : "GET",
        dataType : "JSON",
        success : function(report){
            console.log(report)				
            console.log(inputModal)				
            inputModal[0].innerHTML = report.reportNo +'번 ' + report.reportType + '<br> 유형 : <span>'+report.reportStatusName+'</span>' 
            inputModal[1].innerHTML = 'userid@email.com <br>' +report.createDate+ '<br> 미해결'
            inputModal[2].innerText = report.reportContent 
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
     }

    })
}

function enquiryDetailContent(e){
    const enquiryNo = e.previousElementSibling.previousElementSibling.previousElementSibling.innerText
    console.log(enquiryNo);
    const inputModal = document.getElementsByClassName("input-modal");
   console.log(inputModal);
    $.ajax({
        url : contextPath + "/admin/enquiry/modal", // 절대 경로로 해야 하나?
        data : {"enquiryNo" : enquiryNo},
        type : "GET",
        dataType : "JSON",
        success : function(enquiry){
            console.log(enquiry)				
            console.log(inputModal)				
            inputModal[0].innerHTML = 'userid@email.com <br>  작성일 : ' + enquiry.createDate+ '<br> 수정일 : '+enquiry.modifyDate +'<br> 미답장' 
            inputModal[1].innerHTML = enquiry.enquiryContent
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
            console.log(status);
            console.log(error);
     }

    })
}

function warningPlus(){
    const textarea = document.getElementsByTagName("textarea")[0];
    const content = textarea.value;
    let memberNo = []
  for(const items of checkBox){
      if(items.checked){
          memberNo.push(items.parentElement.nextElementSibling.innerText) 
          
                  }
  }
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

function warningMinus(){
    let memberNo = []
  for(const items of checkBox){
      if(items.checked){
          memberNo.push(items.parentElement.nextElementSibling.innerText) 
          
                  }
  }
  console.log(memberNo)
  $.ajax({
      url : contextPath + "/admin/member/warningMinus",
      traditional : true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
      data : {"memberNo" : memberNo},
      type : "GET",
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

function warningContent(e){
    const memberNo = e.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.innerText
    console.log(memberNo);
//     const inputModal = document.getElementsByClassName("input-modal");
    // $.ajax({
    //     url : contextPath + "/admin/member/violation/content",// 절대 경로로 해야 하나?
    //     data : {"memberNo" : memberNo},
    //     type : "GET",
    //     dataType : "JSON",
    //     success : function(report){
    //         console.log(report)				
    //         console.log(inputModal)				
    //         inputModal[0].innerHTML = report.reportNo +'번 ' + report.reportType + '<br> 유형 : <span>'+report.reportStatusName+'</span>' 
    //         inputModal[1].innerHTML = 'userid@email.com <br>' +report.createDate+ '<br> 미해결'
    //         inputModal[2].innerText = report.reportContent 
    //     },
    //     error : function(req, status, error){
    //         console.log("ajax 실패");
    //         console.log(req.responseText);
    //  }

    // })
}