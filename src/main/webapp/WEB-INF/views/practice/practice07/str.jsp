<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	고정된 값 10개 하드코딩 -->
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<p>스트링 리스트입니다.</p>
	<br>

<!-- 	고정 메시지 10번 반복 -->
	<c:forEach var="i" begin="1" end="10" step="1">
		<p>스트링 리스트입니다.</p>
	</c:forEach>
	<br>
	
<!-- 	서버에서 view 로 전달된 메시지 반복 -->
	
	<c:forEach var="i" begin="1" end="10" step="1">
		<p>${msg}</p>
	</c:forEach>
	
	<br>

<!-- 	리스트 단위로 전달된 메시지 반복 -->
	<c:forEach var="m" items="${msgList}">
		<p>${m}</p>
	</c:forEach>
	
</body>
</html>







