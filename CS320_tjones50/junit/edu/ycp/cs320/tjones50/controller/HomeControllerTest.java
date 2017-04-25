package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Home;

public class HomeControllerTest {
	private HomeController controller;
	private Home home;
	
	@Before
	public void setUp() {
		home = new Home();
		controller = new HomeController();
		controller.setHome(home);
	}
	
	@Test
	public void testModel() {
		assertTrue(home == controller.getHome());
	}
}
