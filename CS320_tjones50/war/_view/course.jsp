<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
					
		<style type="text/css">
    		<%@include file="style.css" %>
		</style>
		
		
	</head>

	<body>
		
		<h1>
			Here is the Advice for ${course.department.name} ${course.name}!
		</h1>	
		
		<p2>
			Average grade recieved was ${course.aveGrade}
		</p2>
		
		<p2>
			Average difficulty was ${course.aveRatings.difficulty}
		</p2>
		
		<p2>
			Average instruction quality was ${course.aveRatings.instruction}
		</p2>
		
		<p2>
			Average cost of supplies was ${course.aveRatings.suppliesCost}
		</p2>
		
		<p2>
			Average enjoyment was ${course.aveRatings.enjoyment}
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
						Student got a ${advice.gradeRecieved} in the class
						
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
