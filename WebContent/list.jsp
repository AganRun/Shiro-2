<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>List jsp</h1>
	
	Welcome:<shiro:principal>...</shiro:principal>
	
	<shiro:hasRole name="admin">
		<br><br>
		<a href="${pageContext.request.contextPath }/admin.jsp">-----------------Admin.jsp</a>
	</shiro:hasRole>
	
	
	<shiro:hasRole name="user">
		<br><br>
		<a href="${pageContext.request.contextPath }/user.jsp">-----------------User.jsp</a>
	</shiro:hasRole>
	
	<br><br>
	<a href="${pageContext.request.contextPath }/shiro/testShiroAnnotation">-----------------testShiroAnnotation</a>
	
	<br><br>
	<a href="${pageContext.request.contextPath }/shiro/logout">-----------------Logout</a>
</body>
</html>