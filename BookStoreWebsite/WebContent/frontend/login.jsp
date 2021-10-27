<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!-- import header jsp -->
	<jsp:directive.include file="header.jsp"/>

	<!-- import Main content -->
	<div align="center">
		<h2>Please Login:</h2>
		<form>
			Email: <input type="text" size="10"> <br>
			Password: <input type="password" size="10">
			<input type="submit" value="Login">
		</form>
	</div>
	
	<!-- import footer jsp -->
	<jsp:directive.include file="footer.jsp"/>
	
</body>
</html>