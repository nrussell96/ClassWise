<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Home</title>
<style type="text/css">
<%@
include file ="style.css " %> .active {
	background-color: #096333;
}
</style>
</head>
<body>
	<ul>
		<li><a class="active" href="http://localhost:8081/tjones50/index">ClassWise</a></li>
		<li><a href="http://localhost:8081/tjones50/home">Courses</a></li>
		<c:choose>
			<c:when test="${empty email}">
				<li><a
					href="http://localhost:8081/tjones50/login?from=${pageContext.request.contextPath}/home">Login</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				<li><a href="http://localhost:8081/tjones50/userAccount">Account
						Information</a></li>
				<li><a>Hello, ${email}</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	<h1 class="headerStyle">Select Your Department:</h1>
	<div class="dropdown">
		<h2 class="headerStyle">A ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="0" end="3" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">B ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="4" end="6" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">C ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="7" end="11" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">E ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="12" end="18" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">F ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="19" end="22" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">G ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="23" end="25" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">H ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="26" end="29" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">I ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="30" end="34" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">L ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="35" end="36" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">M ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="37" end="46" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">N ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="47" end="48" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">P ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="49" end="55" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">Q ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="56" end="56" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">R ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="57" end="61" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">S ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="62" end="68" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">T ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="69" end="69" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<div class="dropdown">
		<h2 class="headerStyle">W ,</h2>
		<div class="dropdown-content">
			<c:forEach begin="70" end="71" items="${home.departments}"
				var="department">
				<tr>
					<td>
						<form action="${pageContext.request.contextPath}/home"
							class="departments" method="post">
							<button type="submit" class="home">${department.name}</button>
							<input name="departmentName" type="hidden"
								value="${department.name}" /> <input type="hidden" name="from"
								value="${pageContext.request.requestURI}/home">
						</form>
					</td>
				</tr>
				<br>
				<br>
			</c:forEach>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<footer>
		<ul>
			<li></li>
		</ul>
	</footer>
</body>
</html>