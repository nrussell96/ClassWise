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
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("in the course doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		
		System.out.println("   User: <" + email + "> logged in");
		
		// initialize variables
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);

		//FakeDatabase database = new FakeDatabase();
		DerbyDatabase database = new DerbyDatabase();
		
		// get info from parameters
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		
		// add info to model
		model.setName(courseName);
		Department department = new Department(departmentName);
		model.setDepartment(department);
		model.setArrAdvice(database.getCourseAdviceList(model));
		
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
		
		// Pass model to jsp
		req.setAttribute("course", model);
		req.setAttribute("department", department);
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("In Course Servlet doPost");
		
		// initialize variables
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);
		AdviceController adviceController = new AdviceController();

		//FakeDatabase database = new FakeDatabase();
		DerbyDatabase database = new DerbyDatabase();
		
		// get info from parameters
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		Integer adviceId = Integer.parseInt(req.getParameter("adviceId"));
		Integer flags = Integer.parseInt(req.getParameter("flags"));
		
		// add info to model
		model.setName(courseName);
		Department department = new Department(departmentName);
		model.setDepartment(department);
		model.setArrAdvice(database.getCourseAdviceList(model));
		
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
		
		// check flags
		for(Advice adv: model.getArrAdvice()){
			if(adviceId == adv.getAdviceId()){
				adv.setFlags(flags);
				adviceController.setModel(adv);
				adviceController.flagAdvice();
				if(adv.getFlags()==3){
					adv.setApproved(false);
				}
			}
		}
		
		// Pass model to jsp
		req.setAttribute("course", model);
		req.setAttribute("department", model.getDepartment());
				
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
}
