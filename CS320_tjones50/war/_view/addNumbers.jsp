<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!--*Example pulled from Lab2A Web Apps on the course web page
 * from Dr. Hake to assist in the structure of our jsps.
 -->
<html>
	<head>
		<title>Add Numbers</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<c:if test="${! empty game.errorMessage}">
			<div class="error">${game.errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="post">
			<table>
				<tr>
					<td class="label">First number:</td>
					<td><input type="text" name="first" size="12" value="${game.addNum1}" /></td>
				</tr>
				<tr>
					<td class="label">Second number:</td>
					<td><input type="text" name="second" size="12" value="${game.addNum2}" /></td>
				</tr>
				<tr>
					<td class="label">Third number:</td>
					<td><input type="text" name="third" size="12" value="${game.addNum3}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${game.addResult}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Numbers!">
		</form>
	</body>
</html>