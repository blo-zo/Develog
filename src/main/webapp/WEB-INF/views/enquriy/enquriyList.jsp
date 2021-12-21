<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
       <title>1:1 문의 목록페이지</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/enquiryList.css">
   
    <jsp:include page="../common/header.jsp"/>
    
   
 
    
    
     

  <main>
    <b>1:1 문의</b>
 	<div class="enquriy_insertBtn_wrap">
    <button class="enquriy_insertBtn">등록</button>
 	</div>												
    <section>
      
      <c:choose>
      <c:when test="${empty enquiryList}">
      
      <p>문의내용 없다~</p>
      </c:when>
      <c:otherwise>
      
      <c:forEach items = "${enquiryList}" var = "enquiry">
      <a href="">
	
        <div>
          <p>${enquiry.enquiryTitle}</p>
          <p>${enquiry.enquiryContent}</p>
          <p>${enquiry.createDt}</p>

        </div>
       
      </a>
    
      </c:forEach>
      </c:otherwise>
 
      </c:choose>
 
    
      <div class="my-5">
        <ul class="pagination">
          <c:if test="${pagination.startPage != 1}">
          <li><a class = "page-link" href = "list?cp=1">&lt;&lt;</a></li>
          <li><a class = "page-link" href = "list?cp=${pagination.prevPage}">&lt;</a></li>
          </c:if>
         
          <c:forEach begin="${pagination.startPage}"  end="${pagination.endPage}" step="1" var="i">
          <c:choose>
          <c:when test="${i==pagination.currentPage}">
          <li><a class = "page-link" style="color: black; font-weight: bold">${i}</a></li>
          
          </c:when>
          <c:otherwise>
            <li><a class = "page-link" href = "list?cp=${i}">${i}</a></li>
          </c:otherwise>
          </c:choose>
          </c:forEach>
            <c:if test="${pagination.endPage != pagination.nextPage}">
            <li><a class = "page-link" href = "list?cp=${pagination.nextPage}">&gt;</a></li>
            <li><a class = "page-link" href = "list?cp=${pagination.maxPage}">&gt;&gt;</a></li>
            </c:if>
  
        </ul>
        
      </div>


    </section>
  </main>

  


        <jsp:include page="../common/footer.jsp"></jsp:include>
      
      
 <script></script>
</body>
</html>