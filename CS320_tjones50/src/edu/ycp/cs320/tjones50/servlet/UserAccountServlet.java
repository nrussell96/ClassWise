package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.db.persist.DerbyDatabase;
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
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60 * 20); //20 minute session
		
		if(email == null || session == null){
			System.out.println("User: <" + email + "> not logged in, or session timed out.");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		// create database
		//FakeDatabase database = new FakeDatabase();
		DerbyDatabase database = new DerbyDatabase();
		
		User model = database.getUserByEmail(email);
		AccountController controller = new AccountController();
		controller.setModel(model);
		//add all ratings to advice array
		ArrayList<Advice> adviceList = database.getAccountAdviceList(model.getAccountId());
		ArrayList<Advice> NEWadviceList = new ArrayList<Advice>();
		for ( Advice advice: adviceList){
			Rating rating = database.getRatingByAdvice(advice);
			advice.setAdviceRating(rating);
			NEWadviceList.add(advice);
			
		}
		// add advice with ratings to user class
		model.setArrAdvice(NEWadviceList);
		
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
