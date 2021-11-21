package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest {
	
	private static UserDAO  userDAO;

	@BeforeClass
	public static void setUpClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
	
	@Test
	public void testCreateUsers() {
		Users user = new Users();
		user.setEmail("Davi22d@gmail.com");
		user.setFullName("David Fake");
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
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 21;
		Users user = userDAO.get(userId);
		
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 23;
		userDAO.delete(userId);
		
		Users userOld = userDAO.get(userId);
		
		assertNull(userOld);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUser() {
		Integer userId = 99;
		userDAO.delete(userId);
		
	}
	
	@Test
	public void testListAllUsers() {
		List<Users> listUsers = userDAO.listAll();
		
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		
		assertTrue(listUsers.size() > 1);
	}
	
	@Test
	public void testCountAllUsers() {
		long countUsers = userDAO.count();
		
		assertTrue(countUsers > 0);
		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "ronaldo@gmail.com";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "nguyenminhchinh@yahoo.com";
		String password = "michinh2021";
		
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "nguyenminhchinh@yahoo.com";
		String password = "michinh2020";
		
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertFalse(loginResult);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
