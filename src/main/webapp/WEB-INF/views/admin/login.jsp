<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>AdminLogin</title>
	<style>
        div{
            box-sizing: border-box;
            background-color: #F7F7F7;
            width: 500px;
            height: 350px;
            margin: auto;
            margin-top: 130px;
            text-align: center;
            padding-top: 100px;
        }
        input{
            width: 400px;
            height: 40px;
            border: 1px solid #D6D6D6;
            border-radius: 5px;
        }

        input::placeholder{
            padding-top: 5px;
            font-size: 20px;
            line-height: 40px;
        }
    </style>
</head>
<body>
<div>
	<a href="${pageContext.servletContext.contextPath}/admin/member">go admin</a>
        <span style="font-size: 40px; font-weight: bold;">Admin</span>
        <br><br>
        <input type="text" placeholder="비밀번호를 입력하세요">
        <br><br>
        <span style="font-size: 30px; font-weight: bold;">Login</span>
   		 </div>
</body>
</html>