<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Account</title>
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
  			<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<c:if test="${!empty email}">
				<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				<li><a>Hello, ${email}</a></li>
			</c:if>
		</ul>
		<h1>
			Welcome to your Account!
		</h1>
		<h2>
			Account info:
		</h2>	
		<p2>
			Your email is: ${user.email}
		</p2>
		<p2>
			Your password is: ${user.password}
		</p2>
		<p2>
			Your class year is: ${user.userClassYear}
		</p2>
		<p2>
			Your major is: ${user.major}
		</p2>
		<p2>
			Your GPA is: ${user.GPA}
		</p2>
		<br><br>
		<h2>
			Advice
		</h2>
		<c:forEach items="${user.arrAdvice}" var="advice">
    		<tr>      
       			<td>
       				<c:if test="${advice.approved}">
						<p1>
							Course was taken ${advice.semester} of ${advice.classYear}
							<br><br>
							Advice was left by a ${advice.userMajor} student who was a ${advice.userClassYear} and had a GPA of ${advice.userGPA }.
							<br>
							Student got a ${advice.gradeReceived} in the class taught by ${advice.professor }
							<br><br>
							"${advice.text }"
							<br>
							<br>
							Difficulty was ${advice.adviceRating.difficulty}
							<br>
							Instruction quality was ${advice.adviceRating.instruction}
							<br>
							Cost of supplies was ${advice.adviceRating.suppliesCost}
							<br>
							Enjoyment was ${advice.adviceRating.enjoyment}
						</p1>
					</c:if>
       					
       			</td> 
    		</tr>
		</c:forEach>
		<footer><ul><li></li></ul> </footer>
	</body>
</html>
