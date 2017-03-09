<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
	
		Select Your Application:
		
		<pre>
 			
		</pre>
		
		
		<br>
		
		<form action="${pageContext.request.contextPath}/addNumbers">
		<button type="submit">Add Numbers</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/multNumbers">
		<button type="submit">Multiply Numbers</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/guessingGame">
		<button type="submit">Guessing Game</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/course">
		<button type="submit">Course</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/department">
		<button type="submit">Department</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/home">
		<button type="submit">Home</button>
		</form>
		
		<br>
		
	</body>
</html>
