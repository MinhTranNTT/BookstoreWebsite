package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;
import static com.bookstore.service.CommonUtility.*;

public class CategoryServices {
	
	private CategoryDAO cateDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		cateDAO = new CategoryDAO();
		
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = cateDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
//		String listPage = "category_list.jsp";
//		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
//		dispatcher.forward(request, response);
		
		forwardToPage("category_list.jsp", request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String nameCate = request.getParameter("name");
		Category existCategory = cateDAO.findByName(nameCate);
		
		if (existCategory != null) {
			
			String message = "Cound not create Category. A Category with name " + nameCate + " already exists.";
//			request.setAttribute("message", message);
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
//			requestDispatcher.forward(request, response);
			
			showMessageBackend(message, request, response);
		} else {
			
			Category category = new Category(nameCate);
			cateDAO.create(category);
			String message = "New Category created successfully.";
			listCategory(message);
		}
	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = cateDAO.get(categoryId);
		
		String destPage = "category_form.jsp";
		
		if(category == null) {
			destPage = "message.jsp"; 
			String message = "Could not edit category with " + categoryId;
			//request.setAttribute("message", message);
			
			showMessageBackend(message, request, response);
		} else {
			
			request.setAttribute("category", category);	
			forwardToPage("category_form.jsp", request, response);
		}
		
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
//		requestDispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int cateId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		
		Category categoryById = cateDAO.get(cateId);
		Category categoryByName = cateDAO.findByName(categoryName);
		
		if (categoryByName != null && categoryByName.getCategoryId() != categoryById.getCategoryId()) {
			
			String message = "Cound not update. A category with name '" + categoryName + "' already exist";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		} else {
			
			categoryById.setName(categoryName);
			cateDAO.update(categoryById);
			String message = "Category has been updated succesfully";
			
			listCategory(message);
		}
		
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO = new BookDAO();
		long numberCategory = bookDAO.countByCategory(categoryId);
		
		String message = "";
		if (numberCategory > 0) {
			message = "Could not delete the Category (ID: %d) because it currently contains some book.";
			message = String.format(message, numberCategory);
		} else {
			cateDAO.delete(categoryId);
			message = "The Category with ID " + categoryId + " has been removed successfully.";
		}
		
		
		
		listCategory(message);
	}
	
	
}
