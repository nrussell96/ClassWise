package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NumbersTest {
	private Numbers model;
	
	@Before
	public void setUp() {
		model = new Numbers();
	}
	
	@Test
	public void TestSetAddNums() {
		model.setAddNums(2.0, 3.0, 4.0);
		assertTrue(2.0 == model.getAddNum1());
		assertTrue(3.0 == model.getAddNum2());
		assertTrue(4.0 == model.getAddNum3());
	}
	
	@Test
	public void TestSetMultNums() {
		model.setMultNums(2.0, 3.0);
		assertTrue(2.0 == model.getMultNum1());
		assertTrue(3.0 == model.getMultNum2());
	}
	
	@Test
	public void TestSetAddResult() {
		model.setAddResult(2.0);
		assertTrue(2.0 == model.getAddResult());
	}
	
	@Test
	public void TestSetMultResult() {
		model.setMultResult(2.0);
		assertTrue(2.0 == model.getMultResult());
	}
	
}
