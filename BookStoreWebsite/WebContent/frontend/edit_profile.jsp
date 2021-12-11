<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Profile</title>
	<link rel="stylesheet" href="css/style.css" >
	<link rel="stylesheet" href="../css/jquery-ui.min.css" >
	
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<!-- import header jsp admin -->
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
			Edit My Profile
		</h2>
	</div>

	<div align="center">
	
		<form action="update_profile" method="post" id="customerForm">
		
		<table class="form">
		
			<tr>
				<td align="right">Email:</td>
				<td align="left"><b>${loggedCustomer.email}</b> (Cannot be changed)</td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullName" name="fullName" size="45" value="${loggedCustomer.fullname}"/></td>
			</tr>
			
			<tr>
				<td align="right">Phone No.:</td>
				<td align="left"><input type="text" id="phone" name="phone" size="20" value="${loggedCustomer.phone}" /></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" id="city" name="city" size="45" value="${loggedCustomer.city}" /></td>
			</tr>	
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" id="zipcode" name="zipcode" size="10" value="${loggedCustomer.zipcode}" /></td>
			</tr>	
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" id="country" name="country" size="45" value="${loggedCustomer.country}" /></td>
			</tr>	
			<tr>
				<td align="right">Address:</td>
				<td align="left">
					<textarea rows="5" cols="50" name="address" id="address">${loggedCustomer.address}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan ="2" align="center"><i>(leave password fields blank if you don't want to change password)</i></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password" name="password" size="45" /></td>
			</tr>
			<tr>
				<td align="right">Confirm password:</td>
				<td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="45" />
			</tr>	
			
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
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
	
	$(document).ready(function() {
		
		$("#customerForm").validate({
			rules: {
				email: { 
					required: true,
				    email: true
				},
				fullName: "required",
				
				confirmPassword: {
					equalTo: "#password"
				},
				phone: "required",
				city: "required",
				zipCode: "required",
			    country: "required",
			    address: "required"
			},
			
			messages: {
				email: {
					required: "Please select a category for the book",
				    email: "Please enter a valid email address"
				}, 
				
			    fullName: "Please enter full name",
				
				confirmPassword: {

					equalTo: "passwords do not match"
				},
				phone: "Please enter phone number",
				city: "Please enter city",
				zipCode: "Please enter zipcode",
				country: "Please enter country",
				address: "Please enter address"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});
</script>

</body>
</html>