<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		
		<title>Course</title>
						
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
  			<li><a href="http://localhost:8081/tjones50/login?from=${pageContext.request.contextPath}/course">Login</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<c:if test="${!empty email}">
				<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				<li><a>Hello, ${email}</a></li>
			</c:if>
		</ul>
		
			<form action="${pageContext.request.contextPath}/giveAdvice">
			<button type="submit" class = "giveAdvice">Give Advice</button>
			<input name="courseName" type="hidden" value="${course.name}" />
			<input name="departmentName" type="hidden" value="${department.name}" />
			<input name="adviceId" type="hidden" value="${advice.adviceId}" />
			<input name="flags" type="hidden" value="${advice.flags}" />
			</form>
		<h1>
		
			Here is the Advice for ${course.department.name} - ${course.name}!
			
		</h1>	

		
	<div class = "ratings">
		<p2>
			Average grade: <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveGrade}"/> out of 4.0
		</p2>
		
		<p2>
			Average difficulty: <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.difficulty}"/> out of 10
		</p2>
		
		<p2>
			Average of instruction quality: <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.instruction}"/> out of 10
		</p2>
		
		<p2>
			Average supply cost: <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.suppliesCost}"/> out of 10
		</p2>
		
		<p2>
			Average enjoyment: <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.enjoyment}"/> out of 10
		</p2>
	</div>			
		<br>
		<br>
	
		
		<div>
		<h2>
			Advice
		</h2>
		
		<p3>
			<form action="${pageContext.servletContext.contextPath}/course" method="post">
			
			<table>
				<tr>
					<td><button type="submit">Sort Advice: </button></td>
					<td><input type="radio" name="sort" value="Newest" checked> Newest First</td>
					<td><input type="radio" name="sort" value="Grade Received"> Grade Received</td>
  					<td><input type="radio" name="sort" value="Student GPA"> Student GPA</td>
  					<td><input type="radio" name="sort" value="Semester Taken">Semester Taken</td>
  					<td><input type="radio" name="sort" value="Student Major">Student Major</td>
  					<td><input type="radio" name="sort" value="Year Taken">Year Taken</td>
  					<td><input type="radio" name="sort" value="Professor">Professor</td>
  					<td><input type="radio" name="sort" value="Course Difficulty">Course Difficulty</td>
  					<td><input type="radio" name="sort" value="Course Instruction">Course Instruction</td>
  					<td><input type="radio" name="sort" value="Course Enjoyment">Course Enjoyment</td>
  					<td><input type="radio" name="sort" value="Price of Supplies">Price of Supplies</td>
				</tr>
			</table>
			
			
			<input name="courseName" type="hidden" value="${course.name}" />
			<input name="departmentName" type="hidden" value="${department.name}" />
			<input name="flag" type="hidden" value="false" />
			</form>
			</p3>
		
			<c:forEach items="${course.arrAdvice}" var="advice">
    			<tr>      
       				<td>
       				<c:if test="${advice.approved}">
						
						<p1>

						Course was taken ${advice.semester} of ${advice.classYear}
						
						<br>
						<br>
						
						Advice was left by a ${advice.userMajor} student who was a ${advice.userClassYear} and had a GPA of ${advice.userGPA }.
						<br>
						Student got a ${advice.gradeReceived} in the class taught by ${advice.professor }
						
						<br>
						<br>
						
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
						
						<form action="${pageContext.servletContext.contextPath}/course" method="post">
						<button type="submit" class = "flags">Flag Inappropriate Advice</button>
						<input name="courseName" type="hidden" value="${course.name}" />
						<input name="departmentName" type="hidden" value="${department.name}" />
						<input name="adviceId" type="hidden" value="${advice.adviceId}" />
						<input name="flag" type="hidden" value="true" />
						<input name="sort" type="hidden" value="Newest" />
						
						</form>
						<div id = "flag_content">
						${advice.flags} flagged this advice
						</div>
						</p1>
						
						
					</c:if>
       					
       				</td> 
    			</tr>
			</c:forEach>
			
		</div>
		
		<pre>
 			
		</pre>
		<footer><ul><li></li></ul> </footer>
	</body>
</html>
