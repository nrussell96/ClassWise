package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("in the course doGet");
		Course model = new Course();
		CourseController controller = new CourseController();
		controller.setModel(model);
		
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		
		model.setName(courseName);
		Department department = new Department(departmentName);
		model.setDepartment(department);
		
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
		
		controller.computeAveGrade();
		controller.computeAveRating();
		
		HttpSession session=req.getSession(false);
		if(session != null){
			String email = (String)session.getAttribute("email");
			req.setAttribute("sessionMessage", "Hello " + email); 
		}
		else{  
			req.setAttribute("errorMessage", "Please login first");  
            req.getRequestDispatcher("/_view/login.jsp").include(req, resp);  
        }  
		// Pass model to jsp
		req.setAttribute("course", model);
		req.setAttribute("department", department);
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("In Course Servlet doPost");
		Course model = new Course();
		CourseController courseController = new CourseController();
		courseController.setModel(model);
		
		AdviceController adviceController = new AdviceController();

		
		CourseController controller = new CourseController();
		controller.setModel(model);
		
		String courseName = req.getParameter("courseName");
		String departmentName = req.getParameter("departmentName");
		Integer adviceId = Integer.parseInt(req.getParameter("adviceId"));
		Integer flags = Integer.parseInt(req.getParameter("flags"));
		
		model.setName(courseName);
		Department department = new Department(departmentName);
		model.setDepartment(department);
		
		Rating adviceRating = new Rating(7,4,0,8);
		Advice advice1 = new Advice(adviceRating, "Sophmore", "Computer Engineering", 3.91, 4.0, "Spring", 2017, "Hake");
		advice1.setAdviceId(0);
		AdviceController adviceController1 = new AdviceController();
		adviceController1.setModel(advice1);
		advice1.setText("This class is the best and I am going to talk a lot so that I can see how it goes to display so much text I am running out of things to say so I'm just going to talk about how it snowed this week and we got a snow day and it was great but I just did homework during the day because I am an engineer and that's all I do I wonder if this is enough text I hope it is I am running out of stuff to say so I guess I am going to stop");
		advice1.setApproved(true);
		model.addAdvice(advice1);
		
		Rating adviceRating2 = new Rating(1,5,4,2);
		Advice advice2 = new Advice(adviceRating2, "Senior", "Computer Science", 3.53, 3.5, "Fall", 2013, "Hake");
		advice2.setAdviceId(1);
		advice2.setText("This class was the worst");
		advice2.setApproved(true);
		model.addAdvice(advice2);
		
		Rating adviceRating3 = new Rating(1,1,1,1);
		Advice advice3 = new Advice(adviceRating3, "Junior", "Electrical Engineering", 3.22, 3.0, "Fall", 2018, "Hake");
		advice3.setAdviceId(2);
		advice3.setText("This class was okay");
		advice3.setApproved(true);
		model.addAdvice(advice3);
		
		controller.computeAveGrade();
		controller.computeAveRating();
		
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
