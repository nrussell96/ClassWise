package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Course;

public class CourseControllerTest {
	private CourseController controller;
	private Course model;
	
	@Before
	public void setUp() {
		model = new Course();
		controller = new CourseController();
		controller.setModel(model);
	}
	
	@Test
	public void testModel() {
		assertTrue(model == controller.getModel());
	}
}
