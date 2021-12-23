<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header include -->
	<jsp:include page="header.jsp"/>
	<%-- include할 jsp 파일 경로 작성 --%>
		
		
		
		   <main>
    <!---------------------------------------------------- Swiper 캐러셀 ------------------------------------->
    <div class="swiper mySwiper"> <!-- active 클래스 넣기 -->
        <div class="swiper-wrapper">
          <div class="swiper-slide wrapper">
          <a href="">
              <img src="https://via.placeholder.com/420x250" alt="" class="carousel-imgbox">
               <div class="bg">
                 <p>테스트입니다</p>
                </div>
              </a>
            </div>
          <div class="swiper-slide wrapper">
          <a href="">
              <img src="https://via.placeholder.com/420x250" alt="" class="carousel-imgbox">
               <div class="bg">
                 <p>테스트입니다</p>
                </div>
              </a>
            </div>
          <div class="swiper-slide wrapper">
          <a href="">
              <img src="https://via.placeholder.com/420x250" alt="" class="carousel-imgbox">
               <div class="bg">
                 <p>테스트입니다</p>
                </div>
              </a>
            </div>
          <div class="swiper-slide wrapper">
          <a href="">
              <img src="https://via.placeholder.com/420x250" alt="" class="carousel-imgbox">
               <div class="bg">
                 <p>테스트입니다</p>
                </div>
              </a>
            </div>
          <div class="swiper-slide wrapper">
          <a href="">
              <img src="https://via.placeholder.com/420x250" alt="" class="carousel-imgbox">
               <div class="bg">
                 <p>테스트입니다</p>
                </div>
              </a>
            </div>
          <div class="swiper-slide wrapper">
          <a href="">
              <img src="https://via.placeholder.com/420x250" alt="" class="carousel-imgbox">
               <div class="bg">
                 <p>테스트입니다</p>
                </div>
              </a>
            </div>
         
         
     
        </div>
        <div class="swiper-pagination"></div>
      </div>
        <!-- --------------------------------------------캐러셀 끝------------------------------------------------------- -->
        <button onclick=" location.href='${contextPath}/member/updatepw'"  >비밀번호 변경</button>
		
     
        
          <section class="post-hr">
           <hr class="hr" >
           <span class="post" >post</span>
           <hr class="hr">
   
          </section> 
           
           
           
           <nav>
          <!-- 태그 정렬 옵션 -->
          <div class="nav-wrap">
   
            <div class="sort-menu">
              <div class="under-line-on">
             <a href="/trend">
                <span><svg id="tr-img" stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24"  xmlns="http://www.w3.org/2000/svg"><path d="M16 6l2.29 2.29-4.88 4.88-4-4L2 16.59 3.41 18l6-6 4 4 6.3-6.29L22 12V6z"></path></svg>
                 트랜드</span>
             </a>
               </div>
               <div class="under-line-off">
                 <a href="#">
                 <span><svg id="tr-img2" stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24"  xmlns="http://www.w3.org/2000/svg"><path d="M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8zm.5-13H11v6l5.25 3.15.75-1.23-4.5-2.67z"></path></svg>
                   최신</span>
                 </a>
                 </div>
                 
               </div>
               
               <form action="" id="date-form" style="height: 30px;">
                 <select name="" id="date">
                   <option value="">오늘</option>
                   <option value="">이번주</option>
                   <option value="">이번달</option>
                   <option value="">올해</option>
                 </select>
               </form>
             </div>
             
   
          
        
        </nav>
          
        <section class="card-bord">
       
   
         <div class="wrapper-card">
            
   
   
                 <a href="" class="wrapper-a">
   
                     
                     <div class="card">
                         
                         <div class="image"><img src="https://via.placeholder.com/280x150" id="img-size"></div>  
                         
                         <div class="title-box">
                             <p  class="title">제목입니다</p>
                         </div>  
                         <div class="user-div sub-div">
                             
                             <span class="by_name">by <b>유동훈</b></span><div class="likes"><svg id="ht"viewBox="0 0 24 24"><path fill="currentColor" d="M18 1l-6 4-6-4-6 5v7l12 10 12-10v-7z"></path>
                             </svg>155</div>
                         </div>
                         
                     </div>
                 </a>
             </div>
      
      
     
   		</section>
       
       </main>
		
	
	
	
	
	
	
	
	
	
	<jsp:include page="footer.jsp"/>
	
	
</body>
</html>