<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Admin Account</title>
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
			Admin Account
		</h1>
		<h2>
			Account info:
		</h2>	
		<p2>
			Your email is: ${admin.email}
		</p2>
		<p2>
			Your password is: ${admin.password}
		</p2>
		<br><br>
		<h2>
			Advice
		</h2>
			<c:forEach items="${admin.arrAdvice}" var="advice">
    			<tr>      
       				<td>
						<p1>
							Course was taken ${advice.semester} of ${advice.classYear}
							<br><br>
							Advice was left by a ${advice.userMajor} student who was a ${advice.userClassYear} and had a GPA of ${advice.userGPA }.
							<br>
							Student got a ${advice.gradeReceived} in the class taught by ${advice.professor }
							<br><br>
							"${advice.text }"
							<br><br>
							Difficulty was ${advice.adviceRating.difficulty}
							<br>
							Instruction quality was ${advice.adviceRating.instruction}
							<br>
							Cost of supplies was ${advice.adviceRating.suppliesCost}
							<br>
							Enjoyment was ${advice.adviceRating.enjoyment}
							<form action="${pageContext.servletContext.contextPath}/admin" method="post">
								<button type="submit" class = "approve">Approve Advice</button>
								<input name="action" type="hidden" value="approve" />
								<input name="adviceId" type="hidden" value="${advice.adviceId}" />
							</form>
							<br><br>
							<form>
								<button type="submit" class = "deactiveate">Deactivate Account</button>
								<input name="action" type="hidden" value="deactiveate" />
								<input name="adviceId" type="hidden" value="${advice.adviceId}" />
							</form>
						</p1>
       				</td> 
    			</tr>
			</c:forEach>
	</body>
</html>