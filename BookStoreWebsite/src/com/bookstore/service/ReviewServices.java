package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Review;

public class ReviewServices extends CommonUtility {
	
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
	}
	
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	
	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		
		request.setAttribute("listReviews", listReviews);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		
//		String listPage = "review_list.jsp";
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
//		requestDispatcher.forward(request, response);
		
		forwardToPage("review_list.jsp", request, response);
	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		if (review != null) {
			
			request.setAttribute("review", review);
			forwardToPage("review_form.jsp", request, response);
			
		} else {
			
			String message = "Could not find review with ID " + reviewId;
			showMessageBackend(message, request, response);
		}
		
		
//		String listPage = "review_form.jsp";
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
//		requestDispatcher.forward(request, response);
			
	}

	public void updateReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = reviewDAO.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);
		
		String message = "The review has been updated successfully.";
		listAllReview(message);
		
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		if (review != null) {
			
			reviewDAO.delete(reviewId);
			String message = "The review has been deleted successfully.";
			listAllReview(message);
			
		} else {
			
			String message = "Could you find review with ID " + reviewId
					+ ", or it might have been deleted by another admin";
			showMessageBackend(message, request, response);
		}	
	}
}
