package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController{
	private Admin admin;
	private Pattern pattern;
	private Matcher matcher;
	private DerbyDatabase database;
	

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public AdminController() {
		pattern = Pattern.compile(EMAIL_PATTERN);
		database = new DerbyDatabase();
	}
	
	public AdminController(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		database = new DerbyDatabase();
		this.admin = database.getAdminByEmail(email);
		this.admin.setArrAdvice(database.getUnapprovedAdvice());
	}
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public Admin getAdmin(){
		return this.admin;
	}
	
	public boolean checkAdminInfo(String email, String password){
		return database.adminLogin(email, password);											//// need the database method
	}
	
	public boolean validate(final String hex) {
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
	
	public Admin getAdminByEmail(String email){
		return database.getAdminByEmail(email);
	}
	
	public ArrayList<Advice> getNewAdviceList(){
		return new ArrayList<Advice>();					//// need the database method
	}
	
	public void deleteAdvice(Advice advice){
		database.deleteAdvice(advice);
	}
	
	public void approveAdvice(Advice advice){
		database.approveAdvice(advice);
	}
	
	public void disapproveAdvice(Advice advice){
		database.disapproveAdvice(advice);
	}
	
	public void deactiveatUser(User user){
		database.deactivateUser(user);
	}
	
	public void activeatUser(User user){
		database.activateUser(user);
	}
	
	
	
	
}