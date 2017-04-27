package edu.ycp.cs320.tjones50.persist;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.ycp.cs320.db.persist.DatabaseProvider;
import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.db.persist.IDatabase;
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class DerbyDatabaseTests {
	private IDatabase db = null;
	ArrayList<Department> depts = null;
	ArrayList<Course> courses = null;
	Course course = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetDeptListGetCourseListandGetDeptandGetCourse() {
		System.out.println("\n*** Testing getDeptList, getCourseList, getCourse, and getDept***");

		// get the list of depts from DB
		depts = db.getDeptList();
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (depts.isEmpty()) {
			System.out.println("No departments in database");
			fail("No departments returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for (Department dept: depts) {
				dept = db.getDept(dept);
				courses = db.getCourseList(dept);
				
				// NOTE: this is a simple test to check if no results were found in the DB
				if (depts.isEmpty()) {
					System.out.println("No courses in specified department");
					fail("No courses in specified department");
				}
				
				dept.setCourses(courses);
				System.out.println(dept.getDepartmentId() + ", " + dept.getName() + ": ");
				
				for (Course course: dept.getCourses()){
					course = db.getCourse(course);
					System.out.println(course.getName() + " , " + course.getCourseId());
				}
				
				
			}			
		}
	}
	
	@Test
	public void testGetCourseByName() {
		System.out.println("\n*** Testing getCourseByName***");

		String name = "CS 320 Software Engineering and Design";
		
		//Get course from db
		Course course = db.getCourseByName(name);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			System.out.println(course.getName());
		}
	}
	
	@Test
	public void testGetDepartmentByName() {
		System.out.println("\n*** Testing getDepartmentByName***");

		String name = "Computer Science";
		
		//Get dept from db
		Department dept = db.getDepartmentByName(name);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (dept == null) {
			System.out.println("No dept by that name found in db!");
			fail("No dept returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			System.out.println(dept.getName());
		}
	}
	
	@Test
	public void testGetCourseAdviceList() {
		System.out.println("\n*** Testing getCourseAdviceList***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getCourseAdviceList(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getText());
			}
		}
	}
	
	@Test
	public void testGetAccountAdviceList() {
		System.out.println("\n*** Testing getAccountAdviceList***");

		int userId = 1;
		
		//Get advice array from db
		ArrayList<Advice> adviceList = new ArrayList<Advice>();
		adviceList = db.getAccountAdviceList(userId);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (adviceList.isEmpty()) {
			System.out.println("No advice for that user found in db!");
			fail("No advice for that user returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getAdviceId() +  ", " + advice.getText());
			}
		}
	}
	
	@Test
	public void testGetRatingFromAdvice() {
		System.out.println("\n*** Testing getRatingFromAdvice***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getCourseAdviceList(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				Rating rating = db.getRatingByAdvice(advice);
				System.out.println("Advice ID: " + rating.getAdviceId() + ", Difficulty: " + rating.getDifficulty()+ 
						 ", Instruction: " + rating.getInstruction()+ ", Cost of Supplies: " + rating.getSuppliesCost() + ", Enjoyment: " + rating.getEnjoyment());
			}
		}
	}
	
	@Test
	public void testAddAdviceToCourseInsertRatingAndDeleteAdvice() {
		System.out.println("\n*** Testing addAdviceToCourse, insertRating, and deleteAdvice***");
		
		User 	user 		= db.getUserFromUserId(1);
		Course 	course 		= db.getCourseByName("CS 320 Software Engineering and Design");
		String 	semester 	= "Spring";
		String 	professor 	= "Professor Hovemeyer";
		double 	grade 		= 4.0;
		int 	year 		= 2015;
		String 	text 		= "Do your best or you'll fail!";
		
		// get advice_id from db method
		int advice_id = db.addAdviceToCourse(user, course, semester, professor, grade, year, text);
		

				double difficulty 	= 5.0;
				double instruction 	= 4.0;
				double supplyCost 	= 3.0;
				double enjoyment 	= 2.0;
				
				// get rating_id from db method
				int rating_id = db.insertRating(advice_id, difficulty, instruction, supplyCost, enjoyment);
				
				// NOTE: this is a simple test to check if no results were found in the DB
				if (rating_id > 0) {
					Rating rating = db.getRatingByRatingId(rating_id);
					
					System.out.println("AdviceID: " + rating.getAdviceId() +" " +"ratingID: " + rating.getRatingId() +" " +
							"Difficulty: " + rating.getDifficulty() +" " +"Instruction: " + rating.getInstruction() +" " +
							"Cost of Supplies: " + rating.getSuppliesCost() +" " +"Enjoyment: " + rating.getEnjoyment());
					
				}
				else{
					System.out.println("No rating with that id found in db!");
					fail("No rating returned from Library DB");
				}
					
		// NOTE: this is a simple test to check if no results were found in the DB
		if (advice_id > 0) {
			Advice advice = db.getAdviceByAdviceId(advice_id);
			
			System.out.println("AdviceID: " + advice.getAdviceId() +" " +"userID: " + advice.getUserId() +" " +
					"courseID: " + advice.getCourseId() +" " +"Semester: " + advice.getSemester() +" " +
					"Professor: " + advice.getProfessor() +" " +"flags: " + advice.getFlags() +" " +
					"Grade: " + advice.getGradeReceived() +" " +"Year: " + advice.getClassYear() +" " +
					"Approved: " + advice.getApproved() +" " +"Text: " + advice.getText() +" ");
			
		}
		else{
			System.out.println("No advice with that id found in db!");
			fail("No advice returned from Library DB");
		}
		Advice advice = db.getAdviceByAdviceId(advice_id);
		
		//delete advice to check, and clear out database if tested data
		int deletedAdviceId = db.deleteAdvice(advice);
				
				assertEquals(advice_id,deletedAdviceId);
				assertTrue(db.getAdviceByAdviceId(deletedAdviceId).getAdviceId()==0);
				assertTrue(db.getRatingByAdvice(advice).getRatingId() == 0);
		
	}
	
	
	@Test
	public void testGetUserFromUserId() {
		System.out.println("\n*** Testing getUserFromUserId***");

		int userId1 = 1;	//student1@ycp.edu
		int userId2 = 2;	//student2@ycp.edu
		
		// get the user object from database
		User user1 = db.getUserFromUserId(userId1);
		User user2 = db.getUserFromUserId(userId2);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (user1 == null) {
			System.out.println("No user by that name found in db!");
			fail("No users returned from Library DB");
		}
		else {			
			System.out.println("UserId: " + user1.getAccountId() + ", Email: "+ user1.getEmail()
			+ ", Password: "+ user1.getPassword() + ", Activated: "+ user1.getApproved() + ", Email Verified?: "+ 
			user1.getEmailVerified() + ", Major: "+ user1.getMajor() + ", GPA: "+ user1.getGPA() + ", class year: "+ user1.getUserClassYear());
		}
		if (user2 == null) {
			System.out.println("No user by that name found in db!");
			fail("No users returned from Library DB");
		}
		else {
		System.out.println("UserId: " + user2.getAccountId() + ", Email: "+ user2.getEmail()
		+ ", Password: "+ user2.getPassword() + ", Activated: "+ user2.getApproved() + ", Email Verified?: "+ 
		user2.getEmailVerified() + ", Major: "+ user2.getMajor() + ", GPA: "+ user2.getGPA() + ", class year: "+ user2.getUserClassYear());
		}
	}
	

	@Test
	public void testGetAdviceListSortedByGrade() {
		System.out.println("\n*** Testing getAdviceListSortedByGrade***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByGrade(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getGradeReceived());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedBySemester() {
		System.out.println("\n*** Testing getAdviceListSortedBySemester***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedBySemester(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getSemester());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedByYear() {
		System.out.println("\n*** Testing getAdviceListSortedByYear***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByYear(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getClassYear());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedByProfessor() {
		System.out.println("\n*** Testing getAdviceListSortedByProfessor***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByProfessor(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getProfessor());
			}
		}
	}
	
	@Test
	public void testLogin() {
		System.out.println("\n*** Testing Login***");

		String email 	= "student1@ycp.edu";
		String password = "password";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		assertTrue(db.login(email, password));
		assertFalse(db.login(email, "passwrd"));
		assertFalse(db.login("studnt1@ycp.edu", password));
		assertFalse(db.login("studnt1@ycp.edu","passwrd"));
	}
	
	@Test
	public void testSetFlags() {
		System.out.println("\n*** Testing setFlags***");
		
		Advice advice = db.getAdviceByAdviceId(1);
		db.setFlags(advice, 0);
		
		//should have no flags
		advice = db.getAdviceByAdviceId(1);
		assertTrue(advice.getFlags() == 0);
		
		db.setFlags(advice, advice.getFlags()+1);
		
		advice = db.getAdviceByAdviceId(1);
		assertTrue(advice.getFlags() == 1);
		
		db.setFlags(advice, advice.getFlags()+1);
		
		advice = db.getAdviceByAdviceId(1);
		assertTrue(advice.getFlags() == 2);
		
		db.setFlags(advice, 0);
	}
	
	@Test
	public void testGetAdviceListSortedByDifficulty() {
		System.out.println("\n*** Testing getAdviceListSortedByDifficulty***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByDifficulty(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getAdviceRating().getDifficulty());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedByInstruction() {
		System.out.println("\n*** Testing getAdviceListSortedByInstruction***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByInstruction(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getAdviceRating().getInstruction());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedBySupplyCost() {
		System.out.println("\n*** Testing getAdviceListSortedBySupplyCost***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedBySupplyCost(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getAdviceRating().getSuppliesCost());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedByEnjoyment() {
		System.out.println("\n*** Testing getAdviceListSortedByEnjoyment***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByEnjoyment(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getAdviceRating().getEnjoyment());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedByGPA() {
		System.out.println("\n*** Testing getAdviceListSortedByGPA***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByGPA(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getUserGPA());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSortedByMajor() {
		System.out.println("\n*** Testing getAdviceListSortedByMajor***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> sortedAdviceList = db.getAdviceListSortedByMajor(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (sortedAdviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: sortedAdviceList){
				System.out.println(advice.getUserMajor());
			}
		}
	}
	
	@Test
	public void testGetAdviceListByGrade() {
		System.out.println("\n*** Testing getAdviceListByGrade***");

		String name 	= "CS 320 Software Engineering and Design";
		double grade 	= 3.0;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListByGrade(course, grade);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getGradeReceived());
			}
		}
	}
	
	@Test
	public void testGetAdviceListByGPA() {
		System.out.println("\n*** Testing getAdviceListByGPA***");

		String name = "CS 320 Software Engineering and Design";
		double gpa 	= 3.7;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListByGPA(course, gpa);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getUserGPA());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSemester() {
		System.out.println("\n*** Testing getAdviceListSemester***");

		String name 	= "CS 320 Software Engineering and Design";
		String semester = "Spring";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListSemester(course, semester);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getSemester());
			}
		}
	}
	
	@Test
	public void testGetAdviceListMajor() {
		System.out.println("\n*** Testing getAdviceListMajor***");

		String name 	= "CS 320 Software Engineering and Design";
		String major 	= "Computer Science";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListMajor(course, major);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getUserMajor());
			}
		}
	}
	
	@Test
	public void testGetAdviceListYear() {
		System.out.println("\n*** Testing getAdviceListYear***");

		String name = "CS 320 Software Engineering and Design";
		int year 	= 2017;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListYear(course, year);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getClassYear());
			}
		}
	}
	
	@Test
	public void testGetAdviceListProfessor() {
		System.out.println("\n*** Testing getAdviceListProfessor***");

		String name 		= "CS 320 Software Engineering and Design";
		String professor 	= "Professor Hake";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListProfessor(course, professor);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getProfessor());
			}
		}
	}
	
	@Test
	public void testGetAdviceListDifficulty() {
		System.out.println("\n*** Testing getAdviceListDifficulty***");

		String name 		= "CS 320 Software Engineering and Design";
		double difficulty 	= 5.0;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListDifficulty(course, difficulty);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getAdviceRating().getDifficulty());
			}
		}
	}
	
	@Test
	public void testGetAdviceListInstruction() {
		System.out.println("\n*** Testing getAdviceListInstruction**");

		String name 		= "CS 320 Software Engineering and Design";
		double instruction 	= 5.0;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListInstruction(course,instruction);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getAdviceRating().getInstruction());
			}
		}
	}
	
	@Test
	public void testGetAdviceListSupplyCost() {
		System.out.println("\n*** Testing getAdviceListSupplyCost***");

		String name 		= "CS 320 Software Engineering and Design";
		double supplyCost 	= 5.0;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListSupplyCost(course, supplyCost);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getAdviceRating().getSuppliesCost());
			}
		}
	}
	
	@Test
	public void testGetAdviceListEnjoyment() {
		System.out.println("\n*** Testing getAdviceListEnjoyment***");

		String name 		= "CS 320 Software Engineering and Design";
		double enjoyment 	= 5.0;
		
		// get the course object from database and use that object to get the arraylist of advice from db
		Course course = db.getCourseByName(name);
		ArrayList<Advice> adviceList = db.getAdviceListEnjoyment(course, enjoyment);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (adviceList.isEmpty()) {
			System.out.println("No advice retrieved from db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice advice: adviceList){
				System.out.println(advice.getAdviceRating().getEnjoyment());
			}
		}
	}
	
	@Test
	public void testGetCourseRatings() {
		System.out.println("\n*** Testing getCourseRatings***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of ratings from db
		Course course = db.getCourseByName(name);
		ArrayList<Rating> ratingsList = db.getCourseRatings(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (course == null) {
			System.out.println("No course by that name found in db!");
			fail("No courses returned from Library DB");
		}
		if (ratingsList.isEmpty()) {
			System.out.println("No ratings retrieved from db!");
			fail("No ratings returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Rating rating: ratingsList){
				System.out.println("Difficulty: " + rating.getDifficulty() +" Instruction: " + rating.getInstruction()
				+ " Supples Cost: " + rating.getSuppliesCost() + " Enjoyment: " + rating.getEnjoyment());
			}
		}
	}
	
	@Test
	public void testGetAdviceByAdviceId() {
		System.out.println("\n*** Testing getAdviceByAdviceId***");

		int adviceId = 1;
		
		Advice advice = db.getAdviceByAdviceId(adviceId);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (advice == null) {
			System.out.println("No advice with that ID found in db!");
			fail("No advice returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			System.out.println(advice.getAdviceId());
		}
	}
	
	@Test
	public void testGetRatingByRatingId() {
		System.out.println("\n*** Testing getRatingByRatingId***");

		int ratingId = 1;
		
		Rating rating = db.getRatingByRatingId(ratingId);

		if (rating == null) {
			System.out.println("No rating with that ID found in db!");
			fail("No rating returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			System.out.println(rating.getAdviceId());
		}
	}
	
	@Test
	public void testGetUserByEmail() {
		System.out.println("\n*** Testing getUserByEmail***");

		String email = "student1@ycp.edu";
		
		//Get user from db
		User user = db.getUserByEmail(email);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (user == null) {
			System.out.println("No user by that name found in db!");
			fail("No user returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			System.out.println(user.getEmail());
		}
	}
	
	@Test
	public void testGetUserByAdvice() {
		System.out.println("\n*** Testing getUserByAdvice***");

		String name = "CS 320 Software Engineering and Design";
		
		// get the course object from database and use that object to get the arraylist of ratings from db
		Course course = db.getCourseByName(name);
		
		//Get advice of course from db
		ArrayList<Advice> adviceList = db.getCourseAdviceList(course);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (adviceList.isEmpty()) {
			System.out.println("No advice found in db!");
			fail("No advice returned from Library DB");
		}
		
		if (course == null) {
			System.out.println("No course found in db!");
			fail("No course returned from Library DB");
		}
		// NOTE: print out each department objects name and id to check to make sure they're correct
		else {			
			for(Advice adv: adviceList){
				System.out.println(adv.getUserId());
			}
		}
	}
	
	@Test
	public void testDeleteAdvice() {
		System.out.println("\n*** Testing deleteAdvice***");
		
		User 	user 		= db.getUserFromUserId(1);
		Course 	course 		= db.getCourseByName("CS 320 Software Engineering and Design");
		String 	semester 	= "Spring";
		String 	professor 	= "Professor Hovemeyer";
		double 	grade 		= 4.0;
		int year 			= 2015;
		String text 		= "Do your best or you'll fail!";
		Rating rating 		= new Rating(2.0, 3.0, 4.0, 5.0);
		int advice_id 		= db.addAdviceToCourse(user, course, semester, professor, grade, year, text);
		
		db.insertRating(advice_id, rating.getDifficulty(), rating.getInstruction(), rating.getSuppliesCost(), rating.getEnjoyment());

		Advice advice = db.getAdviceByAdviceId(advice_id);
		
		//get advice object from advice id
		
		int deletedAdviceId = db.deleteAdvice(advice);
		
		assertEquals(advice_id,deletedAdviceId);
		assertTrue(db.getAdviceByAdviceId(deletedAdviceId).getAdviceId()==0);
		assertTrue(db.getRatingByAdvice(advice).getRatingId() == 0);
		
	
	}
	
	@Test
	public void testApproveAndDisapproveAdvice() {
		System.out.println("\n*** Testing approveAdvice and disapproveAdvice***");
		
		Advice advice = db.getAdviceByAdviceId(1);
		
		db.approveAdvice(advice);
		assertTrue(advice.getApproved());
		
		db.disapproveAdvice(advice);
		advice = db.getAdviceByAdviceId(1);
		assertFalse(db.getAdviceByAdviceId(1).getApproved());
		
		db.approveAdvice(advice);
		advice = db.getAdviceByAdviceId(1);
		assertTrue(db.getAdviceByAdviceId(1).getApproved());
	}
	
	@Test
	public void testGetAdminByEmail() {
		System.out.println("\n*** Testing getAdminByEmail***");
		
		String email = "admin@ycp.edu";
		
		Admin admin = db.getAdminByEmail(email);
		
		//check to make sure all admin account info is correct
		assertTrue(admin.getAccountId()==1);
		assertTrue(admin.getApproved());
		assertTrue(admin.getEmail().equals(email));
		assertTrue(admin.getEmailVerified() == true);
		assertTrue(admin.getPassword().equals("password"));
		
	}
	
	@Test
	public void testActivateAndDeactivateUser() {
		System.out.println("\n*** Testing activateUser and deactivateUser***");
		
		User user = db.getUserByEmail("student1@ycp.edu");
		
		db.activateUser(user);
		assertTrue(user.getApproved());
		
		db.deactivateUser(user);
		user = db.getUserByEmail("student1@ycp.edu");
		
		assertFalse(user.getApproved());
		
		db.activateUser(user);
		user = db.getUserByEmail("student1@ycp.edu");
		
		assertTrue(user.getApproved());
		
	}
	
	@Test
	public void testAdminLogin() {
		System.out.println("\n*** Testing adminLogin***");
		
		String 	email 		= "admin@ycp.edu";
		String 	password	= "password";
		Boolean loggedIn	= false;
		
		//login with credentials
		loggedIn = db.adminLogin(email, password);
		
		assertTrue(loggedIn);
				
	}
	
	@Test
	public void testGetUnapprovedAdvice() {
		System.out.println("\n*** Testing getUnapprovedAdvice***");
		
		Course course = db.getCourseByName("CS 320 Software Engineering and Design");
		ArrayList<Advice> adviceList = db.getCourseAdviceList(course);
		int numberOfUnapproved = db.getUnapprovedAdvice().size();

		//check all advice is approved, then disapprove advice
		for(Advice adv: adviceList){
			assertTrue(adv.getApproved());
			db.disapproveAdvice(adv);
		}
		
		ArrayList<Advice> advicesList = db.getUnapprovedAdvice();
		for(Advice a: advicesList){
			System.out.println("Advice ID " + a.getAdviceId());
		}
		assertEquals(numberOfUnapproved + adviceList.size(), db.getUnapprovedAdvice().size());
		adviceList = db.getCourseAdviceList(course);
		
		//Set advice back to being approved
		for(Advice adv: adviceList){
			assertFalse(adv.getApproved());
			db.approveAdvice(adv);
		}	
	}
	
	@Test
	public void testCreateUserAccountAndDeleteAccount() {
		System.out.println("\n*** Testing createUserAccountAndDeleteAccount***");
		
		Boolean created  = 	false;
		String  major	 =	"English";
		double 	GPA		 =	3.5;
		String  year	 =	"Junior";
		String  email	 =	"student1@ycp.edu";
		String  password =	"password";
		
		//attempt to add user (should fail since email already exists)
		created = db.createUserAccount(major, GPA, year, email, password);
		
		assertFalse(created);
		assertEquals(db.getUserByEmail(email).getMajor(), "Computer Science");
		assertEquals(db.getUserByEmail(email).getAccountId(), 1);
		
		//should now work, since email is different
		created = db.createUserAccount(major, GPA, year, "student4@ycp.edu" , password);
		assertTrue(created);
		assertTrue(db.deleteAccount(db.getUserByEmail("student4@ycp.edu")));
	}
}
