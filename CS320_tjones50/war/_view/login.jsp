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
  			<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
  			<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
  			<li><a href="http://localhost:8081/tjones50/login">Login</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<c:if test="${!empty email}">
				<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
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