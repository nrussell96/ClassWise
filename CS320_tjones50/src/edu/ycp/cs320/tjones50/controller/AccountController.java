package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Account;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountController{
	private Account model;
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public AccountController() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public void setModel(Account model) {
		this.model = model;
	}
	
	public Account getModel(){
		return this.model;
	}
	
	public boolean checkAccountInfo(String email, String password, String reenter){
		// eventually call database query
		
		if(email.equals("tjones50@ycp.edu") && password.equals("password1") && reenter.equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}