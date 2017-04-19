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
		
		//Get course from db
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
	public void testAddAdviceToCourse() {
		System.out.println("\n*** Testing addAdviceToCourse***");
		
		User user = db.getUserFromUserId(1);
		Course course = db.getCourseByName("CS 320 Software Engineering and Design");
		String semester = "Spring";
		String professor = "Professor Hovemeyer";
		double grade = 4.0;
		int year = 2015;
		String text = "Do your best or you'll fail!";
		Rating rating = new Rating(2.0, 3.0, 4.0, 5.0);
		
		// get advice_id from db method
		// This is returning 8 for advice ID since it is getting the first advice that was added,
		// and even though the more times you run the test, it keeps adding the same advice,
		// it will keep returning adviceID of 8, since that was the first advice inserted
		int advice_id = db.addAdviceToCourse(user, course, semester, professor, grade, year, text, rating);
		
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
	}
	
	@Test
	public void testInsertRating() {
		System.out.println("\n*** Testing insertRating***");
		
		Advice advice = db.getAdviceByAdviceId(8);
		double difficulty = 5.0;
		double instruction = 4.0;
		double supplyCost = 3.0;
		double enjoyment = 2.0;
		
		// get advice_id from db method
		int rating_id = db.insertRating(advice, difficulty, instruction, supplyCost, enjoyment);
		
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

		String email = "student1@ycp.edu";
		String password = "password";
		
		// get the course object from database and use that object to get the arraylist of advice from db
		assertTrue(db.login(email, password));
		assertFalse(db.login(email, "passwrd"));
		assertFalse(db.login("studnt1@ycp.edu", password));
		assertFalse(db.login("studnt1@ycp.edu","passwrd"));
	}
	
	@Test
	public void testFlagAdvice() {
		System.out.println("\n*** Testing flagAdvice***");
		
		Advice advice = db.getAdviceByAdviceId(1);
		//should have no flags
		assertTrue(advice.getFlags() == 0);
		
		db.flagAdvice(advice);
		
		advice = db.getAdviceByAdviceId(1);
		assertTrue(advice.getFlags() == 1);
		db.flagAdvice(advice);
		
		advice = db.getAdviceByAdviceId(1);
		assertTrue(advice.getFlags() == 2);
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

		String name = "CS 320 Software Engineering and Design";
		double grade = 3.0;
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
		double gpa = 3.7;
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

		String name = "CS 320 Software Engineering and Design";
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

		String name = "CS 320 Software Engineering and Design";
		String major = "Computer Science";
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
		int year = 2017;
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

		String name = "CS 320 Software Engineering and Design";
		String professor = "Professor Hake";
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

		String name = "CS 320 Software Engineering and Design";
		double difficulty = 5.0;
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

		String name = "CS 320 Software Engineering and Design";
		double instruction = 5.0;
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

		String name = "CS 320 Software Engineering and Design";
		double supplyCost = 5.0;
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

		String name = "CS 320 Software Engineering and Design";
		double enjoyment = 2.0;
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
}
