package edu.ycp.cs320.db.persist;
/*Example pulled from CS320_Library_Example_2017 on the course web page
 * from Dr. Hake and modified for our program.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;

public class InitialData {

	// reads initial Author data from CSV file and returns a List of Authors
	public static List<Department> getDepartments() throws IOException {
		List<Department> departmentList = new ArrayList<Department>();
		ReadCSV readDepartments = new ReadCSV("depts.csv");
		try {
			// auto-generated primary key for authors table
			Integer departmentId = 1;
			while (true) {
				List<String> tuple = readDepartments.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Department dept = new Department();

				
				// auto-generate author ID, instead
				dept.setDepartmentId(departmentId++);				
				dept.setName(i.next());
				departmentList.add(dept);
			}
			System.out.println("departmentList loaded from CSV file");
			return departmentList;
		} finally {
			readDepartments.close();
		}
	}
	
	// reads initial Book data from CSV file and returns a List of Books
	public static List<Course> getCourses() throws IOException {
		List<Course> courseList = new ArrayList<Course>();
		ReadCSV readCourses = new ReadCSV("courses.csv");
		try {
			// auto-generated primary key for table books
			Integer courseId = 1;
			while (true) {
				List<String> tuple = readCourses.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Course course = new Course();
				
				// auto-generate book ID, instead
				course.setCourseId(courseId++);				
//				book.setAuthorId(Integer.parseInt(i.next()));  // no longer in books table
				course.setName(i.next());
				course.setDepartmentId(Integer.parseInt(i.next()));
				
				courseList.add(course);
			}
			System.out.println("courseList loaded from CSV file");			
			return courseList;
		} finally {
			readCourses.close();
		}
	}
	
	
	
}