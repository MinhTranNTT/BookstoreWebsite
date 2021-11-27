<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Evergreen Books - Welcome to BookStore</title>
</head>
<body>

	<!-- import header jsp -->
	<jsp:directive.include file="header.jsp"/>
	
	<!-- Main content jsp -->
	<div align="center">
		<br><br>
		
		<div align="center" style="width: 80%; margin: 0 auto;">
	          <h2>New Book:</h2>
	          
	          <c:forEach items="${listNewBooks}" var="book" >
	            <div style="display: inline-block; margin: 10px">
	               <div>
	                   <a href="view_book?id=${book.bookId}">
	                       <img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"/>
	                   </a>
	               </div>
	               <a href="view_book?id=${book.bookId}">
	                  <div><b>${book.title}</b></div>
	               </a>   
	          
	                  <div>rating *****</div>
	                  <div><i>by ${book.author}</i></div>
	                  <div><b>$ ${book.price}</b></div>
	             </div>
	          </c:forEach>
	     </div>
		
		<div align="center" style="clear: both">
			<h2>Best-Selling Book:</h2>
		</div>
		
		<div align="center" style="clear: both">
			<h2>Most-favored Book:</h2>
		</div>
		
		
		<br><br>
	</div>
	
	<!-- import footer jsp -->
	<jsp:directive.include file="footer.jsp"/>
	
</body>
</html>