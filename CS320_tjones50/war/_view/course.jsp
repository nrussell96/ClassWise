<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
		
	</head>

	<body>
		
		<h1>
			Here is the Advice for ${course.department.name} ${course.name}!
		</h1>	
		
		<div>
			Average grade for the class was ${course.aveGrade}
		</div>
		
		<br>
		
		<div>
			Average difficulty was ${course.aveRatings.difficulty}
			<br>
			Average instruction quality was ${course.aveRatings.instruction}
			<br>
			Average cost of supplies was ${course.aveRatings.suppliesCost}
			<br>
			Average enjoyment was ${course.aveRatings.enjoyment}
		</div>
		
		<br>
		
		<div>
		<h2>
			Advice
		</h2>
			<c:forEach items="${course.arrAdvice}" var="advice">
    			<tr>      
       				<td>
       				<c:if test="${advice.approved}">
       					----------------------------------------------------------------------------------------------------------------------------------------------
						
						<br>
						
						Course was taken ${advice.semester} of ${advice.classYear}
						
						<br>
						<br>
						
						Advice was left by a ${advice.userMajor} student who was a ${advice.userClassYear} and had a GPA of ${advice.userGPA }.
						<br>
						Student got a ${advice.gradeRecieved} in the class
						
						<br>
						
						<br>
       				
						Difficulty was ${advice.adviceRating.difficulty}
						
						<br>
						
						Instruction quality was ${advice.adviceRating.instruction}
						
						<br>
						
						Cost of supplies was ${advice.adviceRating.suppliesCost}
						
						<br>
						
						Enjoyment was ${advice.adviceRating.enjoyment}
						
						<br>
						----------------------------------------------------------------------------------------------------------------------------------------------
       					<br>
						
					</c:if>
       					
       				</td> 
    			</tr>
    			<br>
    			<br>
			</c:forEach>
			
		</div>
		
		<pre>
 			
		</pre>
		
		
		
	</body>
</html>
