package com.bookstore.service;

import static com.bookstore.service.CommonUtility.forwardToPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;

public class CustomerServices extends CommonUtility {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;
	
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}

	public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
	}
	
	public void listCustomers(String message) throws ServletException, IOException {
		
		List<Customer> listCustomer = customerDAO.listAll();
		
		if(message != null) {
    		request.setAttribute("message", message);
    	}
		
		request.setAttribute("listCustomer", listCustomer);
		
		forwardToPage("customer_list.jsp", request, response);
	}

	public void createCustomer() throws ServletException, IOException {
		
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		
		if (existCustomer != null) {
			String message = "Could not create customer: the email "
		       + email + "is already registered";
			listCustomers(message);
		}
		else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			
			listCustomers("New customer has been created successfully");
		}
	}
	
	public void registerCustomer() throws ServletException, IOException { 
		System.out.println("RegisterCustomer Service");
		String email = request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		String message = "";
		
		if(existCustomer != null) {
			message = "Could not register customer: the email "
		       + email + " is already registered";
		}
		else {
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);			
			customerDAO.create(newCustomer);
			
			message = "You have registered successfully! Thank you "
					 + "<a href='frontend/login.jsp'>Click here to</a> login";
		
			}
		
		String messagePage = "frontend/message.jsp";
	
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
		
//		forwardToPage("frontend/message.jsp", message, request, response);
	}

	private void updateCustomerFieldsFromForm(Customer customer) {
		
		String email = request.getParameter("email");
    	String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
        String zipcode = request.getParameter("zipcode");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        
        if (email != null && !email.equals("")) {
        	customer.setEmail(email);
        }
        if (password != null && !password.equals("")) {
        	customer.setPassword(password);
        }
        
        customer.setFullname(fullName);
        //customer.setPassword(password);
        customer.setPhone(phone);
        customer.setCity(city);
        customer.setZipcode(zipcode);
        customer.setCountry(country);
        customer.setAddress(address);
	}

	public void editCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		
		request.setAttribute("customer", customer);	
		
		forwardToPage("customer_form.jsp", request, response);
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		
		Customer customerByEmail = customerDAO.findByEmail(email);
		String message = null;
		
		if (customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			message = "Could not update the customer ID " + customerId 
					+ "because there's an existing customer having the same email";
		} else {
			
			Customer customerById = customerDAO.get(customerId);
			updateCustomerFieldsFromForm(customerById);
			customerDAO.update(customerById);
			
			message = "The customer has been updated successfully";

		}
		listCustomers(message);
		
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		String message="";
		
		if (customer != null) {
			
			ReviewDAO reviewDAO = new ReviewDAO();
			long reviewCount = reviewDAO.countByCustomer(customerId);
			
			if (reviewCount > 0) {
				message = "Could not delete customer with ID " + customerId
						+ " because he/she posted reviews for books.";
				showMessageBackend(message, request, response);
			} else {
				
				customerDAO.delete(customerId);		
				
				message = "The customer has been deleted successfully.";
				//listCustomers(message);
			}
	
		} else {
			message = "Could not find customer with ID " + customerId + ", "
					+ "or it has been deleted by another admin";
			showMessageBackend(message, request, response);
		}
		listCustomers(message);
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if (customer == null) {
			
			String message = "Login failed! Please check your email and password";
		    request.setAttribute("message", message);
		    
		    showLogin();
		    
		} else {
			
			HttpSession session = request.getSession();
			session.setAttribute("loggedCustomer", customer);
			
			Object objRedirectURL = session.getAttribute("redirectURL");
			
			if(objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			}
			else {
				showCustomerProfile();
			}
		}
		
	}

	public void showCustomerProfile() throws ServletException, IOException {
		
//		String profilePage = "frontend/customer_profile.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
//		dispatcher.forward(request, response);
		
		forwardToPage("frontend/customer_profile.jsp", request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		forwardToPage("frontend/edit_profile.jsp", request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();
	}
	
	
}
