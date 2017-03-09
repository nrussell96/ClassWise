package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class HomeTest {
	private Home model;
	
	@Before
	public void setUp() {
		model = new Home();
	}
	
	@Test
	public void TestSetDepartments() {
		Department department1 = new Department();
		department1.setName("Computer Science");
		model.addDepartment(department1);
		Department department2 = new Department();
		department2.setName("Computer Engineering");
		model.addDepartment(department2);
		Department department3 = new Department();
		department3.setName("Electrical Engineering");
		model.addDepartment(department3);
		
		assertEquals("Computer Science", model.getDepartment().getName());
		assertEquals("Computer Engineering", model.getDepartment().getName());
		assertEquals("Electrical Engineering", model.getDepartment().getName());
	}
	
	
	
	
	
}
