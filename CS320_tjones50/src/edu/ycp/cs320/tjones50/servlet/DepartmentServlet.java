package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;

public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Department model = new Department();
		DepartmentController controller = new DepartmentController();
		controller.setModel(model);
		
		String departmentName = req.getParameter("departmentName");
		model.setName(departmentName);
		Course course1 = new Course();
		course1.setName("101");
		model.addCourse(course1);
		Course course2 = new Course();
		course2.setName("201");
		model.addCourse(course2);
		Course course3 = new Course();
		course3.setName("320");
		model.addCourse(course3);
		
		
		// Pass model to jsp
		req.setAttribute("game", model);
		
		req.getRequestDispatcher("/_view/department.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Department model = new Department();
		DepartmentController controller = new DepartmentController();
		controller.setModel(model);
		
		// Reconstruct current GuessingGame model object
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
				
				
		// Pass model to jsp
		req.setAttribute("courseName", courseName);
		req.setAttribute("departmentName", departmentName);
		
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}

}
