<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
		
		<style>
    		.headerStyle{
    			color: #096333;
    		}
		</style>
		
	</head>

	<body>
		
		<h1 class = "headerStyle">
			This is the department of ${department.name}!
		</h1>	
		
		<br>
		
		<div>
			<c:forEach items="${department.courses}" var="course">
    			<tr>      
       				<td>
       					<form action="${pageContext.request.contextPath}/course">
							<button type="submit">${course.name} </button>
							<input name="courseName" type="hidden" value="${course.name}" />
							<input name="departmentName" type="hidden" value="${department.name}" />
						</form>
       				</td> 
    			</tr>
    			<br>
    			<br>
			</c:forEach>
			
		</div>
		
		<br>

		
		<pre>
 			
		</pre>
		
		
		
	</body>
</html>
