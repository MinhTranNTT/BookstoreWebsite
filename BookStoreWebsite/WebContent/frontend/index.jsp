<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Evergreen Books - Welcome to BookStore</title>
	<link rel="stylesheet" href="css/style.css" >
</head>
<body>

	<!-- import header jsp -->
	<jsp:directive.include file="header.jsp"/>
	
	<!-- Main content jsp -->
	<div align="center">
	
		<div align="center" style="width: 80%; margin: 0 auto;">
	          <h2 class="pageheading">New Book:</h2>
	          
	          <c:forEach items="${listNewBooks}" var="book" >
	            <div class="book">
	               <div>
	                   <a href="view_book?id=${book.bookId}">
	                       <img src="data:image/jpg;base64,${book.base64Image}" width="128" height="164"/>
	                   </a>
	               </div>
	               <a href="view_book?id=${book.bookId}">
	                  <div><b>${book.title}</b></div>
	               </a>   
	          
	                  <div>
	                  		<!-- import book_rating jsp -->
							<jsp:directive.include file="book_rating.jsp"/>
	                  </div>
	                  <div><i>by ${book.author}</i></div>
	                  <div><b>$ ${book.price}</b></div>
	             </div>
	          </c:forEach>
	     </div>
		
		<div class="next-row">
			<h2>Best-Selling Book:</h2>
		</div>
		
		<div class="next-row">
			<h2>Most-favored Book:</h2>
		</div>
		
		
		<br><br>
	</div>
	
	<!-- import footer jsp -->
	<jsp:directive.include file="footer.jsp"/>
	
</body>
</html>