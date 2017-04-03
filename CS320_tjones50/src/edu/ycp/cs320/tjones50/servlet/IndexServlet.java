package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
/*		HttpSession session=req.getSession(false); //Part of my attempt at making a session that persists - Nate 
		if(session != null){
			String email = (String)session.getAttribute("email");
			req.setAttribute("sessionMessage", "Hello " + email); 
		}
		else{  
			req.setAttribute("errorMessage", "Please login first");  
            req.getRequestDispatcher("/_view/login.jsp").include(req, resp);  
        } */
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
