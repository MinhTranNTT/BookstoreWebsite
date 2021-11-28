package com.bookstore.service;

import static com.bookstore.service.CommonUtility.forwardToPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
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
        customer.setPassword(password);
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
	
	
}