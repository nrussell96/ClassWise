package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);
		
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		
		model.setName(courseName);
		Department department = new Department(departmentName);
		model.setDepartment(department);
		
		model.setAveGrade(3.21);
		Rating aveRating = new Rating(7,4,0,8);
		model.setAveRatings(aveRating);
		
		// Pass model to jsp
		req.setAttribute("game", model);
		
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);
		
		// Pass model to jsp
		req.setAttribute("game", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}

}
