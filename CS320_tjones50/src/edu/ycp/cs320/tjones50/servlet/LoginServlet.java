package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.controller.AccountController;
import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.Home;
import edu.ycp.cs320.tjones50.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the login doGet");
		String email = req.getParameter("email");
		
		req.setAttribute("email", email);
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the login doPost");
		
		User model = new User();
		AccountController controller = new AccountController();
		controller.setModel(model);
	
		
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		
		model.setEmail(email);
		model.setPassword(password);
		
		boolean emailValid = controller.validate(email);
		boolean accountExists = controller.checkAccountInfo(email, password);
		
		// Pass model to jsp
		req.setAttribute("login", model);
		if(emailValid == false){
			req.setAttribute("errorMessage", "Please enter a valid email pattern.");
			req.setAttribute("email", email);
			req.setAttribute("pass", password);
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		if(accountExists == true){ //if account exists
			// Pass model to jsp
			
			req.setAttribute("email", req.getParameter("email")); //session code modeled after in-class example by Professor Hake
			req.setAttribute("pass", req.getParameter("pass"));
			
			// store email obj in session
			req.getSession().setAttribute("email", email);
			
			//FakeDatabase database = new FakeDatabase();
			DerbyDatabase database = new DerbyDatabase();
			Home homeModel = new Home();
			HomeController homeController = new HomeController();
			homeController.setModel(homeModel);
			
			// add info to model
			homeModel.setDepartments(database.getDeptList());
			
			// Pass model to jsp
			req.setAttribute("home", homeModel);
			
			// Forward to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/home");
			
		}else{
			req.setAttribute("errorMessage", "Email and/or password invalid.");
			req.setAttribute("email", email);
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
}