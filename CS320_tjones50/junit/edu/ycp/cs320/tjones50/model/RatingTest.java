package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RatingTest {
	private Rating model;
	
	@Before
	public void setUp() {
		model = new Rating(2,2,2,2);
	}
	
	@Test
	public void TestSetDifficulty(){
		model.setDifficulty(4);
		assertTrue( 4.0 == model.getDifficulty());
	} 

	@Test
	public void TestSetInstruction(){
		model.setInstruction(4);
		assertTrue( 4.0 == model.getInstruction());
	} 
	
	@Test
	public void TestSetSuppliesCost(){
		model.setSuppliesCost(4);
		assertTrue( 4.0 == model.getSuppliesCost());
	} 
	
	@Test
	public void TestSetEnjoyment(){
		model.setEnjoyment(4);
		assertTrue( 4.0 == model.getEnjoyment());
	} 
	
	
}
