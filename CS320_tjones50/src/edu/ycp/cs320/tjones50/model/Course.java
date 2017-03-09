package edu.ycp.cs320.tjones50.model;

public class Course {
	private String name;
	private Department department;
	private Rating aveRatings;
	private Double aveGrade;
	
	public Course() {
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setDepartment(Department department){
		this.department = department;
	}
	public Department getDepartment(){
		return this.department;
	}
	
	public void setAveRatings(Rating aveRatings){
		this.aveRatings = aveRatings;
	}
	public Rating getAveRatings(){
		return this.aveRatings;
	}
	
	public void setAveGrade(Double aveGrade){
		this.aveGrade = aveGrade;
	}
	public Double getAveGrade(){
		return this.aveGrade;
	}
	
	

}
