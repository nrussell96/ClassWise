package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AdviceController;
import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;

public class GiveAdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("In Give Advice doGet");
		// create the course object
		Course course = new Course();
		CourseController courseController = new CourseController();
		courseController.setModel(course);
		
		//create the department object
		Department department = new Department();
		DepartmentController deptController = new DepartmentController();
		deptController.setModel(department);
		
		//get the courseName and departmentName from the previous servlet (course servlet)
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		
		//Stores the given department and course from the course, so it can be displayed on the give advice page
		course.setName(courseName);
		department.setName(departmentName);
		course.setDepartment(department);
		
		//sets the objects to strings so they can be used in jsp
		req.setAttribute("course", course);
		req.setAttribute("department", department);
		
		req.getRequestDispatcher("/_view/giveAdvice.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("In Give Advice doPost");
		//The code below may be used for making an error message on the give advice page
		
//		Advice model = new Advice();
//		Rating rating = new Rating();
//		AdviceController controller = new AdviceController();
//		controller.setModel(model);
//			
//			if (approved == null || difficulty == null || instruction == null || suppliesCost == null || enjoyment == null ||  gradeReceived == null || flags == null || semester == null || classYear == null || text == null) {
//				model.setErrorMessage("Please enter valid values for all fields");
//			} else {
//				rating.setDifficulty(difficulty);
//				rating.setEnjoyment(enjoyment);
//				rating.setInstruction(instruction);
//				rating.setSuppliesCost(suppliesCost);
//				model.setAdviceRating(rating);
//			}

			req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
			
	}	
}
