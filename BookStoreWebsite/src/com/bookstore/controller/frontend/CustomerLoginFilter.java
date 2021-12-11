package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class CustomerLoginFilter implements Filter {

	private static final String[] LOGIN_REQUIRED_URLs = {"/view_profile", "/edit_profile", "/update_profile"};
    public CustomerLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
				
		if (path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		
		String requestURL = httpRequest.getRequestURL().toString();
		System.out.println("Request URL: " + requestURL);
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null;
		
		if (!loggedIn && isLoginRequired(requestURL)) {
			
			String loginPage = "frontend/login.jsp";
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		} else {
			
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
 	}
	
	private boolean isLoginRequired(String requestURL) {
		for (String loginRequiredURL : LOGIN_REQUIRED_URLs) {
			
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		
		return false;
	}

}
