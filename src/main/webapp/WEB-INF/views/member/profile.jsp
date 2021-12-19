<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/header.jsp" /><!-- 상대 경로  -->


    
    <div class="category">PROFILE</div>
    
    <main> 
       
        <div class="tab-area">
            <div class="tab">프로필</div>  
            <div class="tab">소셜 정보</div>  
            <div class="tab">이메일</div>  
            <div class="tab" style="color: darkgray;">회원탈퇴</div>  
        </div>

        <div class="line"></div>
        
         <!-- 프로필 -->
        <div class="content" style=" display: block;" >
            <div class="profile">
                <div class="p-img"><img src="images/profile-small.png"></div>
                <div class="p-btn">
                    <div id="p-choose" >이미지 업로드</div>
                    <div id="p-delete" >이미지 제거</div>
                </div>
            </div>

            <div class="intro" style="position: relative;">
                <div class="int-title" style="height: 100; line-height: 88px;">자기소개</div>
                <div class="int-input">
                    <input type="text" id="nickname" name="nickname" placeholder="닉네임" >
                    <input type="text" id="line-intro" name="line-int" placeholder="한 줄 소개" style="margin: 5px 0px 5px 9px;" >
                </div>
            </div>

            <div class="devel-intro">
                <div class="d-title">디벨로그 제목</div>
                <div class="d-input">
                    <input type="text"  placeholder="한 줄 소개" >
                </div>
            </div>

            <div class="explain" >
                개인 페이지 좌측 상단에 나타나는 페이지 제목입니다.
            </div>
        </div> <!-- /프로필 -->
        


        <!-- 소셜 정보 -->
        <div class="content" style="padding: 35px;">
          <!-- email -->
          <div class="sns-email" style="position: relative;">
            <div class="email-title" >
              <img id="email-img" src="images/email.png" alt="" >
            </div>
            <div class="email-input">
                <input type="text" id="line-intro" name="line-int" placeholder="이메일을 입력해주세요." size=35 >
            </div>
          </div>

          <!-- github -->
          <div class="sns-github" style="position: relative;">
            <div class="github-title" >
              <img id="github-img" src="images/github.png" alt="" >
            </div>
            <div class="github-input">
                <input type="text" id="line-intro" name="line-int" placeholder="Github 계정을 입력하세요." size=35 >
            </div>
          </div>

          <!-- twitter -->
          <div class="sns-twitter" style="position: relative;">
            <div class="twitter-title" >
              <img id="twitter-img" src="images/twitter.png" alt="">
            </div>
            <div class="twitter-input">
                <input type="text" id="line-intro" name="line-int" placeholder="Twitter 계정을 입력하세요." 
                size=35
                >
            </div>
          </div>

          <!-- facebook -->
          <div class="sns-facebook" style="position: relative;">
            <div class="facebook-title" >
              <img src="images/facebook.png" alt=""
               style="width: 20%;  display: flex; object-fit: cover; float: right; margin: 3px 10px 3px 0px;">
            </div>
            <div class="facebook-input">
                <input type="text" id="line-intro" name="line-int" placeholder="http://www.facebook.com/" size=35 >
            </div>
          </div>

          <!-- homepage -->
          <div class="sns-homepage" style="position: relative;">
            <div class="homepage-title" >
              <img id="homepage-img" src="images/home.png" alt="" >
            </div>
            <div class="homepage-input">
                <input type="text" id="line-intro" name="line-int" placeholder="홈페이지 주소를 입력하세요."  size=35 >
            </div>
          </div> <!-- homepage -->


            <div class="explain-sns" >
                개인 페이지 좌측 상단에 나타나는 페이지 제목입니다.
            </div> 
        </div> <!-- /content -->




        <!-- 내 정보 -->
        <div class="content"  style=" padding: 35px;">
          
          <div class="email-area">
              <div class="email-addr-title">이메일 주소</div>
              <div class="email-addr">
                  <span id="email-span">sample1234@gmail.com</span>
                </div>
          </div>

          <div class="email-set" style="position: relative;">
                <div class="email-set-title" style="height: 100; line-height: 88px;">이메일 수신설정</div>
                <div class="email-set-chk">
                  <span id="reply-span" >
                    댓글 알림
                  </span>  
                  <span id="update-span">
                    벨로그 업데이트 소식
                  </span>
                  <!-- <input type="text" id="nickname" name="nickname" placeholder="닉네임" style="font-weight: bold; color: 323232 ; height: 30px;">
                    <input type="text" id="line-intro" name="line-int" placeholder="한 줄 소개" style="height: 30px;"> -->
                </div>
                
              
          </div>
          
          <div class="devel-intro">
            <div class="d-title">비밀번호 변경</div>
            <div class="d-input">
              <div class="btn-secession-area" style="max-width: 930px;  padding: 10px 0px 10px 9px;">
                <div class="btn-secession" style="  
                    background-color: #3278FE; 
                    color: white; 
                    width: 60px; 
                    height: 37px; 
                    border-radius: 5px; 
                    text-align: center; 
                    line-height: 35px;

                   
                    "  onclick= "location.href ">
                  변경
                </div>
              </div>
              </div>
          </div>

          <div class="explain">
            회원 인증 또는 시스템에서 발송하는 이메일을 수신하는 주소입니다.
          </div> 
        </div>

        <!-- 회원탈퇴 -->
        <div class="content" >
          <div class="secession" style="padding-top: 20px;">
            <textarea class="form" id="" cols="55" rows="6.5"
              style="
              background-color:transparent;
              border: none;
              display: block;
              resize: none;
              outline: none;
              margin: auto;
              text-align: center;
              
              "
            >
