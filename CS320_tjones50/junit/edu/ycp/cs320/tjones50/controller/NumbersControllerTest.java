package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Numbers;

public class NumbersControllerTest {
	private NumbersController controller;
	private Numbers model;
	
	@Before
	public void setUp() {
		model = new Numbers();
		controller = new NumbersController();
		controller.setModel(model);
	}
	
	@Test
	public void testAdd() {
		controller.add(2.0 ,3.0 , 5.0);
		assertTrue(model.getAddResult() == 10);
	}
	
	@Test
	public void testMultiply(){
		controller.mult(2.0, 5.0);
		assertTrue(model.getMultResult() == 10);
	}
}
