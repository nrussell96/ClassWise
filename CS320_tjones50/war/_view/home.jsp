<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Home</title>
		
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
		
		<style type="text/css">
    		<%@include file="style.css" %>
    		
		</style>
	</head>

	<body>
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