[회원탈퇴 약관]

회원탈퇴 신청 전 안내 사항을 확인 해 주세요.
회원탈퇴를 신청하시면 현재 로그인 된 아이디는 사용하실 수 없습니다.
회원탈퇴를 하더라도, 서비스 약관 및 개인정보 취급방침 동의하에 따라 일정 기간동안 회원 개인정보를 보관합니다.

            </textarea>
          </div>
          <div class="secession-chk" style="
          width: 420px;
          margin: auto;">

              <div class="form-check" 
              style=" margin: 20px;     text-align: center;">
                <input type="checkbox" name="agree" id="agree"
                  class=""> 
                <label class=""
                  for="agree">위 약관에 동의합니다.</label>
              </div>
            
          </div>
          <div class="btn-secession-area" 
          style="max-width: 930px; padding-bottom: 20px; display: flex;
          justify-content: center;">
            <div class="btn-secession" style="  
                background-color: #FE3232;
                color: white;
                width: 85px;
                height: 37px;
                border-radius: 5px;
                text-align: center;
                line-height: 35px;
                margin: auto;
                ">
              회원 탈퇴
            </div>
          </div>
          
        </div>

    </main>

    <footer>
        <div class="btn-area">
            <div class="btn-submit">저장</div>

        </div>
    </footer>




    
    <!-- BOOTSTRAP -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!-- JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    
    <script>
       /* 헤더 - 최근검색어 삭제 */
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


        // 정렬 방식 선택하기
        // 클릭한것만 커지게
        $(".sort-menu > div").on("click",function(){
        $(".sort-menu > div").removeAttr("class");
        $(this).addClass("under-line-on");
        $(this).siblings().addClass("under-line-off");
        });


        /* TAB */
        const tabArr = document.getElementsByClassName("tab");

      
        for(let i=0; i < tabArr.length ; i++){
            
            tabArr[i].addEventListener("click", function(){

                for(tab of tabArr){
                    tab.classList.remove("select-tab");
                }
                this.classList.add("select-tab");

                contentArr = document.getElementsByClassName("content")

                for(content of contentArr){
                    content.style.display = "none";
                }
                contentArr[i].style.display = "block";
            });
        } 
 
    </script>
    
  
 
</body>
</html>