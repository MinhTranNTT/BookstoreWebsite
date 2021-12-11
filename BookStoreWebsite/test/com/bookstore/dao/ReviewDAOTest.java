package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	public static ReviewDAO reviewDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDAO = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		
		Book book = new Book();
		book.setBookId(45);
		
		Customer customer = new Customer();
		customer.setCustomerId(23);
		
		review.setBook(book);
		review.setCustomer(customer);
		
		review.setHeadline("This is a very good Book!");
		review.setRating(5);
		review.setComment("I have just read this book, very good.");
		
		Review savedReview = reviewDAO.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testGet() {
		Integer reviewId = 17;
		Review review = reviewDAO.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId = 17;
		Review review = reviewDAO.get(reviewId);
		
		review.setHeadline("Excellent Book.");
		
		Review updatedReview = reviewDAO.update(review);
		
		assertEquals(review.getHeadline(), updatedReview.getHeadline());
	}

	@Test
	public void testDeleteObject() {
		Integer reviewId = 17;
		reviewDAO.delete(reviewId);
		
		Review review = reviewDAO.get(reviewId);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReviews = reviewDAO.listAll();
		
		for (Review review : listReviews) {
			System.out.println(review.getReviewId() + "-" + review.getBook().getTitle() + "-" 
								+ review.getCustomer().getFullname() + "-"
								+ review.getHeadline() + "-" + review.getRating());
		}
		
		assertTrue(listReviews.size() > 0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDAO.count();
		
		System.out.println("Total Review = " + totalReviews);
		assertTrue(totalReviews > 0);
	}

}
