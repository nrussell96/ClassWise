<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
	<head>
		<title>Course</title>
		<style type="text/css">
    		<%@include file="style.css" %>	
		</style>
	</head>
	<body>
		<ul>
  			<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
  			<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
  			<c:choose>
  				<c:when test = "${empty email}">
  					<li><a href="http://localhost:8081/tjones50/login?from=${pageContext.request.contextPath}/department">Login</a></li>
  				</c:when>
  				<c:otherwise>
  					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
  					<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
  					<li><a>Hello, ${email}</a></li>
  				</c:otherwise>
  			</c:choose>
		</ul>
		<h1>
			${department.name}
		</h1>
		<div class = "menuContainer">	
			<c:forEach items="${department.courses}" var="course">
	    		<tr>      
	       			<td>
	       				<form action="${pageContext.request.contextPath}/department" method = "post">
							<button type="submit" class = "department">${course.name} </button>
							<input name="courseName" type="hidden" value="${course.name}" />
							<input name="departmentName" type="hidden" value="${department.name}" />
							<input type="hidden" name="from" value="${pageContext.request.requestURI}/department">
						</form>
	       			</td> 
	    		</tr>
	    		<br><br>
			</c:forEach>
		</div>	
		<br>
		<footer><ul><li></li></ul> </footer>
	</body>
</html>
