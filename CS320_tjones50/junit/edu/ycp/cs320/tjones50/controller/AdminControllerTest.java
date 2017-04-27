package edu.ycp.cs320.tjones50.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.User;

public class AdminControllerTest {

	private AdminController controller1;
	private UserController controller2;
	private Admin model1;
	
	@Before
	public void setUp() {
		
		controller1 = new AdminController("admin@ycp.edu");
		model1 = controller1.getAdmin();
	}
	
	@Test
	public void testValidateValidEmail(){
		assertEquals(controller1.validate("admin@ycp.edu"), true);
	}
	
	@Test
	public void testValidateInvalidEmail(){
		assertEquals(controller1.validate("adminycp.edu"), false);
		assertEquals(controller1.validate("admin@ycp...edu"), false);
		assertEquals(controller1.validate("admin@ycp.e"), false);
	}
	
	@Test
	public void testModel() {
		assertTrue(model1 == controller1.getAdmin());
	}
	
	@Test
	public void TestCheckAdminInfo(){
		assertEquals(controller1.checkAdminInfo("admin@ycp.edu", "password"), true);
		assertEquals(controller1.checkAdminInfo("admin@ycp.edu", "passwor"), false);
	}
	
}
