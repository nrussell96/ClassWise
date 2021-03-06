package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AdviceController;
import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.model.Course;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info
		System.out.println("in the course doGet");
		
		//pulled from Dr. Hake's Lab6 example on resources page
		String email = (String)req.getSession().getAttribute("email"); 
		System.out.println("   User: <" + email + "> logged in");
		String departmentName = (String)req.getSession().getAttribute("departmentName");
		System.out.println("   Department: <" + departmentName + ">");
		String courseName = (String)req.getSession().getAttribute("courseName");
		System.out.println("   Course: <" + courseName + ">");

		// initialize variables
		CourseController controller = new CourseController(courseName);
		Course course = controller.getCourse();

		
		// call controller methods
		controller.computeAveGrade();
		controller.computeAveRating();
	
		/*Clears cache to prevent user from going back
		 * to a previously logged in state after logging out--
		 * https://coderanch.com/t/351980/java/avoid-caching-JSP-pages
		 */
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Cache-Control","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader ("Expires", 0);
		
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
		//pulled from Dr. Hake's Lab6 example on resources page
		String departmentName = (String)req.getSession().getAttribute("departmentName");
		System.out.println("   Departement: <" + departmentName + ">");
		String courseName = (String)req.getSession().getAttribute("courseName"); 

		System.out.println("   Course: <" + courseName + ">");
		
		String flag = req.getParameter("flag");
		String sort = req.getParameter("sort");
		
		System.out.println("   Sort: <" + sort + ">");
		System.out.println("   Flag: <" + flag + ">");
		
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
		for (int i = 0;i<course.getArrAdvice().size();i++){
			System.out.println("   Advice[" + i + "]: <" + course.getArrAdvice().get(i).getUserActivated() + ">");
		}
		
		
		
		
		// Pass model to jsp
		req.setAttribute("course", course);
		req.setAttribute("department", course.getDepartment());
				
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
}
