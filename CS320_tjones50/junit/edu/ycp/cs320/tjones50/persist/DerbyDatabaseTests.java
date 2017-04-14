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
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;

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

		// get the list of (Author, Book) pairs from DB
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
}
