package edu.ycp.cs320.tjones50.model;

import java.util.ArrayList;

public class Home {
	private ArrayList<Department> departments = new ArrayList<Department>();
	
	public Home() {
	}
	
	public void setDepartments(ArrayList<Department> departments){
		this.departments = departments;
	}
	public ArrayList<Department> getDepartments(){
		return this.departments;
	}
	
	public void addDepartment(Department department){
		this.departments.add(department);
	}
	public Department getDepartment(){
		// change to counter
		Department returnDepartment =  this.departments.get(0);
		removeDepartment(returnDepartment);
		return returnDepartment;
	}
	public boolean removeDepartment(Department department){
		return this.departments.remove(department);
	}
	
	
	

}
