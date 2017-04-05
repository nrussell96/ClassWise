<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
	<title>Login</title>
					
		<style type="text/css">
    		<%@include file="style.css" %>
		</style>
		
		
	</head>
	<body>
		
		<p3>
		<form action="${pageContext.request.contextPath}/home">
			<button type="submit">Home</button>
		</form>
		</p3>
		
		<h1> Login to ClassWise</h1>
	
	
		<form action="${pageContext.servletContext.contextPath}/login" method = "post">
		
			<label><b>Email</b></label>
			<input type="text" placeholder="Enter Email" name="email" value = "${user.email}" required>
			<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="pass" value = "${user.password}" required>
			
			<button type="submit">Login</button>
			<div style="color: #FF0000;">${errorMessage}</div>
			
		</form>
	</body>
</html>