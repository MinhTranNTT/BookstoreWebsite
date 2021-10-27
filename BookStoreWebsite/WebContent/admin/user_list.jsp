<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Users - Evergreen - Book Store Admin</title>
</head>
<body>
	
	<!-- import header jsp admin -->
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Users Management</h2>
		<h3><a href="user_form.jsp">Create New User</a></h3>
	</div>
	
	<div align="center">
		<h4><i>${message}</i></h4>
	</div>
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach var="user" items="${listUsers}" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${user.userId}</td>
				<td>${user.email}</td>
				<td>${user.fullName}</td>
				<td>
					<a href="">Edit</a>&nbsp;
					<a href="">Delete</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- import footer jsp admin -->
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		function confirmDelete(userID) {
			
			if(confirm('Are you sure you want to delete the user with ID '+userID + ' ?' )){
				window.location = 'delete_user?id=' +userID;
			}
		}
	</script>
</body>
</html>