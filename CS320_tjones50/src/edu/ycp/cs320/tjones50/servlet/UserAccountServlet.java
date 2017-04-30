package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.controller.UserController;
import edu.ycp.cs320.tjones50.model.Home;
import edu.ycp.cs320.tjones50.model.User;

public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the userAccount doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60 * 20); //20 minute session
		
		if(email == null || session == null){
			System.out.println("User: <" + email + "> not logged in, or session timed out.");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		if(email.equals("admin@ycp.edu")){ //if admin 
			// Forward to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/admin");
		}
		else{
			//initialize objects
			UserController controller = new UserController(email);
			User user = controller.getUser();
			
			// Pass model to jsp
			req.setAttribute("user", user);
			req.getRequestDispatcher("/_view/userAccount.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the userAccount doPost");
		// Forward to view to render the result HTML document
		
		/*Clears cache to prevent user from going back
		 * to a previously logged in state after logging out--
		 * https://coderanch.com/t/351980/java/avoid-caching-JSP-pages
		 */
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
}
