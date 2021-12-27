<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<jsp:include page="../common/header.jsp"/>

<link rel="stylesheet" href="../resources/css/profile.css">

<body class="body">

	<form action="profile/update" enctype="multipart/form-data"  method="POST" name="updateForm" role="form" onsubmit="return false">
		<div class="category">PROFILE</div>
	    
	    <main class="main"> 
	       
	        <div class="tab-area">
	            <div class="tab">프로필</div>  
	            <div class="tab">소셜 정보</div>  
	            <div class="tab">비밀번호 변경</div>  
	            <div class="tab" style="color: darkgray;">회원탈퇴</div>  
	        </div>
	
	        <div class="line"></div>
	        
	         <!-- 프로필 -->
	        <div class="content" style=" display: block;" >
	            <div class="profile">
	                <div class="p-img"> 
		                <div class="profileImg">
	                    	<img class="profileImgCss" name="memberImg" src="${contextPath}${memberImg.memberImgPath}${memberImg.memberImgName}">
	                  </div>
	                </div>
	                
	                <div class="p-btn">
	                    <button type="button" class="p-choose" id="p-choose" >이미지 업로드</button>
	                    <button type="button" class="p-delete" id="p-delete" >이미지 제거</button>
	                </div>
	                <div id="fileArea">
						<input type="file" name="img" onchange="loadImg(this)" id="memberImg"> 
					</div>
	            </div>
	
	            <div class="intro" style="position: relative;">
	                <div class="int-title" style="height: 100; line-height: 88px;">자기소개</div>
	                <div class="int-input">
	                    <input type="text" id="nickname" name="nickname" value="${profileVO.memberNm}" placeholder="닉네임" > 
	                    <input type="text" class="line-intro" id="line-intro" name="line-intro" value="${profileVO.intro}"   placeholder="한 줄 소개" style="margin: 5px 0px 5px 9px;" >
	                </div>
	            </div>
	
	            <div class="devel-intro">
	                <div class="d-title">디벨로그 제목</div>
	                <div class="d-input">
	                    <input type="text" id="devel-input" name="devel-input" value="${profileVO.blogTitle}"  placeholder="디벨로그 제목" >
	                </div>
	            </div>
	
	            <div class="explain" >
	                개인 페이지 좌측 상단에 나타나는 페이지 제목입니다.
	            </div>
	        </div> <!-- /프로필 -->
	        
	
	
	        <!-- 소셜 정보 -->
	        <div class="content" style="padding: 35px;">
	          <!-- email -->
	          <div class="sns-email" >
	            <div class="email-title" >
	              <img class="email-img" id="email-img" src="${contextPath}/resources/images/boardIcon/email.png">
	            </div>
	            <div class="email-input">
	                <input type="text"  class="line-intro" id="emailContent" name="" value="${profileVO.snsEmail}"  placeholder="이메일을 입력해주세요." size=35 >
	            </div>
	          </div>
	
	          <!-- github -->
	          <div class="sns-github" >
	            <div class="github-title" >
	              <img  class="github-img"  id="github-img" src="${contextPath}/resources/images/boardIcon/github.png">
	            </div>
	            <div class="github-input">
	                <input type="text"  class="line-intro" id="gitContent" name="" value="${profileVO.snsGit}"  placeholder="Github 계정을 입력하세요." size=35 >
	            </div>
	          </div>
	
	          <!-- twitter -->
	          <div class="sns-twitter" >
	            <div class="twitter-title" >
	              <img class="twitter-img" id="twitter-img" src="${contextPath}/resources/images/boardIcon/twitter.png" >
	            </div>
	            <div class="twitter-input">
	                <input type="text" class="line-intro"  id="twitterContent" name="" value="${profileVO.snsTwitt}"  placeholder="Twitter 계정을 입력하세요." 
	                size=35
	                >
	            </div>
	          </div>
	
	          <!-- facebook -->
	          <div class="sns-facebook">
	            <div class="facebook-title" >
	              <img  class="facebook-img"  src="${contextPath}/resources/images/boardIcon/facebook.png" 
	              >
	            </div>
	            <div class="facebook-input">
	                <input type="text"  class="line-intro" id="facebookContent" name="" value="${profileVO.snsFbook}" placeholder="http://www.facebook.com/" size=35 >
	            </div>
	          </div>
	
	          <!-- homepage -->
	          <div class="sns-homepage">
	            <div class="homepage-title" >
	              <img  class="homepage-img" id="homepage-img" src="${contextPath}/resources/images/boardIcon/home.png" alt="" >
	            </div>
	            <div class="homepage-input">
	                <input type="text" class="line-intro" id="homeContent" name="" value="${profileVO.snsHome}" placeholder="홈페이지 주소를 입력하세요."  size=35 >
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
	                  <span class="email-span" id="email-span">${loginMember.memberEmail}</span>
	                </div>
	          </div>
	
	          <!-- 뒤에있는 클래스명은 css적용을 위한 클래스명 -->
	          <div class="pw-area devel-intro"> 
	            <div class="pw-title d-title " >비밀번호 변경</div>
	            <div class="pw-input d-input ">
	              <div class="pw-btn-area ">
	                <button class="pw-btn"  type="button" id="pw-btn" onclick="location.href='${contextPath}/member/updatepw'">변경</button> <!-- get 방식 -->
	              </div>
	            </div>
	          </div>
	
	          <div class="explain ex-myInfo">
	            회원 인증 또는 시스템에서 발송하는 이메일을 수신하는 주소입니다.
	          </div> 
	        </div>
	
	        <!-- 회원탈퇴 -->
	        <div class="content" >
	          <div class="secession">
	            <textarea class="secessionForm" id="" cols="65" rows="6.5">
	[회원탈퇴 약관]
	
	회원탈퇴 신청 전 안내 사항을 확인 해 주세요.
	회원탈퇴를 신청하시면 현재 로그인 된 아이디는 사용하실 수 없습니다.
	회원탈퇴를 하더라도, 서비스 약관 및 개인정보 취급방침 동의하에 따라 일정 기간동안 회원 개인정보를 보관합니다.
	            </textarea>
	          </div>
	
	          <div class="secession-chk">
	              <div class="form-chk">
	                <input type="checkbox" name="agree" id="agree"> 
	                <label class="" for="agree">위 약관에 동의합니다.</label>
	              </div>
	          </div>
	
	          <div class="btn-secession-area">
	            <button class="btn-secession" onclick="secession();">회원 탈퇴</button>
	          </div>
	
	        </div>
	    </main>
	
	
	
	    
	    <div class="footer">
	        <div class="btn-area">
	            <button class="btn-submit" onclick="return pofileValidate();">저장</button>
	        </div>
	    </div>
	</form>



    
    <!-- BOOTSTRAP -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <!-- JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		const memberNo = '${loginMember.memberNo}'
		// const contextPath = '${contextPath}'
	</script>
    <script src="../resources/js/profile.js"></script>
    
    
  
 
</body>
</html>