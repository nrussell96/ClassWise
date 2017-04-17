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
import edu.ycp.cs320.tjones50.model.User;

public class GiveAdviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("In Give Advice doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		if(email == null){
			System.out.println("User: <" + email + "> not logged in, or session timed out.");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
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
		
		// Create the objects needed to get the information from the jsp
				String courseName = req.getParameter("courseName");
				String departmentName = req.getParameter("departmentName");
				Double instruction = Double.parseDouble(req.getParameter("instruction"));
				Double difficulty = Double.parseDouble(req.getParameter("difficulty"));
				Double suppliesCost = Double.parseDouble(req.getParameter("suppliesCost"));
				Double enjoyment = Double.parseDouble(req.getParameter("enjoyment"));
				Double gradeReceived = Double.parseDouble(req.getParameter("gradeReceived"));
				String semester = req.getParameter("semester");
				int classYear = Integer.parseInt(req.getParameter("classYear"));
				String professor = req.getParameter("professor");
				String text = req.getParameter("text");

				//Set user
				User user  = new User();
				user.setUserClassYear("Junior");
				user.setMajor("Computer Science");
				user.setGPA(3.90);
				
				//create object where new advice will be stored
				Advice adviceModel = new Advice();
				AdviceController adviceController = new AdviceController();
				adviceController.setModel(adviceModel);
				
				// create course model and controller
				Course model = new Course();
				CourseController controller = new CourseController();
				controller.setModel(model);
				
				//set the name and department of course model
				model.setName(courseName);
				Department department = new Department(departmentName);
				model.setDepartment(department);
				
				//hard coded advice and ratings
				model.setAveGrade(3.21);
				Rating aveRating = new Rating(7,4,0,8);
				model.setAveRatings(aveRating);
				
				// Use data from user's advice from give advice page and create a new piece of advice, then add it to the advice list
				adviceModel.setText(text);
				Rating adviceModelRating = new Rating(difficulty, instruction, suppliesCost, enjoyment);
				adviceModel.setAdviceRating(adviceModelRating);
				adviceModel.setClassYear(classYear);
				adviceModel.setGradeReceived(gradeReceived);
				adviceModel.setSemester(semester);
				adviceModel.setUserGPA(user.getGPA());
				adviceModel.setUserMajor(user.getMajor());
				adviceModel.setUserClassYear(user.getUserClassYear());
				adviceModel.setProfessor(professor);
				adviceModel.setApproved(true);
				model.addAdvice(adviceModel);
				
				// Other hard coded advice
				Rating adviceRating = new Rating(7,4,0,8);
				Advice advice = new Advice(adviceRating, "Sophmore", "Computer Engineering", 3.91, 4.0, "Spring", 2017, "Hake");
				advice.setText("This class is the best and I am going to talk a lot so that I can see how it goes to display so much text I am running out of things to say so I'm just going to talk about how it snowed this week and we got a snow day and it was great but I just did homework during the day because I am an engineer and that's all I do I wonder if this is enough text I hope it is I am running out of stuff to say so I guess I am going to stop");
				advice.setApproved(true);
				model.addAdvice(advice);
				
				Rating adviceRating2 = new Rating(1,5,4,2);
				Advice advice2 = new Advice(adviceRating2, "Senior", "Computer Science", 3.53, 3.5, "Fall", 2013, "Hake");
				advice2.setText("This class was the worst");
				advice2.setApproved(true);
				model.addAdvice(advice2);
				
				Rating adviceRating3 = new Rating(1,1,1,1);
				Advice advice3 = new Advice(adviceRating3, "Junior", "Electrical Engineering", 3.22, 3.0, "Fall", 2018, "Hake");
				advice3.setText("This class was okay");
				advice3.setApproved(true);
				model.addAdvice(advice3);	
				
				// Pass model to jsp
				req.setAttribute("course", model);
				req.setAttribute("department", department);
		
				// Forward to view to render the result HTML document
				req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
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

			
	}	
}
