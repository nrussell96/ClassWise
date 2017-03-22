package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account model;
	
	@Before
	public void setUp() {
		model = new Account();
	}
	
	@Test
	public void TestAdviceApproved() {
		model.setActivated(true);
		assertEquals(true, model.getApproved());
	}
	
	@Test
	public void TestSetEmail(){
		model.setEmail("test1@ycp.edu");
		assertEquals("test1@ycp.edu", model.getEmail());
	}
	
	@Test
	public void TestSetPassword(){
		model.setPassword("password");
		assertEquals("password", model.getPassword());
	}
}
