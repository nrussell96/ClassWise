package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.AccountController;
import edu.ycp.cs320.tjones50.controller.AdviceController;
import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the userAccount doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info
		if(email == null){
			System.out.println("User: <" + email + "> not logged in, or session timed out.");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		User model = new User();
		AccountController controller = new AccountController();
		controller.setModel(model);
		
		model.setEmail("tjones50@ycp.edu");
		model.setPassword("password1");
		model.setMajor("Computer Engineering");
		model.setGPA(3.91);
		model.setUserClassYear("Sophmore");
		
		Rating adviceRating = new Rating(7,4,0,8);
		Advice advice = new Advice(adviceRating, model.getUserClassYear(), model.getMajor(), model.getGPA(), 4.0, "Spring", 2017, "Hake");
		advice.setText("This class is the best and I am going to talk a lot so that I can see how it goes to display so much text I am running out of things to say so I'm just going to talk about how it snowed this week and we got a snow day and it was great but I just did homework during the day because I am an engineer and that's all I do I wonder if this is enough text I hope it is I am running out of stuff to say so I guess I am going to stop");
		advice.setApproved(true);
		model.addAdvice(advice);
		
		Rating adviceRating2 = new Rating(1,5,4,2);
		Advice advice2 = new Advice(adviceRating2, model.getUserClassYear(), model.getMajor(), model.getGPA(), 2.0, "Summer", 2017, "Hake");
		advice2.setText("This class was the worst");
		advice2.setApproved(true);
		model.addAdvice(advice2);
		
		Rating adviceRating3 = new Rating(1,1,1,1);
		Advice advice3 = new Advice(adviceRating3, model.getUserClassYear(), model.getMajor(), model.getGPA(), 3.5, "Fall", 2016, "Hake");
		advice3.setText("This class was okay");
		advice3.setApproved(true);
		model.addAdvice(advice3);
		
		
		
		// Pass model to jsp
		req.setAttribute("user", model);
		
		req.getRequestDispatcher("/_view/userAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the userAccount doPost");
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
	
}
