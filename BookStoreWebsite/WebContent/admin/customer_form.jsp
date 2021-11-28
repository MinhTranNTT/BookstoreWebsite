<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create New Customer</title>
	<link rel="stylesheet" href="../css/style.css" >
	<link rel="stylesheet" href="../css/jquery-ui.min.css" >
	
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<!-- import header jsp admin -->
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer != null}">
				Edit Customer
			</c:if>
			
			<c:if test="${customer == null}">
				Create New Customer
			</c:if>
		</h2>
	</div>

	<div align="center">
	
		<c:if test="${customer != null}">
			<form action="update_customer" method="post" id="customerForm"  >
			<input type="hidden" name="customerId" value="${customer.customerId}">
		</c:if>
		
		<c:if test="${book == null}">
			<form action="create_customer" method="post" id="customerForm"  >
		</c:if>
		
		<table class="form">
		
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" id="email" name="email" size="45" value="${customer.email}" /></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullName" name="fullName" size="45" value="${customer.fullname}" /></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password" name="password" size="45" value="${customer.password}" /></td>
			</tr>
			<tr>
				<td align="right">Confirm password:</td>
				<td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="45" value="${customer.password}"/>
			</tr>	
			<tr>
				<td align="right">Phone No.:</td>
				<td align="left"><input type="text" id="phone" name="phone" size="20" value="${customer.phone}" /></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input type="text" id="city" name="city" size="45" value="${customer.city}" /></td>
			</tr>	
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input type="text" id="zipcode" name="zipcode" size="10" value="${customer.zipcode}" /></td>
			</tr>	
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input type="text" id="country" name="country" size="45" value="${customer.country}" /></td>
			</tr>	
			<tr>
				<td align="right">Address:</td>
				<td align="left">
					<textarea rows="5" cols="50" name="address" id="address">${customer.address}</textarea>
				</td>
			</tr>
			
			
			<tr>
					<td>&nbsp;</td>
			</tr>
	
			<tr>
					<td colspan="2" align="center">
						<button type="submit" >Save</button> &nbsp;&nbsp;&nbsp;
						<button	id="buttonCancel">Cancel</button>
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
				password: "required",
				confirmPassword: {
					required: true,
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
				    email: "Please enter a vlid email address"
				}, 
				
			    fullName: "Please enter full name",
				password: "Please enter password",
				confirmPassword: {
					required: "Please confirm password",
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

	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
	};
	
</script>

</body>
</html>