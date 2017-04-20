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
	public void testValidateValidEmail(){
		assertEquals(controller1.validate("tjones50@ycp.edu"), true);
		assertEquals(controller1.validate("nrussell@ycp.edu"), true);
		assertEquals(controller1.validate("kjones65@ycp.edu"), true);
		
		assertEquals(controller2.validate("tjones50@ycp.edu"), true);
		assertEquals(controller2.validate("nrussell@ycp.edu"), true);
		assertEquals(controller2.validate("kjones65@ycp.edu"), true);
		
	}
	
	@Test
	public void testValidateInvalidEmail(){
		assertEquals(controller1.validate("tjones50@ycpedu"), false);
		assertEquals(controller1.validate("nrussellycp&^.edu"), false);
		assertEquals(controller1.validate("kjones65@ycp.)u"), false);
		
		assertEquals(controller2.validate("tjones50@ycp..edu"), false);
		assertEquals(controller2.validate("nrussellycp&^.edu"), false);
		assertEquals(controller2.validate("kjo#$nes65@ycp.)u"), false);
		
	}
	
	@Test
	public void testModel() {
		assertTrue(model1 == controller1.getModel());
		assertTrue(model2 == controller2.getModel());
	}
	
	@Test
	public void TestCheckAccountInfo(){
		assertEquals(controller1.checkAccountInfo("student1@ycp.edu", "password"), true);
		assertEquals(controller1.checkAccountInfo("student2@ycp.edu", "passwor"), false);
		assertEquals(controller2.checkAccountInfo("student3@ycp.edu", "password"), true);
		assertEquals(controller2.checkAccountInfo("student1@ycp", "password1"), false);
		
	}
	
}
