<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
  <!-- 부트스트랩 js -->
   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
      <!-- Swiper JS -->  
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        
   	<script src="${contextPath}/resources/js/login.js"></script>     
    
    <c:if test="${!empty sessionScope.message}">
        <script>
		$(function(){
			alert("${message}");
			
		});        
        
        </script>
        
        <c:remove var="message" scope = "session"/>
        
    </c:if>
        
        
    <script>
    
        var swiper = new Swiper(".mySwiper", {
        slidesPerView: 3,
        spaceBetween: 30,
        freeMode: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
      });
             // ****** 공통 검색창 JS *******

        // 최근 검색어 삭제
        $(".search-delete").on("click", function(){

        const recentText = $(this).parent();

        recentText.remove();

        });

        // 전체 삭제하기
        $("#search-delete-all").on("click",function(){

        const recentTexts = $(this).parent().siblings();

        recentTexts.remove();

        });

        // 클릭한것만 커지게
        $(".sort-menu div").on("click",function(){

        $(".sort-menu > div").removeAttr("class");

        $(this).addClass("under-line-on");

        $(this).siblings().addClass("under-line-off");

        });



    </script>