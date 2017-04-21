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
  			<li><a href="http://localhost:8081/tjones50/login">Login</a></li>
 			<li><a href="http://localhost:8081/tjones50/createaccount">Create an Account</a></li>
			<c:if test="${!empty email}">
				<li><a href="http://localhost:8081/tjones50/userAccount">Account Information</a></li>
			</c:if>
		</ul>
		<p3>
		<form action="${pageContext.request.contextPath}/giveAdvice">
			<button type="submit">Give Advice</button>
			<input name="courseName" type="hidden" value="${course.name}" />
			<input name="departmentName" type="hidden" value="${department.name}" />
			<input name="adviceId" type="hidden" value="${advice.adviceId}" />
			<input name="flags" type="hidden" value="${advice.flags}" />
		</form>
		</p3>
		
		<h1>
		
			Here is the Advice for ${course.department.name} - ${course.name}!
		</h1>	
		
		
		
		<p2>
			Average grade received was <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveGrade}"/>
		</p2>
		
		<p2>
			Average difficulty was <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.difficulty}"/>
		</p2>
		
		<p2>
			Average instruction quality was <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.instruction}"/>
		</p2>
		
		<p2>
			Average cost of supplies was <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.suppliesCost}"/>
		</p2>
		
		<p2>
			Average enjoyment was <fmt:formatNumber type="number" maxFractionDigits="2" value="${course.aveRatings.enjoyment}"/>
		</p2>
		
		<br>
		<br>
	
		
		<div>
		<h2>
			Advice
		</h2>
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
						<button type="submit">Flag Inappropriate Advice</button>
						<input name="courseName" type="hidden" value="${course.name}" />
						<input name="departmentName" type="hidden" value="${department.name}" />
						<input name="adviceId" type="hidden" value="${advice.adviceId}" />
						<input name="flags" type="hidden" value="${advice.flags}" />
						
						</form>
						
						Flags: ${advice.flags}
						</p1>
						
						
					</c:if>
       					
       				</td> 
    			</tr>
			</c:forEach>
			
		</div>
		
		<pre>
 			
		</pre>
		
	</body>
</html>
