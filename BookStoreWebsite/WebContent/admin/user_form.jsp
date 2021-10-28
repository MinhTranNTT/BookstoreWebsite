<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- import header jsp admin -->
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>Create New User</h2>
	</div>

	<div align="center">
		<form action="create_user" method="post" onclick="return validateFormInput()">
		<table>
			<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" name="email" id="email"
						size="20" /></td>
			</tr>
	
			<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullname"
						id="fullname" size="20" /></td>
			</tr>
	
			<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" name="password"
						id="password" size="20" /></td>
			</tr>
	
			<tr>
					<td>&nbsp;</td>
			</tr>
	
			<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save" /> <input type="button" value="Cancel"
						onclick="javascript:history.go(-1);" />
					</td>
			</tr>
		</table>
		</form>

	</div>

	<!-- import footer jsp admin -->
	<jsp:directive.include file="footer.jsp" />
</body>
<!-- script js-->
<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullname = document.getElementById("fullname");
		var fieldPassword = document.getElementById("password");

		if (fieldEmail.value.length == 0) {
			alert("Email is required!");
			fieldEmail.focus();
			return false;
		}

		if (fieldFullname.value.length == 0) {
			alert("Fullname is required!");
			fieldFullname.focus();
			return false;
		}

		if (fieldPassword.value.length == 0) {
			alert("Password is required!");
			fieldPassword.focus();
			return false;
		}

		return true;
	}
</script>
</body>
</html>