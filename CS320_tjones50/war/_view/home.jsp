<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Home</title>
		
		<style>
    		.headerStyle{
    			color: #096333;
    		}
		</style>
	</head>

	<body>
		<h1 class = "headerStyle">
			Select Your Department:
		</h1>
		
		
		<br>
		<br>
		
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
