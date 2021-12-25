<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../common/header.jsp"/><!-- 상대 경로  -->
    
    
    <main class="b-container">

        <form method="POST" action="updatepw" onsubmit="return updatePwValidate();">
            <fieldset>
                <p class="updatePw-title">비밀번호변경</p>
                
                <br>
                
                <table>
                    
                    
                    <!-- 이메일 -->
                    
                    <tr>
                        <td> <label for="email" class="label1">이메일</label> 
                            <div class="session-val">
                                <p>${sessionScope.loginMember.memberEmail}</p>
                            </div>
						</td> 
                        
                    </tr>
                    <!-- 닉네임  -->
                    <tr>
                        <td> <label for="name" class="label1">닉네임</label> 
                            <div class="session-val">
                                <p>${sessionScope.loginMember.memberNm}</p>
                            </div>
						</td> 
                        
                    </tr>
             
					<tr>
                        
                        <td> <label for="currentPw" class="label1"> 기존비밀번호</label><input class="updatePw-input" id="currentPw" type="password" name="currentPw" placeholder="  기존비밀번호를 입력해주세요" required="required"></td> 
                        
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
                        
                        <td> <label for="newPw" class="label1"> 새 비밀번호</label><input class="updatePw-input" id="newPw" type="password" name="newPw" placeholder="  새 비밀번호를 입력해주세요" required="required"></td> 
                        
                    </tr>
                     <tr>
						<td>

							<div class="check-wrapper">
								
							</div>
						</td>
					</tr>
                    <tr>

                        <td> <label for="newPw1" class="label1"> 새 비밀번호 확인</label><input class="updatePw-input" id="newPw1" type="password" name="newPw1" placeholder="  새 비밀번호를 확인해주세요" required="required"></td> 
                        
                    </tr>
                     <tr>
						<td>

							<div class="check-wrapper">
								
							</div>
						</td>
					</tr>
                    
                    <tr>
                        <td>
                            
                            <section class="btin-wrap">
                                <button type="submit" id="suc">확인</button>
                                <button type="reset" id="eix" onclick= "location.href = '${contextPath}/member/profile'">취소</button>
                            </section>
                        </td>
                    </tr>
                    
                </table>  

                
            </fieldset>
        </form>
        
    </main>
         <jsp:include page="../common/footer.jsp"></jsp:include>
         
         <script>
            // 비밀번호 유효성 검사
        
            
            function updatePwValidate(){
                // 새 비밀번호/ 확인에 작성된 값을 변수에 저장
                const newPw =document.getElementById("newPw").value;
                const newPw1 =document.getElementById("newPw1").value;
                //1) 새 비밀번호가 정규식에 맞는지
            const regExp =	/^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
                if(!regExp.test(newPw)){
                    alert("새 비밀번호가 유효하지 않습니다.")
                    return false;
    
                }
                //2) 새 비밀번호/ 확인 같지 않은 경우
                if(newPw != newPw1){
                    alert("새 비밀번호가 일치하지 않습니다.")
                    return false;
                }
            };
            
        </script>
    
     
</body>
</html>     