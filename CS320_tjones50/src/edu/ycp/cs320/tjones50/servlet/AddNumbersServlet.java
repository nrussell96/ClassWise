package edu.ycp.cs320.tjones50.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.tjones50.controller.NumbersController;
import edu.ycp.cs320.tjones50.model.Numbers;

/*Example pulled from Lab2A Web Apps on the course web page
 * from Dr. Hake to assist in the structure of our servlets.
 */
public class AddNumbersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Decode form parameters and dispatch to controller
		Numbers model = new Numbers();
		NumbersController controller = new NumbersController();
		controller.setModel(model);

		try {
			Double first = getDoubleFromParameter(req.getParameter("first"));
			Double second = getDoubleFromParameter(req.getParameter("second"));
			Double third = getDoubleFromParameter(req.getParameter("third"));

			if (first == null || second == null || third == null) {
				model.setErrorMessage("Please specify three numbers");
			} else {

				controller.add(first, second, third);
			}
		} catch (NumberFormatException e) {
			model.setErrorMessage("Invalid double");
		}

		// Pass model to jsp
		req.setAttribute("game", model);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	}

	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
}
