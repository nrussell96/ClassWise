package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.DepartmentController;
import edu.ycp.cs320.tjones50.model.Department;

public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In the department doGet");
		
		//pulled from Dr. Hake's Lab6 example on resources page
		String email = (String)req.getSession().getAttribute("email");
		System.out.println("   User: <" + email + "> logged in");
		String departmentName = (String)req.getSession().getAttribute("departmentName");
		System.out.println("   Department: <" + departmentName + ">");
		
		// initialize variables
		DepartmentController controller = new DepartmentController();
		controller.setDepartmentByName(departmentName);
		Department model = controller.getDepartment();
		
		// Pass model to jsp
		req.setAttribute("department", model);
		req.setAttribute("email", email);
		
		/*Clears cache to prevent user from going back
		 * to a previously logged in state after logging out--
		 * https://coderanch.com/t/351980/java/avoid-caching-JSP-pages
		 */
		resp.setHeader("Cache-Control","no-cache");
		resp.setHeader("Cache-Control","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader ("Expires", 0);
		
		req.getRequestDispatcher("/_view/department.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// session info 
		System.out.println("In the department doPost");
		
		//pulled from Dr. Hake's Lab6 example on resources page
		String departmentName = (String)req.getSession().getAttribute("departmentName"); 
		System.out.println("   Department: <" + departmentName + ">");

		// get info from parameters
		String courseName = req.getParameter("courseName");
		
		// store courseName obj in session
		req.getSession().setAttribute("courseName", courseName);
		
		// Forward to view to render the result HTML document
		resp.sendRedirect(req.getContextPath() + "/course");
	}

}
