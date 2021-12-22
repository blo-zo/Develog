<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp" /><!-- 상대 경로  -->


   <div class="modal" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog modal-dialog-centered " style="max-width: inherit">
            <div class="modal-content" id="email-modal">
                <div class="modal-header1">
                   
                        <p id="md-title1">이메일 인증</p>

                        <div class="x-box1">
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>

                        </div>
                    
                </div>
                <div class="modal-body1">
                    <form>

                        <div class="input-box1">
                            <div class="put1">
                              
                                <div class="input-wrp1">
                                    <input type="email" class="sumit-box1" id="email1" placeholder="  이메일을 입력하세요."
                                        required="required">
                                        <button type="button" id="sub_btn1" onclick="regEmailCheck();"> 전송</button>
                                </div>
                            </div>
                        

						</div>
                    </form>
                </div>
            </div>
           
     </div>
    </div>

<main class="b-container">

	<form action="searchpw" method="post" onsubmit= "return searchValidate()">
		<fieldset>
			<p class="search-title">비밀번호 찾기</p>

			<br>

			<table>

				<!-- 이메일 -->
				<tr>
					<td><label for="email" class="label1">이메일</label>
					<input class="btn btn-primary searchPw-input" id="searchPw-email" data-bs-toggle="modal" data-bs-target="#exampleModal1" 
						type="email" name="searchPwEmail" placeholder="  이메일을 입력해주세요" 
						 style="text-align: left;  background-color: white; color: black;">
					</td>

				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkEmail"></span>
						</div>
					</td>
				</tr>
				<!-- ------------------------------------------------------- 로그인 모달 ---------------------------------------- -->
			    
  
    
 
				
				
				<!-- 비밀번호 -->
				<tr>

					<td><label for="password" class="label1">임시비밀번호</label><input class="searchPw-input" id="temp" type="password" name="temp" placeholder="  비밀번호를 입력해주세요">
					
						<button type="button" id="sub_btn2">확인</button>
					</td>
				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkPwd"></span>
						</div>
					</td>
				</tr>

				<!-- 새 비밀번호 -->
				<tr>

					<td><label for="newPassword" class="label1"> 새 비밀번호</label><input class="searchPw-input" name="searchPw" id="newPassword" type="password" placeholder="  새 비밀번호를 입력해주세요"></td>

				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkPwd1"></span>
						</div>
					</td>
				</tr>
				<tr>

					<td><label for="newPassword1" class="label1"> 새 비밀번호 확인</label><input class="searchPw-input" name="searchPw" id="newPassword1" type="password" placeholder="  새 비밀번호를 확인해주세요" ></td>

				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkPwd2"></span>
						</div>
					</td>
				</tr>

				<tr>
					<td>

						<section class="btin-wrap">
							<button type="submit" id="suc">확인</button>
							<button type="reset" id="eix" onclick="location.href = '${contextPath}';">취소</button>
						</section>
					</td>
				</tr>

			</table>




		</fieldset>
	</form>

</main>



<jsp:include page="../common/footer.jsp"></jsp:include>


<script src="${contextPath}/resources/js/searchPw.js"></script>
</body>
</html>
