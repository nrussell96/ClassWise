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
		
		System.out.println("In Home doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		System.out.println("   User: <" + email + "> logged in");
		
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
		System.out.println("In Home doPost");

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
