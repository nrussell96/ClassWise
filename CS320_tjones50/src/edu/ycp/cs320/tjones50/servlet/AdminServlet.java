package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.tjones50.controller.AdminController;
import edu.ycp.cs320.tjones50.model.Admin;


public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the admin doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60 * 20); //20 minute session
		
		if(email == null || session == null){
			System.out.println("User: <" + email + "> not logged in, or session timed out.");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		if(!email.equals("admin@ycp.edu")){ //if user 
			// Forward to view to render the result HTML document
			resp.sendRedirect(req.getContextPath() + "/userAccount");
		}
				
		AdminController controller = new AdminController(email);
		Admin admin = controller.getAdmin();
		
		// Pass model to jsp
		req.setAttribute("admin", admin);
			

		req.getRequestDispatcher("/_view/adminAccount.jsp").forward(req, resp);
//		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the admin doPost");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		String action = req.getParameter("action");
		int adviceId = Integer.parseInt(req.getParameter("adviceId"));
		
		AdminController controller = new AdminController(email);
		
		
		if(action.equals("approve")){
			controller.approveAdvice(adviceId);
		}
		else if(action.equals("deactivate")){
			controller.deactiveatUser(adviceId);
		}
		else if(action.equals("activate")){
			controller.activeatUser(adviceId);
		}
		
		Admin admin = controller.getAdmin();
		
		// Pass model to jsp
		req.setAttribute("admin", admin);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/adminAccount.jsp").forward(req, resp);
	}
}