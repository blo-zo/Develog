console.log("modal.js");

function restore(memberNo){
    console.log(memberNo);
    if(confirm("탈퇴한 회원을 정말로 복구 하시겠습니까?")){
        $.ajax({
            url : contextPath + "/admin/member/restore",
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
}

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
            inputModal[0].innerHTML = '신고 회원 : ' + report.memberName+'<br>'+ '신고 대상 : '+ report.reportNo +'번 ' + report.reportType
            inputModal[1].innerHTML = report.createDate
            inputModal[2].innerText = report.reportContent 
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
     }

    })
}

function enquiryDetailContent(e){
    enquiryNo = e.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.innerText
    const title = e.parentElement.previousElementSibling.innerText
    $.ajax({
        url : contextPath + "/admin/enquiry/modal", // 절대 경로로 해야 하나?
        data : {"enquiryNo" : enquiryNo},
        type : "GET",
        dataType : "JSON",
        success : function(enquiry){
            console.log(enquiry);
            inputModal[0].innerHTML = '제목 : '+enquiry.enquiryTitle+' <br>  작성일 : ' + enquiry.createDate+ '<br> 미답장' 
            inputModal[1].innerHTML = enquiry.enquiryContent.replace(/(<([^>]+)>)/gi, "")
            
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
            console.log(status);
            console.log(error);
         }

    })
}

function enquirySend(){
    const content = inputModal[1].value
    $.ajax({
        url : contextPath + "/admin/enquiry/send",
        data : {"enquiryNo" : enquiryNo, "content" : content},
        type : "GET",
        success : function(message){
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

function warningPlus(){
    const textarea = document.getElementsByTagName("textarea")[0];
    const content = textarea.value;
    
    // 아 이게 있어야 실행이 되는 거네 왜냐하면 클릭한 순간에 함수가 발동하니까
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

function warningMinus(e){
    const violationNo = e.previousElementSibling.innerText
    const memberNo = e.nextElementSibling.innerText
  $.ajax({
      url : contextPath + "/admin/member/warningMinus",
      traditional : true, //이게 뭔지는 모르겠는데 배열을 넘겨준데
      data : {"violationNo" : violationNo},
      type : "GET",
      success : function(){
                warningContent(memberNo)
                },
                  
      error : function(req, status, error){
                  console.log("ajax 실패");
                  console.log(req.responseText);
                  console.log(status);
                  console.log(error);
              }
                  
  })
}

function warningContent(memberNo){

    //const container = document.getElementsByClassName("container")[0]
    // const memberNo = e.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.innerText
    const container = $(".container").eq(0); //jQuery js랑 섞여서 문제가 생겼다
//     const inputModal = document.getElementsByClassName("input-modal");
console.log(memberNo);
    $.ajax({
        url : contextPath + "/admin/violation",// 절대 경로로 해야 하나?
        data : {"memberNo" : memberNo},
        type : "GET",
        dataType : "JSON",
        success : function(violation){
            container.html("")
            $.each(violation, function(index, item){
                const str = '<span>'+item.reportNo+'</span>' + ' : ' + item.reportContent + '<span onclick="warningMinus(this)" class="removeViolation" style="float:right; font-weight: bold;">-</span> <span style="display:none">'+item.memberNo+'</span>'
                const div = $('<div class="col">')
                            .html(str)
                container.append(div)
            })
        },
        error : function(req, status, error){
            console.log("ajax 실패");
            console.log(req.responseText);
     }

    })
}


// 화면 XSS처리 
function XSSCheck(str, level) {

    if (level == undefined || level == 0) {
       str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-|\s/g, "");
    } else if (level != undefined && level == 1) {
       str = str.replace(/\</g, "&lt;");
       str = str.replace(/\>/g, "&gt;");
    }
    return str;
 }
 