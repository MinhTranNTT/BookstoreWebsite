package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest {
	
	private static BookDAO bookDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDAO = new BookDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		
		Book newBook = new Book();
		Category category = new Category("Java");
		
		category.setCategoryId(12);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective Java (2nd Edition)");
	    newBook.setAuthor("Joshua Bloch");
	    newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
		Date publishDate = dateFormat.parse("25/05/2001");
		
		newBook.setPublishDate(publishDate);
		
		String imagePath = "E:\\PrivacyPersonal\\Jsp_Servlet_NamHaMinh\\s18 Code Book management\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testCreate2ndBook() throws ParseException, IOException {
		
		Book newBook = new Book();
		Category category = new Category("Java");
		
		category.setCategoryId(12);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective C# (2nd Edition)");
	    newBook.setAuthor("Joshua Bloch");
	    newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(48.87f);
		newBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
		Date publishDate = dateFormat.parse("25/05/2001");
		
		newBook.setPublishDate(publishDate);
		
		String imagePath = "E:\\PrivacyPersonal\\Jsp_Servlet_NamHaMinh\\s18 Code Book management\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testUpdateBook() throws IOException, ParseException {
		Book existBook = new Book();
		existBook.setBookId(42);
		
		Category category = new Category("Math Science");
		
		category.setCategoryId(14);
		existBook.setCategory(category);
		
		existBook.setTitle("Effective Java (1st Edition)");
	    existBook.setAuthor("Joshua Bloch");
	    existBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		existBook.setPrice(50.49f);
		existBook.setIsbn("0321356683");
		
		DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
		Date publishDate = dateFormat.parse("25/05/2001");
		
		existBook.setPublishDate(publishDate);
		
		String imagePath = "E:\\PrivacyPersonal\\Jsp_Servlet_NamHaMinh\\s18 Code Book management\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		
		Book updateBook = bookDAO.update(existBook);
		
		assertEquals(updateBook.getTitle(), "Effective Java (1st Edition)");
	}
	
	@Test (expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDAO.delete(bookId);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 38;
		bookDAO.delete(bookId);
		assertTrue(true);
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId = 100;
		Book book = bookDAO.get(bookId);
		
		assertNull(book);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 41;
		Book book = bookDAO.get(bookId);
		
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();
		
		for (Book aBook : listBooks) {
			System.out.println(aBook.getTitle() + " - " + aBook.getAuthor());
		}
		
		assertFalse(listBooks.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinkin in Java";
		Book book = bookDAO.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title = "Effective Java (1st Edition)";
		Book book = bookDAO.findByTitle(title);
		
		System.out.println(book.getAuthor() + " - " + book.getPrice());
		assertNotNull(book);
	}
	
	@Test
	public void testCountBooks() {
		long countBooks = bookDAO.count();
		assertEquals(2, countBooks);
	}
	
	@Test
	public void testSearchBookInTitle() {
		String keyword = "java";
		List<Book> listBooks = bookDAO.search(keyword);
		
		for (Book book : listBooks) {
			System.out.println(book.getTitle());
		}
		
		assertEquals(4, listBooks.size());
	}
	
	@Test
	public void testSearchBookInAuthor() {
		String keyword = "minh";
		List<Book> listBooks = bookDAO.search(keyword);
		
		for (Book book : listBooks) {
			System.out.println(book.getAuthor());
		}
		
		assertEquals(1, listBooks.size());
	}
	
	@Test
	public void testSearchBookInDescription() {
		String keyword = "annotations";
		List<Book> listBooks = bookDAO.search(keyword);
		
		for (Book book : listBooks) {
			System.out.println(book.getDescription());
		}
		
		assertEquals(1, listBooks.size());
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listBooks = bookDAO.listNewBooks();
		
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " - " + book.getPublishDate());
		}
		
		assertEquals(4, listBooks.size());
	}
	
	@Test
	public void testListByCategory() {
		int categoryId = 12;
		List<Book> listByCategory = bookDAO.listByCategory(categoryId);
		assertTrue(listByCategory.size() > 0);
	}
	

}
