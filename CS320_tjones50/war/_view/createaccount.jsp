<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
	<title>Create Account</title>
					
		<style type="text/css">
    		<%@include file="style.css" %>
    	.active {
		background-color:#096333;
		}
		</style>
		
	</head>
	<body>
		<ul>
  			<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
  			<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
  			<li><a href="http://localhost:8081/tjones50/login">Login</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<c:if test="${!empty email}">
				<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
				<li><a>Hello, ${email}</a></li>
			</c:if>
		</ul>
	

<div id = "container">
		<form action="${pageContext.servletContext.contextPath}/createaccount" method = "post">
		
  			<label for="usermail">Email: </label>
  	 	    <input type="email" name="email" placeholder="yourname@ycp.edu" value = "${user.email}" required><br>
  	        
  	        <label for="password">Password: </label>
  	        <input type="password" name="pass" placeholder="password" value = "${user.password}" required><br>
  	        
  	        <label for="password">Password Reentry: </label>
  	        <input type="password" name="reenter" placeholder="reenter password" value = "" required>
  	        
  	        <br>
  	        
  	        <label for="major">Major: </label>
  	        <input type="text" name="major" placeholder="Major" value = "${user.Major}" required>
  	        
  	        <br>
  	        
  	        <label for="GPA">GPA (from 0.0-4.0): </label>
  	        <input type="number" step = "0.1" name="GPA" min="0.0" max="4.0" placeholder="GPA" value = "${user.GPA}" required>
  	        
  	        <br>
  	        
  	        <label for="year">Year: </label>
  	        
  	        <br>
  	        
  	        <table>
  	        	<input type="radio" name="year" value="Freshman" checked required> Freshman<br>
  				<input type="radio" name="year" value="Sophomore" required> Sophomore <br>
  				<input type="radio" name="year" value="Junior" required> Junior<br>
  				<input type="radio" name="year" value="Senior" required> Senior<br>
  	        </table>
  	       
  	        <input id = "ca_accountButton" type="submit" value = "Create Account">
  	       
  	    </form>	
  	    
  	    <div style="color: #FF0000;">${errorMessage}</div>
	</body>