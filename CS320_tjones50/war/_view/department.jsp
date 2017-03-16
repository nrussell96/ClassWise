<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
	</head>

	<body>
		
		<div>
			This is the department of ${department.name}!
		</div>	
		
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
