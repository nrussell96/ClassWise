package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.UserController;
import edu.ycp.cs320.tjones50.model.User;

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		UserController controller = new UserController(email);
		User user = controller.getUser();
		
		// Pass model to jsp
		req.setAttribute("user", user);
		
		/*Clears cache to prevent user from going back
		 * to a previously logged in state after logging out--
		 * https://coderanch.com/t/351980/java/avoid-caching-JSP-pages
		 */
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Cache-Control","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader ("Expires", 0);
		
		req.getRequestDispatcher("/_view/updateUserInfo.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//String email = req.getParameter("email");
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		String password = req.getParameter("pass");
		String reenter = req.getParameter("reenter");
		String major = req.getParameter("major");
		String GPAstring = req.getParameter("GPA");
		double GPA = Double.parseDouble(GPAstring);
		String year = req.getParameter("year");
		
		UserController controller = new UserController(email);
		User user = controller.getUser();
		
		// Pass model to jsp
		req.setAttribute("user", user);
		
		if(!password.equals(reenter) ){
			req.setAttribute("errorMessage", "Passwords don't match.");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/updateUserInfo.jsp").forward(req, resp);
		}
		else{
			
			controller.updateUserInfo(password, major, GPA, year);
			
			// Forward to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/userAccount");
		}
	}
}