package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {
	private User user;
	private Pattern pattern;
	private Matcher matcher;
	private DerbyDatabase database;

	/*From class resources page on email validation
	 * https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	 */
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public UserController() {
		pattern = Pattern.compile(EMAIL_PATTERN);
		database = new DerbyDatabase();
	}

	public UserController(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		database = new DerbyDatabase();
		this.user = database.getUserByEmail(email);
		ArrayList<Advice> adviceList = database.getAccountAdviceList(user.getAccountId());
		ArrayList<Advice> NEWadviceList = new ArrayList<Advice>();
		for (Advice advice : adviceList) {
			Rating rating = database.getRatingByAdvice(advice);
			advice.setAdviceRating(rating);
			NEWadviceList.add(advice);
		}
		this.user.setArrAdvice(NEWadviceList);
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public boolean checkUserInfo(String email, String password) {
		return database.login(email, password);
	}

	/*From class resources page on email validation
	 * https://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	 */
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	public User getUserByEmail(String email) {
		return database.getUserByEmail(email);
	}

	public void createUser(String major, double GPA, String year, String email, String password) {
		database.createUserAccount(major, GPA, year, email, password);
	}

	public void deleteAdvice(Advice advice) {
		database.deleteAdvice(advice);
	}

	
	public void updateUserInfo(String password, String major, double GPA, String year){
		database.updateUserInformation(this.user, password, major, GPA, year);
		this.user = database.getUserByEmail(user.getEmail());
		System.out.println("User password: " + user.getPassword() + " User GPA " + user.getGPA());
	}
}