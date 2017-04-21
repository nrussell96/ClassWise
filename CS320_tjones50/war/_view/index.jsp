<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index view</title>
		
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
			<!--<li><a>Hello, ${user.email}</a></li>-->
			</c:if>
		</ul>
	
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
		
		<form action="${pageContext.request.contextPath}/userAccount">
		<button type="submit">Account</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/login">
		<button type="submit">Login</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/createaccount">
		<button type="submit">Create Account</button>
		</form>
		
		<br>
		
		<form action="${pageContext.request.contextPath}/giveAdvice">
		<button type="submit">Give Advice</button>
		</form>
		
		<br>
		
	</body>
</html>
