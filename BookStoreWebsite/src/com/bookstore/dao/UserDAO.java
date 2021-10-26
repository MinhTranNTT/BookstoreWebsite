package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Users create(Users user) {
		return super.create(user);
	}

	@Override
	public Users update(Users t) {
		return null;
	}

	@Override
	public Users get(Object id) {
		return null;
	}

	@Override
	public void delete(Object id) {
		
	}

	@Override
	public List<Users> listAll() {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}
	
	

}
