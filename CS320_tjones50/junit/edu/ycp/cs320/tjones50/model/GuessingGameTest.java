package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.GuessingGame;

public class GuessingGameTest {
	private GuessingGame model;
	
	@Before
	public void setUp() {
		model = new GuessingGame();
	}
	
	@Test
	public void testSetMin() {
		model.setMin(1);
		assertEquals(1, model.getMin());
	}
	
	public void testSetMax() {
		model.setMax(100);
		assertEquals(100, model.getMax());
	}
	
	public void testisDone() {
		model.setMax(100);
		model.setMin(100);
		assertTrue(model.isDone());
	}
	
	public void testGetGuess() {
		model.setMax(100);
		model.setMin(1);
		assertTrue(model.getGuess() == 50);
	}
	
	public void testIsLessThan() {
		model.setMax(100);
		model.setMin(1);
		model.setIsLessThan(50);
		assertTrue(50 == model.getMax());
	}
	
	public void testIsGreaterThan() {
		model.setMax(100);
		model.setMin(1);
		model.setIsGreaterThan(50);
		assertTrue(50 == model.getMin());
	}
}
