package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Department;

public class DepartmentController {
	
	private Department model;
	
	public void setModel(Department model) {
		this.model = model;
	}
	
	public Department getModel(){
		return this.model;
	}
	
}
