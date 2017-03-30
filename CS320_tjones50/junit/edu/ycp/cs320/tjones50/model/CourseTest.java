package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	@Test
	public void TestSetArrAdvice() {
		ArrayList<Advice> arrAdvice = new ArrayList<Advice>();
		Advice advice = new Advice();
		advice.setGradeRecieved(4.0);
		arrAdvice.add(advice);
		model.setArrAdvice(arrAdvice);
		assertEquals((Double) 4.0, model.getAdvice(advice).getGradeRecieved());
	}
	
}
