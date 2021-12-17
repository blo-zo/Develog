<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="../common/header.jsp"/><!-- 상대 경로  -->
    
    
    <main class="b-container">

        <form action="searchPw" method="post">
            <fieldset>
                <p class="search-title">비밀번호 찾기</p>
                
                <br>
                
                <table>
                    
                    <!-- 이메일 -->
                    <tr>
                        <td> <label for="email" class="label1">이메일</label><input class="searchPw-input" id="searchPw-email" type="email" name="searchPwEmail" placeholder="  이메일을 입력해주세요" required="required">
                             <button id="sub_btn" onclick="return emailput()">전송</button>
                        </td>           
                        
                    </tr>
                    
                    <tr>
						<td>

							<div class="check-wrapper">
								<span id="checkEmail"></span>
							</div>
						</td>
					</tr>
                    <!-- 비밀번호 -->
                    <tr>
                        
                        <td>  <label for="password" class="label1">비밀번호</label><input class="searchPw-input" id="password" type="password" name="pw" placeholder="  비밀번호를 입력해주세요" required="required">
                                
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
                        
                        <td> <label for="password1" class="label1"> 새 비밀번호</label><input class="searchPw-input" id="newPassword" type="password" name="pw1" placeholder="  새 비밀번호를 입력해주세요" required="required"></td> 
                        
                    </tr>
                     <tr>
						<td>

							<div class="check-wrapper">
								<span id="checkPwd1"></span>
							</div>
						</td>
					</tr>
                    <tr>

                        <td> <label for="password1" class="label1"> 새 비밀번호 확인</label><input class="searchPw-input" id="newPassword1" type="password" name="pw2" placeholder="  새 비밀번호를 확인해주세요" required="required"></td> 
                        
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
                                <button type="reset" id="eix" onclick= "location.href = '${contextPath}';">취소</button>
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