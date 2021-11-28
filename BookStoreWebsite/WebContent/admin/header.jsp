 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div align="center">
	<a href="${pageContext.request.contextPath}/admin/">
		<img alt="loginadmin" src="../images/BookstoreAdminLogo.png">
	</a>
	<div>
		Welcome, <c:out value="${sessionScope.useremail}"></c:out> | <a href="logout">Logout</a> 
		<br>
	</div>
	<br>
	<div id="headermenu">
	
		<div>
			<a href="list_users">
				<img alt="userPic" src="../images/users.png"><br>Users
			</a>
		</div>
		
		<div>
			<a href="list_categories">
				<img alt="categoryPic" src="../images/category.png"><br>Categories
			</a>
		</div>
		
		<div>
			<a href="list_books">
				<img alt="bookPic" src="../images/bookstack.png"><br>Books
			</a>
		</div>
		
		<div>
			<a href="list_customers">
				<img alt="customerPic" src="../images/customer.png"><br>Customers
			</a>
		</div>
		
		<div>
			<a href="reviews">
				<img alt="reivewPic" src="../images/review.png"><br>Reviews
			</a>
		</div>
		
		<div>
			<a href="orders">
				<img alt="orderPic" src="../images/order.png"><br>Orders
			</a>
		</div>
		
	</div>
	
	
</div>