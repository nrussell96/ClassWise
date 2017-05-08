<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
	<title>Update Account Information</title>		
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
  			<c:choose>
  				<c:when test = "${empty email}">
  					<li><a href="http://localhost:8081/tjones50/login?from=${pageContext.request.contextPath}/course">Login</a></li>
  				</c:when>
  				<c:otherwise>
  					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
  					<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
  					<li><a>Hello, ${email}</a></li>
  				</c:otherwise>
  			</c:choose>
		</ul>
		<div id ="createAccountHeader">
			
		</div>
		<br>
		<br>
		<div id = "container">
			<form action="${pageContext.servletContext.contextPath}/updateaccount" method = "post">
	  	 	    <br>
	  	        <label for="password">New Password: </label>
	  	        <input type="password" name="pass" placeholder="password" value = "${user.password}" required>
	  	        <br>
	  	        <label for="password">New Password Reentry: </label>
	  	        <input type="password" name="reenter" placeholder="reenter password" value = "${user.password}" required>
	  	        <br>
	  	        <label for="major">Major: </label>
	  	        <input type="text" name="major" placeholder="Major" value = "${user.major}" required>
	  	        <br>
	  	        <label for="GPA">GPA (from 0.0-4.0): </label>
	  	        <input type="number" step = "0.1" name="GPA" min="0.0" max="4.0" placeholder="GPA" value = "${user.GPA}" required>
	  	        <br>
	  	        <label for="year">Class Year: </label>
	  	        <br>
	  	        <table>
	  	        <c:if test="${user.userClassYear == 'Freshman'}">
	  	        	<input type="radio" name="year" value="Freshman" checked required> Freshman
	  	        	<br>
	  				<input type="radio" name="year" value="Sophomore" required> Sophomore 
	  				<br>
	  				<input type="radio" name="year" value="Junior" required> Junior
	  				<br>
	  				<input type="radio" name="year" value="Senior" required> Senior
	  			</c:if>
	  			<c:if test="${user.userClassYear == 'Sophomore'}">
	  	        	<input type="radio" name="year" value="Freshman" required> Freshman
	  	        	<br>
	  				<input type="radio" name="year" value="Sophomore" checked required> Sophomore 
	  				<br>
	  				<input type="radio" name="year" value="Junior" required> Junior
	  				<br>
	  				<input type="radio" name="year" value="Senior" required> Senior
	  			</c:if>
	  			<c:if test="${user.userClassYear == 'Junior'}">
	  	        	<input type="radio" name="year" value="Freshman" required> Freshman
	  	        	<br>
	  				<input type="radio" name="year" value="Sophomore" required> Sophomore 
	  				<br>
	  				<input type="radio" name="year" value="Junior" checked required> Junior
	  				<br>
	  				<input type="radio" name="year" value="Senior" required> Senior
	  			</c:if>
	  			<c:if test="${user.userClassYear == 'Senior'}">
	  	        	<input type="radio" name="year" value="Freshman" required> Freshman
	  	        	<br>
	  				<input type="radio" name="year" value="Sophomore" required> Sophomore 
	  				<br>
	  				<input type="radio" name="year" value="Junior" required> Junior
	  				<br>
	  				<input type="radio" name="year" value="Senior" checked required> Senior
	  			</c:if>
	  				<br>
	  	        </table>
	  	        <input id = "ca_accountButton" type="submit" value = "Update Information">  
	  	    </form><!-- "container" -->
		</div>
	  	<div style="color: #FF0000;">${errorMessage}</div>
	</body>
</html>
