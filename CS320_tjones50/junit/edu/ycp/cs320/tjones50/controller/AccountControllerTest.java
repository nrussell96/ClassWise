package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Account;

public class AccountControllerTest {

	private AccountController controller;
	private Account model;
	
	@Before
	public void setUp() {
		model = new Account();
		controller = new AccountController();
		controller.setModel(model);
	}
	
	@Test
	public void testModel() {
		assertTrue(model == controller.getModel());
	}
}
