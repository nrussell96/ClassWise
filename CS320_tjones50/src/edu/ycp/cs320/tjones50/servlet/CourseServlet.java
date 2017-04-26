package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AdviceController;
import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("in the course doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		System.out.println("   User: <" + email + "> logged in");
		
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Departement: <" + departmentName + ">");
		
		String courseName = (String)req.getSession().getAttribute("courseName"); //pulled from class example on session info

		System.out.println("   Course: <" + courseName + ">");

		// initialize variables
		CourseController controller = new CourseController(courseName);
		Course course = controller.getCourse();

		
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
	
		// Pass model to jsp
		req.setAttribute("course", course);
		req.setAttribute("department", course.getDepartment());
		req.setAttribute("email", email);
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("In Course Servlet doPost");
				
		// get info from parameters
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Departement: <" + departmentName + ">");
		
		String courseName = (String)req.getSession().getAttribute("courseName"); //pulled from class example on session info

		System.out.println("   Course: <" + courseName + ">");
		
		String flag = req.getParameter("flag");
		String sort = req.getParameter("sort");
				
		// initialize variables
		CourseController controller = new CourseController();
		controller.setCourseByName(courseName);
		
		// set flags
		if(flag.equals("true")){
			Integer adviceId = Integer.parseInt(req.getParameter("adviceId"));
			AdviceController adviceController = new AdviceController(adviceId);
			adviceController.flagAdvice();
		}

		
		// sort advice
		controller.SortAdvice(sort);
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
		// get course
		Course course = controller.getCourse();
		
		
		
		
		// Pass model to jsp
		req.setAttribute("course", course);
		req.setAttribute("department", course.getDepartment());
				
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
}
