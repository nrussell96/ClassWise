package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User user;
	private ArrayList<Advice> adviceList;
	private Advice advice1;
	private Advice advice2;
	
	@Before
	public void setUp() {
		user = new User();
		adviceList= new ArrayList<Advice>();
		advice1 = new Advice();
		Rating rating = new Rating(3,3,3,3);
		advice2 = new Advice(rating, "freshman", "Biology", 3.68, 3.5, "Fall", 2017);
		adviceList.add(advice1);
		adviceList.add(advice2);
	}
	
	
	@Test
	public void TestSetUserClassYear(){
		user.setUserClassYear("Sophmore");
		assertEquals("Sophmore", user.getUserClassYear());
	}
	
	@Test
	public void TestSetMajor(){
		user.setMajor("Computer Engineering");
		assertEquals("Computer Engineering", user.getMajor());
	}
	
	@Test
	public void TestGPA(){
		user.setGPA(3.91);
		assertTrue(3.91 == user.getGPA());
	}
	
	@Test
	public void TestEmailVerified(){
		user.setEmailVerified(true);
		assertEquals(true, user.getEmailVerified());
	}
	
	@Test
	public void TestSetAdviceList(){
		user.setAdviceList(adviceList);
		assertEquals(adviceList, user.getAdviceList());
	}
	
	@Test
	public void TestAddAdvice(){
		user.addAdvice(advice2);
		assertEquals(advice2, user.getAdvice(advice2));
	}
	
}
