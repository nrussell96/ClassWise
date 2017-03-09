package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CourseTest {
	private Course model;
	
	@Before
	public void setUp() {
		model = new Course();
	}
	
	@Test
	public void TestSetName() {
		model.setName("name");
		assertEquals("name", model.getName());
	}
	
	@Test
	public void TestSetDepartment() {
		Department department = new Department("department");
		model.setDepartment(department);
		assertEquals("department", model.getDepartment().getName());
	}
	
	@Test
	public void TestSetAveRatings() {
		Rating aveRatings = new Rating(5,5,5,5);
		model.setAveRatings(aveRatings);
		assertEquals(aveRatings, model.getAveRatings());
	}
	
	@Test
	public void TestSetAveGrade() {
		model.setAveGrade(3.0);
		assertEquals((Double) 3.0, model.getAveGrade());
	}
	
	
	
	
}
