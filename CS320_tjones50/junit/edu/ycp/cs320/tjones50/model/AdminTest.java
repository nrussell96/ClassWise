package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdminTest {
	private Admin admin;
	private Advice advice;
	
	@Before
	public void setUp() {
		admin = new Admin();
		advice = new Advice();
	}
	
	@Test
	public void TestApproveAdvice() {
		admin.approveAdvice(advice);
		assertEquals(true, advice.getApproved());
	}
	
	@Test
	public void TestDisapproveAdvice() {
		admin.disapproveAdvice(advice);
		assertEquals(false, advice.getApproved());
	}
	
	
}
