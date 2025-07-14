<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${type=='str'}">
		<c:forEach var="i" begin="1" end="10" step="1">
			<p>${msg}</p>
		</c:forEach>
	</c:if>

	<c:if test="${type=='member'}">
		<c:forEach var="member" items="${memberList}">
			<p>${member.id} ${member.pw} ${member.name}</p>
		</c:forEach>
	</c:if>
</body>
</html>