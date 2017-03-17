<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
		
		<style>
			.adviceStyle {
    			float: left;
    			margin: 5px;
    			padding: 15px;
    			width: 600px;
    			height: 400px;
    			border: 1px solid #096333;
    		} 
    		.headerStyle{
    			color: #096333;
    		}
</style>
		
		
	</head>

	<body>
		
		<h1 class = "headerStyle">
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
		<h2 class = "headerStyle">
			Advice
		</h2>
			<c:forEach items="${course.arrAdvice}" var="advice">
    			<tr>      
       				<td>
       				<c:if test="${advice.approved}">
						
						<div class="adviceStyle" >

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
						
						</div>
						
					</c:if>
       					
       				</td> 
    			</tr>
			</c:forEach>
			
		</div>
		
		<pre>
 			
		</pre>
		
		
		
	</body>
</html>
