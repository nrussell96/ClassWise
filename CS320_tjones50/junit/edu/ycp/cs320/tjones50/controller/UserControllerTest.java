package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Account;
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.User;

public class UserControllerTest {

	private UserController controller1, controller2;
	private User model1, model2;
	
	@Before
	public void setUp() {
		model1 = new User();
		controller1 = new UserController();
		controller1.setModel(model1);
		
		model2 = new User();
		controller2 = new UserController();
		controller2.setModel(model2);
	}
	
	
	@Test
	public void testModel() {
		assertTrue(model1 == controller1.getModel());
		assertTrue(model2 == controller2.getModel());
	}
	
	@Test
	public void TestCheckAccountInfo(){
		assertEquals(controller1.checkUserInfo("student1@ycp.edu", "password"), true);
		assertEquals(controller1.checkUserInfo("student2@ycp.edu", "passwor"), false);
		assertEquals(controller2.checkUserInfo("student3@ycp.edu", "password"), true);
		assertEquals(controller2.checkUserInfo("student1@ycp", "password1"), false);
		
	}
	
	@Test
	public void TestGetUserByEmail(){
		assertEquals(controller1.getUserByEmail("student1@ycp.edu").getEmail(), "student1@ycp.edu");
		assertEquals(controller1.getUserByEmail("student2@ycp.edu").getEmail(), "student2@ycp.edu");
		assertEquals(controller2.getUserByEmail("student3@ycp.edu").getEmail(), "student3@ycp.edu");
		assertEquals(controller2.getUserByEmail("student1@ycp.edu").getEmail(), "student1@ycp.edu");
	}
	
}
