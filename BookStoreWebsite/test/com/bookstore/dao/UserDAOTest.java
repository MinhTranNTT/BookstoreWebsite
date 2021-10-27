package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO  userDAO;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		userDAO = new UserDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownClass() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void testCreateUsers() {
		Users user = new Users();
		user.setEmail("David@gmail.com");
		user.setFullName("David Tran");
		user.setPassword("12345");
	
		user = userDAO.create(user);
			
		assertTrue(user.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user = new Users();
		user = userDAO.create(user);
		
		assertTrue(user.getUserId() > 0);
	}

	@Test
	public void testUpdateUser() {
		Users user = new Users();
		user.setUserId(21);
		user.setEmail("minh@gmail.net");
		user.setFullName("MinhTran");
		user.setPassword("myadmin");
		
		user = userDAO.update(user);
		String expected = "myadmin";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);
		
	}
}