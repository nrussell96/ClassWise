<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Home</title>
		
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
			<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
		</ul>
		
		<h1 class = "headerStyle">
			Select Your Department:
		</h1>
		
		<c:forEach items="${home.departments}" var="department">
    			<tr>      
       				<td>
       					<p3>
						<form action="${pageContext.request.contextPath}/department">
							<button type="submit" class = "home">${department.name}</button>
							<input name="departmentName" type="hidden" value="${department.name}" />
						</form>
						</p3>
					</td> 
    			</tr>
    			<br>
    			<br>
			</c:forEach>
		
		
		
	</body>
</html>
