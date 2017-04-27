package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.db.persist.FakeDatabase;
import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Data;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Home;

public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In the department doGet");
		
		String email = (String)req.getSession().getAttribute("email"); //pulled from class example on session info

		System.out.println("   User: <" + email + "> logged in");
		
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info

		System.out.println("   Department: <" + departmentName + ">");
		
		//String url = req.getHeader("referer");
		
		
		// initialize variables
		DepartmentController controller = new DepartmentController();
		controller.setDepartmentByName(departmentName);
		Department model = controller.getDepartment();
		
		// Pass model to jsp
		req.setAttribute("department", model);
		req.setAttribute("email", email);
		//req.setAttribute("referer", url);
		
		req.getRequestDispatcher("/_view/department.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In the department doPost");
		
		String departmentName = (String)req.getSession().getAttribute("departmentName"); //pulled from class example on session info


		System.out.println("   Department: <" + departmentName + ">");
		
		// get info from parameters
		String courseName = req.getParameter("courseName");
		
		//String url = req.getParameter("referer");
		// store courseName obj in session
		req.getSession().setAttribute("courseName", courseName);
		//req.setAttribute("referer", req.getParameter("referer"));
		
		//req.getSession().setAttribute("referer", url);
		
		
		// Forward to view to render the result HTML document
		resp.sendRedirect(req.getContextPath() + "/course");
	}

}
