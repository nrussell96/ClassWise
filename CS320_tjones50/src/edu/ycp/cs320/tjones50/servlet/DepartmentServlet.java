package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.db.persist.FakeDatabase;
import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Data;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Home;

public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In the department doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info

		System.out.println("   User: <" + email + "> logged in");
		
		// initialize variables

		//FakeDatabase database = new FakeDatabase();
		DerbyDatabase database = new DerbyDatabase();
		Department model = new Department();
		DepartmentController controller = new DepartmentController();
		controller.setModel(model);
		
		// get info from parameters
		String departmentName = req.getParameter("departmentName");
		
		// add info to model
		model.setName(departmentName);
		model.setCourses(database.getDepartmentByName(departmentName).getCourses());
		
		// Pass model to jsp
		req.setAttribute("department", model);
		
		req.getRequestDispatcher("/_view/department.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In the department doPost");
		
		// initialize variables
		Department model = new Department();
		DepartmentController controller = new DepartmentController();
		controller.setModel(model);
		
		// get info from parameters
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
				
				
		// Pass model to jsp
		req.setAttribute("courseName", courseName);
		req.setAttribute("departmentName", departmentName);
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}

}
