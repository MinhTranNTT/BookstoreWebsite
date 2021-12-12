package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {

	@Test
	public void testEverageRating1() {
		Book book = new Book();
		
		Set<Review> reviews = new HashSet<Review>();
		
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(5.0, averageRating, 0.0);
	}
	
	@Test
	public void testEverageRating2() {
		Book book = new Book();
		
		float averageRating = book.getAverageRating();
		
		assertEquals(0.0, averageRating, 0.0);
	}
	
	@Test
	public void testEverageRating3() {
		Book book = new Book();
		
		Set<Review> reviews = new HashSet<Review>();
		
		Review review1 = new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		Review review2 = new Review();
		review2.setRating(4);
		reviews.add(review2);
		
		Review review3 = new Review();
		review3.setRating(3);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		float averageRating = book.getAverageRating();
		
		assertEquals(4.0, averageRating, 0.0);
	}
	
	@Test
	public void testRatingString1() {
		float everageRating = 0.0f;
		Book book = new Book();
		
		String actual = book.getRatingString(everageRating);
		String expected = "off,off,off,off,off";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString2() {
		float everageRating = 5.0f;
		Book book = new Book();
		
		String actual = book.getRatingString(everageRating);
		String expected = "on,on,on,on,on";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString3() {
		float everageRating = 4.5f;
		Book book = new Book();
		
		String actual = book.getRatingString(everageRating);
		String expected = "on,on,on,on,half";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString4() {
		float everageRating = 3.5f;
		Book book = new Book();
		
		String actual = book.getRatingString(everageRating);
		String expected = "on,on,on,half,off";
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRatingString5() {
		float everageRating = 3.7f;
		Book book = new Book();
		
		String actual = book.getRatingString(everageRating);
		String expected = "on,on,on,half,off";
		
		assertEquals(expected, actual);
	}


}
