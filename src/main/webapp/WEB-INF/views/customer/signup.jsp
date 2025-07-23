<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>signup 회원가입 페이지</h1>
	
	<form action="/customer/signup" method="post">	
		아이디 : <input type="text" name="id"><br>
		비번 : <input type="password" name="pw"><br>
		비밀번호확인 : <input type="password"><br>
		
		이름 : <input type="text" name="name"><br>
		
		<button type="submit">가입하기</button>
	</form>
</body>
</html>