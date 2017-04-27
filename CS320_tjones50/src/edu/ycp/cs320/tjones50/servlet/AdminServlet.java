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
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.User;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the admin doGet");
		
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
		DerbyDatabase database = new DerbyDatabase();
				
		Admin model = database.getAdminByEmail(email);
		AccountController controller = new AccountController();
		controller.setModel(model);
		
		/* ArrayList<Advice> adviceList = database.getAccountAdviceList(model.getAccountId());
		ArrayList<Advice> NEWadviceList = new ArrayList<Advice>();
		for ( Advice advice: adviceList){
			Rating rating = database.getRatingByAdvice(advice);
			advice.setAdviceRating(rating);
			NEWadviceList.add(advice); */
		
		// Pass model to jsp
		req.setAttribute("admin", model);
			
		req.getRequestDispatcher("/_view/Admin.jsp").forward(req, resp);
//		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the admin doPost");
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/course.jsp").forward(req, resp);
	}
}