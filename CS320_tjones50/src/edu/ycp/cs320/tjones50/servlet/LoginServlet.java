package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		
		model.setEmail(email);
		model.setPassword(password);
		
		boolean accountExists = controller.checkAccountInfo(email, password);
		
		// Pass model to jsp
		req.setAttribute("login", model);
		
		if(accountExists == true){ //if account exists
			// Pass model to jsp
/*			HttpSession session = req.getSession(); //Part of my attempt at making a session that persists
			session.setAttribute("login", model); */
					
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}else{
			req.setAttribute("errorMessage", "Email and/or password invalid.");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
}