<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
	<title>Login</title>
		<style type="text/css">
    		<%@include file="loginstyle.css" %>
    		
		</style>
		
	</head>
	<body>
		
		<header>
		
			<form action="${pageContext.request.contextPath}/home">
			<button type="submit">Home</button></form><br>
			
		</header>
		
		<div id = "container">
		<form action="${pageContext.servletContext.contextPath}/login" method = "post">
		
  			<label for="usermail">Email: </label>
  	 	    <input type="email" name="email" placeholder="yourname@ycp.edu" value = "${user.email}" required><br>
  	        <label for="password">Password: </label>
  	        <input type="password" name="pass" placeholder="password" value = "${user.password}" required><br>
  	        <label for="password">Password Reentry: </label>
  	        <input type="password" name="reenter" placeholder="reenter password" value = "${user.reenter}" required>
  	        <input type="submit" value = "Login">
  	        <label for = "createAccount">New to ClassWise?</label>
  	        <input type="submit" value = "New Account"></form>
		</div>
 		   
<!--			<label><b>Email</b></label>
			<input type="text" placeholder="Enter Email" name="email" value = "${user.email}" required>
			<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="pass" value = "${user.password}" required>
			<label><b>Reenter Password</b></label>
			<input type="password" placeholder="Enter Password" name="reenter" value = "${user.reenter}" required>
			
			<button type="submit">Login</button> -->
			<div style="color: #FF0000;">${errorMessage}</div>
			
		</form>
	</body>
</html>