<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>관리자 페이지</h1>
	<h2>사용자(User) 추가</h2>
	
<!-- 	모든 값을 입력 받는 케이스 -->
<!-- 	<form action="/admin/users/add" method="post">	 -->
<!-- 		사용자 아이디 : <input type="text" name="id"><br> -->
<!-- 		사용자 비번 : <input type="password" name="pw"><br> -->
<!-- 		사용자 이름 : <input type="text" name="name"><br> -->
		
<!-- 		계정 구분  -->
<!-- 		<select name="userType"> -->
<!-- 			<option value="ADM">관리자</option> -->
<!-- 			<option value="CUS">사용자</option> -->
<!-- 		</select> -->
<!-- 		<button type="submit">등록하기</button> -->
<!-- 	</form> -->
	
<!-- 	필요한 값들만 입력받는 케이스 -->
	<form action="/admin/users/add" method="post">	
		사용자 아이디 : <input type="text" name="id"><br>
		사용자 이름 : <input type="text" name="name"><br>
		
		<button type="submit">등록하기</button>
	</form>
	
</body>
</html>