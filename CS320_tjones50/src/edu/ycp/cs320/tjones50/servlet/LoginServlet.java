package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AdminController;
import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.controller.UserController;
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.Home;
import edu.ycp.cs320.tjones50.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the login doGet");
		String email = req.getParameter("email");
		//String url = (String)req.getSession().getAttribute("referer");
		
		//req.setAttribute("referer", url);
		req.setAttribute("email", email);
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the login doPost");
		//String url = req.getHeader("referer");
		//System.out.println("THE URL: " + url); //Personal test: checking the header URL
		
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		
		AdminController adminController = new AdminController(email);
		Admin admin = adminController.getAdmin();
		admin.setEmail(email);
		admin.setPassword(password);
		
		UserController userController = new UserController(email);
		User user = userController.getUser();
		user.setEmail(email);
		user.setPassword(password);
		
		boolean emailValid = userController.validate(email);
		boolean userAccountExists = userController.checkUserInfo(email, password);
		boolean adminAccountExists = adminController.checkAdminInfo(email, password);
		String from = req.getParameter("from");
		System.out.println("From: " + from); //visual representation of from value
	
		
		// Pass model to jsp
		req.setAttribute("login", user);
		if(emailValid == false){
			req.setAttribute("errorMessage", "Please enter a valid email pattern.");
			req.setAttribute("email", email);
			req.setAttribute("pass", password);
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		if(userAccountExists == true || adminAccountExists == true){ //if account exists
			// Pass model to jsp
			
			/*session code modeled after in-class example by Professor Hake
			 * Other reference includes tutorialspoint.com
			 * LINK: http://www.tutorialspoint.com/jsp/jsp_session_tracking.htm
			 * ^^^From the resources page
			 */
			req.setAttribute("email", req.getParameter("email")); 
			req.setAttribute("pass", req.getParameter("pass"));		
			
			// store email in session
			req.getSession().setAttribute("email", email);
			
			// initialize variables
			HomeController HomeController = new HomeController();
			Home home = HomeController.getHome();
			
			// Pass model to jsp
			req.setAttribute("home", home);
			
			// Forward to view to render the result HTML document
			//resp.sendRedirect(url);
			if(from == null || from == ""){
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			//obtain redirect path from the 'from' parameter on login.jsp
			resp.sendRedirect(req.getParameter("from"));
			
		}
		else{
			req.setAttribute("errorMessage", "Email and/or password invalid.");
			req.setAttribute("email", email);
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
}