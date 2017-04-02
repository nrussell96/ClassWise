package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.model.Data;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Home;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Data data = new Data();
		data.populate();

		Home model = new Home();
		HomeController controller = new HomeController();
		controller.setModel(model);
		model.setDepartments(data.getDepts());
		
		// Pass model to jsp
		req.setAttribute("home", model);
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*		HttpSession session=req.getSession(false); //Part of my attempt at making a session that persists - Nate 
		if(session != null){
			String email = (String)session.getAttribute("email");
			req.setAttribute("sessionMessage", "Hello " + email); 
		}
		else{  
			req.setAttribute("errorMessage", "Please login first");  
            req.getRequestDispatcher("/_view/login.jsp").include(req, resp);  
        } */
		Home model = new Home();
		HomeController controller = new HomeController();
		controller.setModel(model);
		
		// Reconstruct current GuessingGame model object
		String departmentName = req.getParameter("departmentName");
		
		
		// Pass model to jsp
		req.setAttribute("departmentName", departmentName);
				
		req.getRequestDispatcher("/_view/department.jsp").forward(req, resp);
		
	}
}
