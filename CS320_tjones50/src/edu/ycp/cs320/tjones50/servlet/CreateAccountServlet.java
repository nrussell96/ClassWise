package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AccountController;
import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.User;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/_view/createaccount.jsp").forward(req, resp);
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
		req.setAttribute("createaccount", model);
		
		if(accountExists == true){ //if account exists
			req.setAttribute("errorMessage", "That account already exists.");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/createaccount.jsp").forward(req, resp);
		}else{
			// Pass model to jsp
			req.setAttribute("createaccount", model);
								
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
	}
}