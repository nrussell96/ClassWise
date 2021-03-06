package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.UserController;
import edu.ycp.cs320.tjones50.model.User;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the CreateAccount doGet");
		String email = req.getParameter("email");
		
		req.setAttribute("email", email);
		
		/*Clears cache to prevent user from going back
		 * to a previously logged in state after logging out--
		 * https://coderanch.com/t/351980/java/avoid-caching-JSP-pages
		 */
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Cache-Control","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader ("Expires", 0);
		
		req.getRequestDispatcher("/_view/createaccount.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the CreateAccount doPost");
		
		String email = req.getParameter("email");
		String password = req.getParameter("pass");
		String reenter = req.getParameter("reenter");
		String major = req.getParameter("major");
		String GPAstring = req.getParameter("GPA");
		double GPA = Double.parseDouble(GPAstring);
		String year = req.getParameter("year");
		
		UserController controller = new UserController(email);
		User user = controller.getUser();
		
		user.setEmail(email);
		user.setPassword(password);
		user.setMajor(major);
		user.setGPA(GPA);
		user.setUserClassYear(year);
		
		boolean emailValid = controller.validate(email);
		boolean accountExists = controller.checkUserInfo(email, password);
		
		// Pass model to jsp
		req.setAttribute("createaccount", user);
		
		if(emailValid == false){
			req.setAttribute("errorMessage", "Please enter a valid email pattern.");
			req.getRequestDispatcher("/_view/createaccount.jsp").forward(req, resp);
		}
		else if(!password.equals(reenter) ){
			req.setAttribute("errorMessage", "Passwords don't match.");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/createaccount.jsp").forward(req, resp);
		}
		else if(accountExists == true){ //if account exists
			req.setAttribute("errorMessage", "Account already exists");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/createaccount.jsp").forward(req, resp);
		} else{
			
			controller.createUser(major, GPA, year, email, password);
							
			// Forward to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}