package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Home;

public class HomeControllerTest {
	private HomeController controller;
	private Home model;
	
	@Before
	public void setUp() {
		model = new Home();
		controller = new HomeController();
		controller.setModel(model);
	}
	
	@Test
	public void testModel() {
		assertTrue(model == controller.getModel());
	}
}
