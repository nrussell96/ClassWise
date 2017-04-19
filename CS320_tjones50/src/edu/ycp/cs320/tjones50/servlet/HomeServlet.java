package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.db.persist.FakeDatabase;
import edu.ycp.cs320.tjones50.controller.HomeController;
import edu.ycp.cs320.tjones50.model.Data;
import edu.ycp.cs320.tjones50.model.Department;
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
		
		//FakeDatabase database = new FakeDatabase();
		DerbyDatabase database = new DerbyDatabase();
		Home model = new Home();
		HomeController controller = new HomeController();
		controller.setModel(model);
		
		// add info to model
		model.setDepartments(database.getDeptList());
		
		// Pass model to jsp
		req.setAttribute("home", model);
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("In Home doPost");
		
		// initialize variables
		Home model = new Home();
		HomeController controller = new HomeController();
		controller.setModel(model);
		
		// get info from parameters
		String departmentName = req.getParameter("departmentName");
		
		
		// Pass model to jsp
		req.setAttribute("departmentName", departmentName);
				
		req.getRequestDispatcher("/_view/department.jsp").forward(req, resp);
		
	}
}
