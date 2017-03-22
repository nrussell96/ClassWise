package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Account;

public class AccountController{
	private Account model;
	
	public void setModel(Account model) {
		this.model = model;
	}
	
	public Account getModel(){
		return this.model;
	}
}