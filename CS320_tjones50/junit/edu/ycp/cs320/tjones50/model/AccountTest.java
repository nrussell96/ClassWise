package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account user, admin;
	
	@Before
	public void setUp() {
		user = new User();
		admin = new Admin();
	}
	
	@Test
	public void TestAccountApproved() {
		user.setApproved(true);
		assertEquals(true, user.getApproved());
		admin.setApproved(false);
		assertEquals(false, admin.getApproved());
	}
	
	@Test
	public void TestSetEmail(){
		user.setEmail("test1@ycp.edu");
		assertEquals("test1@ycp.edu", user.getEmail());
		admin.setEmail("test1@ycp.edu");
		assertEquals("test1@ycp.edu", admin.getEmail());
	}
	
	@Test
	public void TestSetPassword(){
		user.setPassword("password");
		assertEquals("password", user.getPassword());
		admin.setPassword("password");
		assertEquals("password", admin.getPassword());
	}
	
	@Test
	public void TestSetReenter(){
		user.setReenter("password");
		assertEquals("password", user.getReenter());
		admin.setReenter("password");
		assertEquals("password", admin.getReenter());
	}
	
	@Test
	public void TestSetAccountId(){
		user.setAccountId(1);
		assertEquals(1, user.getAccountId());
		admin.setAccountId(1029);
		assertEquals(1029, admin.getAccountId());
	}
	
}
