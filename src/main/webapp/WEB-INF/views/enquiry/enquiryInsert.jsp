<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    <title>문의 작성</title>
 
     <link rel="stylesheet" href="${contextPath}/resources/css/enquiryInsert.css">
 
</head>
<body>
    
    <div class="enquiryInsert-head">
    <p>1:1 문의
    </div>
    
    
    <section>

        <div>
            <p>이메일 : ${sessionScope.loginMember.memberEmail}</p>
    
        </div>
     
    </section>
    <main>
       

			<form action="insert" method="POST" onsubmit="return insertEnquiry();">
			<div class="entitle">
			<label>문의 제목 </label><input id="enquiryTitle" name="enquiryTitle" maxlength="30" autocomplete="off"  placeholder="최대 30글자">
			</div>
            <div class="enquriy-context">
            <p>내용</p>
            <textarea name="enquiryContent" id="enquiryContent" style="resize: none;"></textarea>
            </div>
      		<div class="btn-wrap">
          	<button type="button"  onclick="location.href = '${contextPath}/enquiry/list';">이전</button>
          	<button>등록</button>
      		</div>
			</form>
    </main>
    	
        <jsp:include page="../common/footer.jsp"></jsp:include>
 		
		<script src="${contextPath}/resources/js/enquiry.js"></script>
</body>
</html>