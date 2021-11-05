package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Category create(Category category) {
		return super.create(category);
	}
	
	@Override
	public Category update(Category category) {
		return super.update(category);
	}
	
	public Category findByName(String nameCategory) {
		List<Category> listCate = super.findWithNamedQuery("Category.findByName", "name", nameCategory);
		
		if(listCate != null && listCate.size() > 0) {
			return listCate.get(0);
		}
		
		return null;
	}
	
}
