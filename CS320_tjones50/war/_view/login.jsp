<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
	<title>Login</title>
		<style type="text/css">
    		<%@include file="style.css" %>
    	.active {
		background-color:#096333;
		}
		</style>
		
	</head>
	<body>
		<ul>
  			<!--<li><form action="${pageContext.servletContext.contextPath}/home" method = "get">
  				<a><input class="active" type = "submit" value = classwise></a>
  			</form></li>-->
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
		<form action="${pageContext.servletContext.contextPath}/login" method = "post">
		
  			<label for="usermail">Email: </label>
  	 	    <input type="email" name="email" placeholder="yourname@ycp.edu" value = "${email}" required><br>
  	        <label for="password">Password: </label>
  	        <input type="password" name="pass" placeholder="password" value = "${password}" required><br>
  	        <input id = "ca_accountButton" type="submit" value = "Login">
  	     </form>
  	    
  	    <br>
  	    
  	    <form action="${pageContext.servletContext.contextPath}/createaccount" method = "get">
  	    
  	        <div>
  	        	<input id = "ca_accountButton" type="submit" value = "New Account?">
  	        </div>
  	    </form>
  	    
		</div>
 		   
			<div style="color: #FF0000;">${errorMessage}</div>
			
		</form>
	</body>
</html>