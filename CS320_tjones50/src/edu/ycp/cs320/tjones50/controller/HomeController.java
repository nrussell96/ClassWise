package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Home;

public class HomeController {
	
	private Home model;
	
	public void setModel(Home model) {
		this.model = model;
	}
	
	public Home getModel(){
		return this.model;
	}
	
}
