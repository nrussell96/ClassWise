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
		<h1 class = "headerStyle">
			Select Your Department:	
		</h1>
		<c:forEach items="${home.departments}" var="department">
    			<tr>      
       				<td>
						<form action="${pageContext.request.contextPath}/home" class = "departments" method = "post">
							<button type="submit" class = "home">${department.name}</button>
							<input name="departmentName" type="hidden" value="${department.name}" />
						</form>
					</td> 
    			</tr>
    			<br>
    			<br>
			</c:forEach>
		<footer><ul><li></li></ul></footer>
	</body>
</html>