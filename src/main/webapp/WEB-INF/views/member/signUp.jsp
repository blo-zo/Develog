<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp" /><!-- 상대 경로  -->



<main class="b-container">

	<form action="signup" method="POST" name="signUpForm" onsubmit="return validate();">
		<fieldset class="signUp-fieldset">
			<p class="signUp-title">회원가입</p>

			<br>

			<table>

				<tr>

					<td><label for="signUp-name" class="label1">닉네임</label><input maxlength="10" autocomplete="off" class="signUp-input" id="signUp-name" type="text" name="signUpNm" placeholder="  닉네임을 입력해주세요." required="required"></td>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkName"></span>
						</div>
					</td>
				</tr>

				<tr>
					<td><label for="signUp-email" class="label1">이메일</label><input class="signUp-input" id="signUp-email" type="email" name="signUpEmail" placeholder="  이메일을 입력해주세요" autocomplete="off" required="required"></td>
				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkEmail"></span>
						</div>
					</td>
				</tr>
				<tr>

					<td><label for="signUp-pw" class="label1">비밀번호</label><input class="signUp-input" id="signUp-pw" type="password" name="signUpPw" placeholder="  비밀번호를 입력해주세요" autocomplete="off" required="required"></td>
				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkPw"></span>
						</div>
					</td>
				</tr>
				<tr>

					<td><label for="signUp-pw1" class="label1">비밀번호확인</label><input class="signUp-input" id="signUp-pw1" type="password" name="signUpPw1" placeholder="  비밀번호를 확인해주세요" autocomplete="off" required="required"></td>

				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkPw1"></span>
						</div>
					</td>
				</tr>

				<tr>
					<td>

						<section class="btin-wrap">
							<button type="submit" id="suc">확인</button>
							<button type="reset" id="eix" onclick= "location.href = '${contextPath}';" >취소</button>
						</section>
					</td>
				</tr>
				<tr>
					<td>

						<div class="check-wrapper">
							<span id="checkName"></span>
						</div>
					</td>
				</tr>
			</table>


		</fieldset>
	</form>
</main>

<jsp:include page="../common/footer.jsp"></jsp:include>



<script src="${contextPath}/resources/js/member.js"></script>
</body>
</html>
