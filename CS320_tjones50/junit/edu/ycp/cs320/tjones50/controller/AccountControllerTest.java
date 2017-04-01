package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.User;

public class AccountControllerTest {

	private AccountController controller1, controller2;
	private Account model1, model2;
	
	@Before
	public void setUp() {
		model1 = new User();
		controller1 = new AccountController();
		controller1.setModel(model1);
		
		model2 = new Admin();
		controller2 = new AccountController();
		controller2.setModel(model2);
	}
	
	
	@Test
	public void testModel() {
		assertTrue(model1 == controller1.getModel());
		assertTrue(model2 == controller2.getModel());
	}
	
	@Test
	public void TestCheckAccountInfo(){
		assertEquals(controller1.checkAccountInfo("tjones50@ycp.edu", "password1"), true);
		assertEquals(controller1.checkAccountInfo("tjones50@ycp.edu", "passwor"), false);
		
		assertEquals(controller2.checkAccountInfo("tjones50@ycp.edu", "password1"), true);
		assertEquals(controller2.checkAccountInfo("tjones50@ycp", "password1"), false);
		
	}
	
}
