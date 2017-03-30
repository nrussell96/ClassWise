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
			Enter your advice for ${course.name}
		</h1>	
		
	
		<%-- This part right below is needed to save all of the typed in values and send them to the course servlet to get displayed --%>
			<form action="${pageContext.servletContext.contextPath}/course" method="post">
			<table>
				<tr>

					<td class="label">Difficulty (1-10):</td>
					<td><input type="text" name="difficulty" size="12" value="${adviceModel.adviceRating.difficulty}" /></td>
				</tr>
				<tr>
					<td class="label">Quality of instruction (1-10):</td>
					<td><input type="text" name="instruction" size="12" value="${adviceModel.adviceRating.instruction}" /></td>
				</tr>
				<tr>
					<td class="label">Cost of Supplies (1-10):</td>
					<td><input type="text" name="suppliesCost" size="12" value="${adviceModel.adviceRating.suppliesCost}" /></td>
				</tr>
				<tr>
					<td class="label">Enjoyment (1-10):</td>
					<td><input type="text" name="enjoyment" size="12" value="${adviceModel.adviceRating.enjoyment}" /></td>
				</tr>
				<tr>
					<td class="label">Grade Received (0-4):</td>
					<td><input type="text" name="gradeReceived" size="12" value="${adviceModel.gradeRecieved}" /></td>
				</tr>
				<tr>
					<td class="label">Semester (Fall, Spring, or Summer):</td>
					<td><input type="text" name="semester" size="12" value="${adviceModel.semester}" /></td>
				</tr>
				<tr>
					<td class="label">Year the class was taken:</td>
					<td><input type="text" name="classYear" size="12" value="${adviceModel.classYear}" /></td>
				</tr>
				<tr>
					<td class="label">What advice do you have for students?:</td>
					<td><textarea rows="4" cols="50" name="text" value="${adviceModel.text}" </td>
					</textarea>
				</tr>
			</table>
							<button type="submit">Submit Advice</button>
							<input name="courseName" type="hidden" value="${course.name}" />
							<input name="departmentName" type="hidden" value="${department.name}" />
							<input name="difficulty" type="hidden" value="${model.adviceRating.difficulty}" />
							<input name="instruction" type="hidden" value="${model.adviceRating.instruction}" />
							<input name="suppliesCost" type="hidden" value="${model.adviceRating.suppliesCost}" />
							<input name="enjoyment" type="hidden" value="${model.adviceRating.enjoyment}" />
							<input name="gradeReceived" type="hidden" value="${model.gradeRecieved}" />
							<input name="semester" type="hidden" value="${model.semester}" />
							<input name="classYear" type="hidden" value="${model.classYear}" />
							<input name="text" type="hidden" value="${model.text}" />
				</form>
		

	</body>
</html>
