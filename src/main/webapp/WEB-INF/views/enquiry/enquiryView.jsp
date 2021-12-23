<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <title>문의사항</title>
 
     <link rel="stylesheet" href="${contextPath}/resources/css/enquiryView.css">
 
</head>
<body>
    
    <div class="enquiryView-head">
    <p>1:1 문의
    </div>
    
    
    <section>

        <div>
            <p>이메일 : ${sessionScope.loginMember.memberEmail}</p>
    
        </div>
        <div>
            <p>작성일 : ${enquiry.createDt}</p>
    
        </div>
     
    </section>
    <main>
       

			<form action="#">
			<div class="entitle">
			<label>문의 제목 </label><input id="enquiryTitle" name="enquiryTitle" maxlength="30" autocomplete="off"  placeholder="최대 30글자" readonly value="${enquiry.enquiryTitle}">
			</div>
            <div class="enquriy-context">
            <p>내용</p>
            <textarea name="enquiryContent" id="enquiryContent" style="resize: none;" readonly>${enquiry.enquiryContent}</textarea>
            </div>
      		<div class="btn-wrap">
          	<button type="button"  onclick="location.href = '${contextPath}/enquiry/list';">목록</button>
          	
      		</div>
			</form>
    </main>
    	
        <jsp:include page="../common/footer.jsp"></jsp:include>
 		
		<script src="${contextPath}/resources/js/enquiry.js"></script>
</body>
</html>