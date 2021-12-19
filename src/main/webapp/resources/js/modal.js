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