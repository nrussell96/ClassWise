package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.User;

public class UserControllerTest {

	private UserController controller1, controller2;
	private User model1, model2;
	
	@Before
	public void setUp() {
		model1 = new User();
		controller1 = new UserController();
		controller1.setUser(model1);
		
		model2 = new User();
		controller2 = new UserController();
		controller2.setUser(model2);
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
		assertTrue(model1 == controller1.getUser());
		assertTrue(model2 == controller2.getUser());
	}
	
	@Test
	public void TestCheckUserInfo(){
		assertEquals(controller1.checkUserInfo("student1@ycp.edu", "password"), true);
		assertEquals(controller1.checkUserInfo("student2@ycp.edu", "passwor"), false);
		assertEquals(controller2.checkUserInfo("student3@ycp.edu", "password"), true);
		assertEquals(controller2.checkUserInfo("student1@ycp", "password1"), false);
		
	}
	
}
