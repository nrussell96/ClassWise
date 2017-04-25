package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController{
	private User user;
	private DerbyDatabase database;
	
	
	public UserController() {
		database = new DerbyDatabase();
	}
	
	public void setModel(User user) {
		this.user = user;
	}
	
	public Account getModel(){
		return this.user;
	}
	
	public boolean checkUserInfo(String email, String password){
		// eventually call database query
		return database.login(email, password);
		
	}
	
	public User getUserByEmail(String email){
		return database.getUserByEmail(email);
	}

	
}