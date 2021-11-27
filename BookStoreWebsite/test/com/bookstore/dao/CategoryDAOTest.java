package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDAO = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCate = new Category("Python");
		Category category = categoryDAO.create(newCate);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}
	
	@Test
	public void testUpdateCategory() {
		Category cate = new Category("Java Advanced");
		cate.setCategoryId(11);
		
		Category cateUpdate = categoryDAO.update(cate);
		
		assertEquals(cate.getName(), cateUpdate.getName());
	}
	
	@Test
	public void testGetCategory() {
		Integer id = 11;
		Category cate = categoryDAO.get(id);
		
		assertNotNull(cate);
	}
	
	@Test
	public void testDeleteCategory() {
		Integer id = 13;
		categoryDAO.delete(id);
		
		Category cate = categoryDAO.get(id);
		
		assertNull(cate);
	}
	
	@Test
	public void testListAll() {
		List<Category> listCate = categoryDAO.listAll();
		
		listCate.forEach(c -> System.out.println(c.getName()));
		
		assertTrue(listCate.size() > 0);
	}
	
	@Test
	public void testCountCategory() {
		long countCategory = categoryDAO.countWithNamedQuery("Category.countAll");
		System.out.println(countCategory);
		assertTrue(countCategory != 0);
	}
	
	@Test
	public void testFindByName() {
		String nameCategory = "Java Advanced";
		Category cate = categoryDAO.findByName(nameCategory);
		
		assertNotNull(cate);
	}
	
	@Test
	public void testFindByNameNotExist() {
		String name = "Hibernate";
		Category category = categoryDAO.findByName(name);
		
		assertNull(category);
	}
	

}
