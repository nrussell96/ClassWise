package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdviceTest {
	private Advice model;
	
	@Before
	public void setUp() {
		model = new Advice();
	}
	
	@Test
	public void TestAdviceApproved() {
		model.setApproved(true);
		assertEquals(true, model.getApproved());
	}
	
	@Test
	public void TestSetAdviceRating(){
		Rating ratingVals = new Rating(4,3,4,5);
		model.setAdviceRating(ratingVals);
		assertEquals(ratingVals, model.getAdviceRating());
	} 

	@Test 
	public void TestSetUserClassYear(){
		model.setClassYear(2);
		assertEquals(2, model.getClassYear());
	}
	
	@Test
	public void TestSetUserMajor(){
		model.setUserMajor("Computer Science");
		assertEquals("Computer Science", model.getUserMajor());
	}
	
	@Test
	public void TestSetUserGPA(){
		model.setUserGPA(3.5);
		assertEquals((Double) 3.5, model.getUserGPA());
	}
	
	@Test
	public void TestSetGradeRecieved(){
		model.setGradeRecieved(2.0);
		assertEquals((Double) 2.0, model.getGradeRecieved());
	}
	
	@Test
	public void TestSetFlags(){
		model.setFlags(3);
		assertEquals(3, model.getFlags());
	}
	
	@Test
	public void TestSetSemester(){
		model.setSemester("Spring");
		assertEquals("Spring", model.getSemester());
	}
	
	@Test
	public void TestSetClassYear(){
		model.setClassYear(2017);
		assertEquals(2017, model.getClassYear());
	}
	
	@Test
	public void TestSetText(){
		model.setText("This class is the best");
		assertEquals("This class is the best", model.getText());
	}
	
	@Test
	public void TestAccountId(){
		model.setAccountId(1);
		assertEquals(1, model.getAccountId());
	}
	
	@Test
	public void TestAdviceId(){
		model.setAdviceId(1);
		assertEquals(1, model.getAdviceId());
	}
}
