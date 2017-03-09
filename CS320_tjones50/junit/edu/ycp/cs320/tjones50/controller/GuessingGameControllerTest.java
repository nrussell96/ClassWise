package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.controller.GuessingGameController;
import edu.ycp.cs320.tjones50.model.GuessingGame;

public class GuessingGameControllerTest {
	private GuessingGame model;
	private GuessingGameController controller;
	
	@Before
	public void setUp() {
		model = new GuessingGame();
		controller = new GuessingGameController();
		
		model.setMin(1);
		model.setMax(100);
		
		controller.setModel(model);
	}
	
	@Test
	public void teststartGame() {
		controller.startGame();
		assertTrue(model.getMax() == 100);
		assertTrue(model.getMin() == 1);
	}
	
	@Test
	public void testNumberIsGreater() {
		int currentGuess = model.getGuess();
		controller.setNumberIsGreaterThanGuess();
		assertTrue(model.getGuess() > currentGuess);
	}
	
	@Test
	public void testNumberIsLessThanGuess() {
		int currentGuess = model.getGuess();
		controller.setNumberIsLessThanGuess();
		assertTrue(model.getGuess() < currentGuess);
	}
	
	@Test
	public void testNumberFound() {
		int currentGuess = model.getGuess();
		controller.setNumberFound();
		assertTrue(currentGuess == model.getMin());
		assertTrue(currentGuess == model.getMax());
	}
	
	
}