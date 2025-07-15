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
	<h1>practice08 member</h1>
		
	<h2>서버에서 화면에 보여줄 리스트를 필터링하고 넘겨줬을때</h2>
	
	<c:forEach var="m" items="${viewMemberList}">
		<p>${m.id} ${m.pw} ${m.name} ${m.type}</p>	
	</c:forEach>
	
	<h2>서버에서 리스트 전체 넘기고, 화면에서 JSTL 로 구분 출력 했을때</h2>
	
	<c:choose>
		<c:when test="${auth == 'basic'}">
			<c:forEach var="m" items="${memberList}">
				<c:if test="${m.type == 'basic'}">
					<p>${m.id} ${m.pw} ${m.name} ${m.type}</p>
				</c:if>	
			</c:forEach>
		</c:when>
		<c:when test="${auth == 'manager'}">
			<c:forEach var="m" items="${memberList}">
				<c:if test="${m.type == 'manager'}">
					<p>${m.id} ${m.pw} ${m.name} ${m.type}</p>
				</c:if>	
			</c:forEach>
		</c:when>
		<c:when test="${auth == 'admin'}">
			<c:forEach var="m" items="${memberList}">
				<p>${m.id} ${m.pw} ${m.name} ${m.type}</p>	
			</c:forEach>
		</c:when>
	</c:choose>

	
</body>
</html>