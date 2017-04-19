<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Home</title>
		
		<style type="text/css">
    		<%@include file="loginstyle.css" %>
    	.active {
		background-color:#4CAF50;
		}
		</style>
		
		<!--This is commented out until I figure
		out how to do both .css files at once
		  <style type="text/css">
    		<%@include file="style.css" %>
    		
		</style> -->
		
		
		<p3>
		
		<table>
		<form action="${pageContext.request.contextPath}/index">
			<button type="submit">Index</button>
		</form>
		
		
		<form action="${pageContext.request.contextPath}/login">
		<button type="submit">Login</button>
		</form>
		
		
		<form action="${pageContext.request.contextPath}/createaccount">
		<button type="submit">Create Account</button>
		</form>
		</table>
		
		</p3>
		

	</head>

	<body>
		<ul>
  			<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
  			<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
  			<li><a href="http://localhost:8081/tjones50/login">Login</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
		</ul>
		
		<h1 class = "headerStyle">
			Select Your Department:
		</h1>
		
		<c:forEach items="${home.departments}" var="department">
    			<tr>      
       				<td>
       				
						<form action="${pageContext.request.contextPath}/department">
							<button type="submit">${department.name}</button>
							<input name="departmentName" type="hidden" value="${department.name}" />
						</form>
					</td> 
    			</tr>
    			<br>
    			<br>
			</c:forEach>
		
		
		
	</body>
</html>
