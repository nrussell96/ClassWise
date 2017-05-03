package edu.ycp.cs320.tjones50.model;

import java.util.ArrayList;

public class Course {
	private String name;
	private Department department;
	private Rating aveRatings;
	private Double aveGrade;
	private ArrayList<Advice> arrAdvice = new ArrayList<Advice>();
	private String errorMessage;
	private int courseId;
	private int departmentId;

	public Course() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setAveRatings(Rating aveRatings) {
		this.aveRatings = aveRatings;
	}

	public Rating getAveRatings() {
		return this.aveRatings;
	}

	public void setAveGrade(Double aveGrade) {
		this.aveGrade = aveGrade;
	}

	public Double getAveGrade() {
		return this.aveGrade;
	}

	public void setArrAdvice(ArrayList<Advice> arrAdvice) {
		this.arrAdvice = arrAdvice;
	}

	public ArrayList<Advice> getArrAdvice() {
		return this.arrAdvice;
	}

	public void addAdvice(Advice advice) {
		this.arrAdvice.add(advice);
	}

	public Advice getAdvice(Advice advice) {
		return this.arrAdvice.get(this.arrAdvice.indexOf(advice));
	}

	public boolean removeAdvice(Advice advice) {
		return this.arrAdvice.remove(advice);
	}

}
