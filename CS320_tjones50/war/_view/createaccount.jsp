<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
	<title>Create Account</title>
					
		<style type="text/css">
    		<%@include file="style.css" %>
    	.active {
		background-color:#4CAF50;
		}
		</style>
		
	</head>
	<body>
		<ul>
  			<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
  			<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
  			<li><a href="http://localhost:8081/tjones50/login">Login</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
		</ul>
	

<div id = "container">
		<form action="${pageContext.servletContext.contextPath}/login" method = "post">
		
  			<label for="usermail">Email: </label>
  	 	    <input type="email" name="email" placeholder="yourname@ycp.edu" value = "${user.email}" required><br>
  	        <label for="password">Password: </label>
  	        <input type="password" name="pass" placeholder="password" value = "${user.password}" required><br>
  	        <label for="password">Password Reentry: </label>
  	        <input type="password" name="reenter" placeholder="reenter password" value = "${user.reenter}" required>
  	        <input type="submit" value = "Create Account"></form>
  	        
  	    <form action="${pageContext.servletContext.contextPath}/login" method = "post">
  	    
  	        <div id = "ca_accountButton"><input type="submit" value = "Existing Account"></div></form>
		</div>
			<div style="color: #FF0000;">${errorMessage}</div>
	</body>