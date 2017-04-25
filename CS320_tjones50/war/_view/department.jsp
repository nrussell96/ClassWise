<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
		
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
				<!--  <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li> -->
				<li><a>Hello, ${email}</a></li>
				<li><form action="${pageContext.request.contextPath}/logout" id = "none" method="post">
    				<a><input type="submit" value="Logout" /></a>
				</form></li>
			</c:if>
		</ul>
		
		<h1>
			This is the department of ${department.name}!
		</h1>	
		
		
		
		<br>
		
		
			<c:forEach items="${department.courses}" var="course">
    			<tr>      
       				<td>
       					<form action="${pageContext.request.contextPath}/department" method = "post">
							<button type="submit" class = "department">${course.name} </button>
							<input name="courseName" type="hidden" value="${course.name}" />
							<input name="departmentName" type="hidden" value="${department.name}" />
						</form>
       				</td> 
    			</tr>
    			<br>
    			<br>
			</c:forEach>	
		
		<br>

		
		<pre>
 			
		</pre>
		
		
		
	</body>
</html>
