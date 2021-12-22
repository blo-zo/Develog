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
       

			<form action="">
			<label>문의 제목 : </label>l<input name="enquriy-title">
            <p>내용</p>
            <textarea name="" id="" style="resize: none;"></textarea>
      		<div class="btn-wrap">
          	<button onclick="location.href = '${contextPath}/enquiry/list';">이전</button>
          	<button>전송</button>
      		</div>
			</form>
    </main>
    
 
<script src="${contextPath}/resources/js/enqiry.js"></script>
</body>
</html>