package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {
	private Department model;
	
	@Before
	public void setUp() {
		model = new Department();
	}
	
	@Test
	public void TestSetName() {
		model.setName("name");
		assertEquals("name", model.getName());
	}
	
	@Test
	public void TestSetCourses() {
		Course course1 = new Course();
		course1.setName("101");
		model.addCourse(course1);
		Course course2 = new Course();
		course2.setName("201");
		model.addCourse(course2);
		Course course3 = new Course();
		course3.setName("320");
		model.addCourse(course3);
		
		assertEquals("201", model.getCourse(course2).getName());
		assertEquals("320", model.getCourse(course3).getName());
		assertEquals("101", model.getCourse(course1).getName());
	}
	
	
	
	
	
}
