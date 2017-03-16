package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Advice;

public class AdviceControllerTest {

	private AdviceController controller;
	private Advice model;
	
	@Before
	public void setUp() {
		model = new Advice();
		controller = new AdviceController();
		controller.setModel(model);
	}
	
	@Test
	public void testModel() {
		assertTrue(model == controller.getModel());
	}
}
