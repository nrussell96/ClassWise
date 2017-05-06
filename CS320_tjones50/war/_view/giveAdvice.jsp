<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<head>
		<title>Give Advice</title>				
			<style type="text/css">
    			<%@include file="style.css" %>
    	    		.active {
					background-color:#096333;
					}	    		
			</style> 
	</head>
<body>
	<h3>Enter your advice for ${course.department.name} -
		${course.name}</h3>
	<%-- This part right below is needed to save all of the typed in values and send them to the course servlet to get displayed --%>
	<form action="${pageContext.servletContext.contextPath}/giveAdvice"
		method="post">
		<div class="tableRatings">
			<table>
				<tr>
					<td class="label">Difficulty (1-10):</td>
					<td><input type="number" step="1" name="difficulty" min="1"
						max="10" placeholder="1"
						value="${adviceModel.adviceRating.difficulty}" required></td>
				</tr>
				<tr>
					<td class="label">Quality of instruction (1-10):</td>
					<td><input type="number" step="1" name="instruction" min="1"
						max="10" placeholder="1"
						value="${adviceModel.adviceRating.instruction}" required></td>
				</tr>
				<tr>
					<td class="label">Cost of Supplies (1-10):</td>
					<td><input type="number" step="1" name="suppliesCost" min="1"
						max="10" placeholder="1"
						value="${adviceModel.adviceRating.suppliesCost}" required></td>
				</tr>
				<tr>
					<td class="label">Enjoyment (1-10):</td>
					<td><input type="number" step="1" name="enjoyment" min="1"
						max="10" placeholder="1"
						value="${adviceModel.adviceRating.enjoyment}" required></td>
				</tr>
			</table>
		</div>
		<div class="tableRatingsLower">
			<table>
				<tr>
					<td class="label">Grade Received (0-4):</td>
					<td><input type="radio" name="gradeReceived" value="4.0"
						checked> 4.0</td>
					<td><input type="radio" name="gradeReceived" value="3.5">3.5</td>
					<td><input type="radio" name="gradeReceived" value="3.0">3.0</td>
					<td><input type="radio" name="gradeReceived" value="2.5">2.5</td>
					<td><input type="radio" name="gradeReceived" value="2.0">2.0</td>
					<td><input type="radio" name="gradeReceived" value="1.0">1.0</td>
				</tr>
				<tr>
					<td class="label">Semester:</td>
					<td><input type="radio" name="semester" value="Fall" checked>
						Fall</td>
					<td><input type="radio" name="semester" value="Spring">
						Spring</td>
					<td><input type="radio" name="semester" value="Summer">Summer</td>
				</tr>
			</table>
		</div>
		<div class="tableRatingsBottom">
			<table>
				<tr>
					<td class="label">Year the class was taken:</td>
					<td><input type="number" name="classYear" min="2010"
						max="2100" placeholder="2010" value="${adviceModel.classYear}"
						required></td>
				</tr>
				<tr>
					<td class="label">Professor:</td>
					<td><input type="text" name="professor" size="12"
						value="${adviceModel.professor}" /></td>
				</tr>
				<tr>
					<br>
					<br>
					<td class="label">What advice do you have for students?</td>
					<td><textarea rows="5" cols="50" name="text"
							value="${adviceModel.text}"> </textarea></td>
				</tr>
			</table>
			<button type="submit">Submit Advice</button>
			<input name="courseName" type="hidden" value="${course.name}" /> <input
				name="departmentName" type="hidden" value="${department.name}" /> <input
				name="difficulty" type="hidden"
				value="${model.adviceRating.difficulty}" /> <input
				name="instruction" type="hidden"
				value="${model.adviceRating.instruction}" /> <input
				name="suppliesCost" type="hidden"
				value="${model.adviceRating.suppliesCost}" /> <input
				name="enjoyment" type="hidden"
				value="${model.adviceRating.enjoyment}" /> <input
				name="gradeReceived" type="hidden" value="${model.gradeRecieved}" />
			<input name="semester" type="hidden" value="${model.semester}" /> <input
				name="classYear" type="hidden" value="${model.classYear}" /> <input
				name="professor" type="hidden" value="${model.professor}" /> <input
				name="text" type="hidden" value="${model.text}" />
			<!-- W3Schools Back Button: https://www.w3schools.com/jsref/met_his_back.asp -->
			<button type="button" name="cancel" onclick="history.back()">Cancel</button>
		</div>
	</form>
</body>
</html>