package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AccountController;
import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User model = new User();
		AccountController controller = new AccountController();
		controller.setModel(model);
		
		String email = req.getParameter("abc.gmail.com");
		String password = req.getParameter("1234");
		
		model.setEmail(email);
		model.setPassword(password);
		
		// Pass model to jsp
		req.setAttribute("login", model);
				
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
}