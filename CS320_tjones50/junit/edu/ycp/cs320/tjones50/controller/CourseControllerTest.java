package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.Advice;

public class CourseControllerTest {
	private CourseController controller;
	private Course model;
	private ArrayList<Advice> arrAdvice;
	private Rating rating1;
	private Rating rating2;
	private Advice advice1;
	private Advice advice2;
	
	@Before
	public void setUp() {
		model = new Course();
		controller = new CourseController();
		controller.setModel(model);
		ArrayList<Advice> arrAdvice= new ArrayList<Advice>();
		rating1 = new Rating(2,2,2,2);
		rating2 = new Rating(4,4,4,4);
		advice1 = new Advice(rating1, "Freshman", "Biology", 4.0, 2.0, "Fall", 2017, "Hake");
		advice2 = new Advice(rating2, "Freshman", "Biology", 4.0, 4.0, "Fall", 2017, "Hake");
		arrAdvice.add(advice1);
		arrAdvice.add(advice2);
		model.setArrAdvice(arrAdvice);
	}
	
	@Test
	public void testModel() {
		assertTrue(model == controller.getModel());
	}
	
	@Test
	public void testComputeAveRating() {
		controller.computeAveRating();
		assertTrue(3.0 == model.getAveRatings().getDifficulty());
		assertTrue(3.0 == model.getAveRatings().getInstruction());
		assertTrue(3.0 == model.getAveRatings().getSuppliesCost());
		assertTrue(3.0 == model.getAveRatings().getEnjoyment());
	}
	
	@Test
	public void testComputeAveGrade() {
		controller.computeAveGrade();
		assertTrue(3.0 == model.getAveGrade());
	}
	
	
}
