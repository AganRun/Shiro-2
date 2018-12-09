<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>

	<form action="shiro/login" method="post">
		username:<input type="text" name="username">
		<br><br>
		password:<input type="password" name="password">
		<br><br>
		
		<input type="submit" value="submit">
	</form>
</body>
</html>