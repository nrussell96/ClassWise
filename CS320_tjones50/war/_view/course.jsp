<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Course</title>
	</head>

	<body>
		
		<div>
			Here is the advice for ${game.department.name} ${game.name}!
		</div>	
		
		<br>
		
		<div>
			Average grade for the class was ${game.aveGrade}
		</div>
		
		<br>
		
		<div>
			Average difficulty was ${game.aveRatings.difficulty}
			<br>
			Average instruction quality was ${game.aveRatings.instruction}
			<br>
			Average cost of supplies was ${game.aveRatings.suppliesCost}
			<br>
			Average enjoyment was ${game.aveRatings.enjoyment}
		</div>
		
		<br>
		
		<pre>
 			
		</pre>
		
		
		
	</body>
</html>
