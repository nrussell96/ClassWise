package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Course;

public class CourseController {
	
	private Course model;
	
	public void setModel(Course model) {
		this.model = model;
	}
	
	public Course getModel(){
		return this.model;
	}
	
}
