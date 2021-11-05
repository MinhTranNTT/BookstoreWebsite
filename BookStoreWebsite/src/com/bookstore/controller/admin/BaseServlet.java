package com.bookstore.controller.admin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	

	public void init() throws ServletException {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public void destroy() {
		entityManager.close();
		entityManagerFactory.close();
	}

}