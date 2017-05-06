<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login</title>
<style type="text/css">
<%@
include file ="style.css " %> .active {
	background-color: #096333;
}
</style>
</head>
<body>
	<ul>
		<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
		<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
		<c:choose>
			<c:when test="${empty email}">
				<li><a href="http://localhost:8081/tjones50/createaccount">Create
						Account</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				<li><a href="http://localhost:8081/tjones50/userAccount">Account
						Information</a></li>
				<li><a>Hello, ${email}</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	<div id="loginHeader">Login</div>
	<div id="container">
		<form action="${pageContext.servletContext.contextPath}/login"
			method="post">
			<label for="usermail">Email: </label> <input type="email"
				name="email" placeholder="yourname@ycp.edu" value="${email}"
				required><br> <label for="password">Password: </label>
			<input type="password" name="pass" placeholder="password"
				value="${password}" required><br> <input type="hidden"
				name="from" value="${param.from}"> <input
				id="ca_accountButton" type="submit" value="Login">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/createaccount"
			method="get">
			<input id="ca_accountButton" type="submit" value="New Account?">
		</form>
	</div>
	<div style="color: #FF0000;">${errorMessage}</div>
</body>
</html>