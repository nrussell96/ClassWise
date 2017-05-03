package edu.ycp.cs320.tjones50.controller;

import java.util.ArrayList;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Home;

public class HomeController {

	private Home home;
	private DerbyDatabase database = new DerbyDatabase();

	public HomeController() {
		this.home = new Home();
		home.setDepartments(database.getDeptList());
	}

	public void setHome(Home model) {
		home = model;
	}

	public Home getHome() {
		return this.home;
	}

	public ArrayList<Department> getDepartmentList() {
		return home.getDepartments();
	}

}
