package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.db.persist.FakeDatabase;
import edu.ycp.cs320.tjones50.controller.AdviceController;
import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.controller.UserController;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class GiveAdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("In Give Advice doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60 * 20); //20 minute session
		
		if(email == null || session == null){
			System.out.println("User: <" + email + "> not logged in, or session timed out.");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		System.out.println("   User: <" + email + "> logged in");
		
		//get the courseName and departmentName from the previous servlet (course servlet)
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Departement: <" + departmentName + ">");
		
		String courseName = (String)req.getSession().getAttribute("courseName"); //pulled from class example on session info

		System.out.println("   Course: <" + courseName + ">");
		
		// create the course object
		CourseController courseController = new CourseController();
		courseController.setCourseByName(courseName);
		Course course = courseController.getCourse();
				
		//create the department object
		DepartmentController controller = new DepartmentController();
		controller.setDepartmentByName(departmentName);
		Department department = controller.getDepartment();
		
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
		
		// Create the objects needed to get the information from the jsp
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Departement: <" + departmentName + ">");
		
		String courseName = (String)req.getSession().getAttribute("courseName"); //pulled from class example on session info

		System.out.println("   Course: <" + courseName + ">");
		Double instruction = Double.parseDouble(req.getParameter("instruction"));
		Double difficulty = Double.parseDouble(req.getParameter("difficulty"));
		Double suppliesCost = Double.parseDouble(req.getParameter("suppliesCost"));
		Double enjoyment = Double.parseDouble(req.getParameter("enjoyment"));
		Double gradeReceived = Double.parseDouble(req.getParameter("gradeReceived"));
		String semester = req.getParameter("semester");
		int classYear = Integer.parseInt(req.getParameter("classYear"));
		String professor = req.getParameter("professor");
		String text = req.getParameter("text");
				
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		// create the course object
		CourseController courseController = new CourseController();
		courseController.setCourseByName(courseName);
		UserController userController = new UserController();

		//Set user
		User user = userController.getUserByEmail(email);
		
		//add advice
		courseController.addAdviceAndRatingToCourse(user, semester, professor, gradeReceived, classYear, text, difficulty, instruction, suppliesCost, enjoyment);
		
		// Forward to view to render the result HTML document
		resp.sendRedirect(req.getContextPath() + "/course");

			
	}	
}
