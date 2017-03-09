package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Department;

public class DepartmentControllerTest {
	private DepartmentController controller;
	private Department model;
	
	@Before
	public void setUp() {
		model = new Department();
		controller = new DepartmentController();
		controller.setModel(model);
	}
	
	@Test
	public void testModel() {
		assertTrue(model == controller.getModel());
	}
}
