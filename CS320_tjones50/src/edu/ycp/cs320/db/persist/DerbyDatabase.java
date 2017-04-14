package edu.ycp.cs320.db.persist;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;



public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you specify the location of your Derby SQL database
	// TODO: You will need to change this location to the same path as your workspace for this example
	// TODO: Change it here and in SQLDemo under CS320_Lab06->edu.ycp.cs320.sqldemo	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/Kyle/Desktop/Code/York College/CS320/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
	
			
				try {
					
					stmt1 = conn.prepareStatement(
						"create table departments (" +
						"	department_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	name varchar(150)" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Departments table created");
					
					stmt2 = conn.prepareStatement(
							"create table courses (" +
							"	course_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	department_id integer constraint department_id references departments, " +
							"	name varchar(150)" +
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Courses table created");										
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Department> departmentList;
				List<Course> courseList;
				
				
				try {
					departmentList     = InitialData.getDepartments();
					courseList       = InitialData.getCourses();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertDepartment     = null;
				PreparedStatement insertCourse      = null;

				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertDepartment = conn.prepareStatement("insert into departments (name) values(?)");
					for (Department department : departmentList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertDepartment.setString(1, department.getName());
						insertDepartment.addBatch();
					}
					insertDepartment.executeBatch();
					
					System.out.println("Department table populated");
					
					// must completely populate Books table before populating BookAuthors table because of primary keys
					insertCourse = conn.prepareStatement("insert into courses (name, department_id) values(?, ?)");
					for (Course course : courseList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getAuthorId());	// this is now in the BookAuthors table
						insertCourse.setString(1, course.getName());
						insertCourse.setInt(2, course.getDepartmentId());
						insertCourse.addBatch();
					}
					insertCourse.executeBatch();
					
					System.out.println("Course table populated");					
					return true;
				} finally {
					DBUtil.closeQuietly(insertDepartment);
					DBUtil.closeQuietly(insertCourse);					
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}
	
	// Gets a list of all departments
	@Override
	public ArrayList<Department> getDeptList() {
		return executeTransaction(new Transaction<ArrayList<Department>>() {
			@Override
			public ArrayList<Department> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve all departments in database
				try {
					stmt = conn.prepareStatement(
							"select * from departments"
					);
					
					// establish the ArrayList of Department objects to receive the result
					// and establish arrayList of courses to be added to department
					ArrayList<Department> result = new ArrayList<Department>();
					ArrayList<Course> courses = new ArrayList<Course>();
					
					// execute the query, get the results, and assemble them in an ArrayLsit
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Department dept = new Department();
						loadDepartment(dept, resultSet, 1);
						courses = getCourseList(dept);
						dept.setCourses(courses);
						
						result.add(dept);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Department getDept(Department dept) {
		return executeTransaction(new Transaction<Department>() {
			@Override
			public Department execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Course> courses = null;

				// try to retrieve course
				try {
					stmt = conn.prepareStatement(
							"select * from departments" + 
							" where departments.department_id = ?"
					);
					
					stmt.setInt(1, dept.getDepartmentId());
					
					// establish the Department object to receive the result
					Department dept = new Department();
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadDepartment(dept, resultSet, 1);
						courses = getCourseList(dept);
						dept.setCourses(courses);
					}
					return dept;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Course> getCourseList(Department dept) {
		return executeTransaction(new Transaction<ArrayList<Course>>() {
			@Override
			public ArrayList<Course> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve all courses in specified department
				try {
					stmt = conn.prepareStatement(
							"select * from courses, departments where" + 
							" courses.department_id = departments.department_id" +
							" and departments.department_id = ?"
					);
					
					stmt.setInt(1, dept.getDepartmentId());
					
					// establish the ArrayList of Course objects to receive the result
					ArrayList<Course> courses = new ArrayList<Course>();
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Course course = new Course();
						loadCourse(course, resultSet, 1);
						courses.add(course);
					}
					
					return courses;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public Course getCourse(Course course) {
		return executeTransaction(new Transaction<Course>() {
			@Override
			public Course execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve course
				try {
					stmt = conn.prepareStatement(
							"select * from courses" + 
							" where courses.course_id = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// establish the Course object to receive the result
					Course course = new Course();
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadCourse(course, resultSet, 1);
					}
					return course;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Advice> getCourseAdviceList(Course course) {
		return null;
	}

	@Override
	public ArrayList<Advice> getAccountAdviceList(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// retrieves Department information from query result set
	private void loadDepartment(Department dept, ResultSet resultSet, int index) throws SQLException {
		dept.setDepartmentId(resultSet.getInt(index++));
		dept.setName(resultSet.getString(index++));
	}
		
	private void loadCourse(Course course, ResultSet resultSet, int index) throws SQLException {
		course.setCourseId(resultSet.getInt(index++));
		course.setDepartmentId(resultSet.getInt(index++));
		course.setName(resultSet.getString(index++));
	}
}
