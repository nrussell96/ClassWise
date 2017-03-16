package edu.ycp.cs320.tjones50.model;

import java.util.ArrayList;

public class Department {
	private String name;
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public Department() {
	}
	
	public Department(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setCourses(ArrayList<Course> courses){
		this.courses = courses;
	}
	public ArrayList<Course> getCourses(){
		return this.courses;
	}
	
	public void addCourse(Course course){
		course.setDepartment(this);
		this.courses.add(course);
	}
	public Course getCourse(){
		// redo, add counter that iterates through arraylist
		Course returnCourse =  this.courses.get(0);
		removeCourse(returnCourse);
		return returnCourse;
	}
	public boolean removeCourse(Course course){
		return this.courses.remove(course);
	}
	
	
	

}
