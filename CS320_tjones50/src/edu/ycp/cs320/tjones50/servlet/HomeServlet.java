package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.model.Home;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In Home doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		System.out.println("   User: <" + email + "> logged in");
		
		// initialize variables
		HomeController controller = new HomeController();
		Home home = controller.getHome();
		
		// Pass model to jsp
		req.setAttribute("home", home);
		req.setAttribute("email", email);
		
		/*Clears cache to prevent user from going back
		 * to a previously logged in state after logging out--
		 * https://coderanch.com/t/351980/java/avoid-caching-JSP-pages
		 */
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Cache-Control","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader ("Expires", 0);
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("In Home doPost");
		
		// get info from parameters
		String departmentName = req.getParameter("departmentName");
		//String email = (String)req.getSession().getAttribute("email");
		
		// store departmentName obj in session
		req.getSession().setAttribute("departmentName", departmentName);

		// Forward to view to render the result HTML document
		resp.sendRedirect(req.getContextPath() + "/department");
		
	}
}
