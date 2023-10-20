<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Application</title>
</head>
<style>
body {
	align-content: center;
	justify-content: center;
	margin-left: 100px;
	margin-right: 100px;
}

form {
	margin-top: 100px;
	margin-bottom: 20px;
}

button {
	padding: 5px;
}

tr {
	margin: 2px;
}

td {
	padding: 5px;
}
</style>
<body>
	<h1>ID: <c:out value="${student.id }"></c:out></h1>
	<form action="${pageContext.request.contextPath }/HomeController"
		method="post">
		First Name: <input type="text" name="firstName"
			placeholder="First Name" value="${student.firstName }"> Last Name: <input type="text"
			name="lastName" placeholder="Last Name" value="${student.lastName }">
		<button>Add</button>
	</form>
	<br />
	<br />
	<table border="1">
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${students }" var="student">
			<tr>
				<td>${student.id }</td>
				<td>${student.firstName }</td>
				<td>${student.lastName }</td>
				<td><a
					href="${pageContext.request.contextPath }/HomeController?action=EDIT&id=${student.id }">Edit</a>
					<a href="${pageContext.request.contextPath }/HomeController?action=DELETE&id=${student.id }">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>