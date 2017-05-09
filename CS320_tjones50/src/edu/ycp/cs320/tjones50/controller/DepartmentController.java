package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Department;

public class DepartmentController {

	private Department department;
	private DerbyDatabase database = new DerbyDatabase();

	public DepartmentController() {

	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDepartmentByName(String departmentName) {
		this.department = database.getDepartmentByName(departmentName);
	}

	public Department getDepartment() {
		return this.department;
	}

}
