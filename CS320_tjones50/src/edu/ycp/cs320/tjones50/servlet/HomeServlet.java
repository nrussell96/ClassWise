package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Home;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Home model = new Home();
		HomeController controller = new HomeController();
		controller.setModel(model);
		
		Department department1 = new Department();
		department1.setName("Computer Science");
		model.addDepartment(department1);
		Department department2 = new Department();
		department2.setName("Computer Engineering");
		model.addDepartment(department2);
		Department department3 = new Department();
		department3.setName("Electrical Engineering");
		model.addDepartment(department3);
		
		
		// Pass model to jsp
		req.setAttribute("game", model);
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
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
