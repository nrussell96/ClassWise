package edu.ycp.cs320.db.persist;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/User/workspace/library.db;create=true");		
		
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
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
	
			
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
					
					stmt3 = conn.prepareStatement(
							"create table users (" +
							"	user_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	email varchar(150)," +
							"	password varchar(150)," +
							"   activated boolean, " +
							"   email_verified boolean, " + 
							"	major varchar(150)," +
							"	GPA double precision," +
							"	class_year integer" +
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("Users table created");
					
					stmt4 = conn.prepareStatement(
							"create table admins (" +
							"	admin_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	email varchar(150)," +
							"	password varchar(150)," +
							"   activated boolean, " + 
							"   email_verified boolean " + 
							")"
					);
					stmt4.executeUpdate();
					
					System.out.println("Admins table created");
					
					stmt5 = conn.prepareStatement(
							"create table advices (" +
							"	advice_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	user_id integer constraint user_id references users, " +
							"	course_id integer constraint course_id references courses, " +
							"	semester varchar(150)," +
							"	professor  varchar(150)," +
							"	flags integer," + 
							"	grade double precision," +
							"	class_year integer," +
							"   approved boolean " + 
							")"
					);
					stmt5.executeUpdate();
					
					System.out.println("Advices table created");
					
					
					stmt6 = conn.prepareStatement(
							"create table ratings (" +
							"	rating_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	advice_id integer constraint advice_id references advices, " +
							"	difficulty integer," +
							"	instruction integer," +
							"	supply_cost integer," +
							"	enjoyment integer" +
							")"
					);
					stmt6.executeUpdate();
					
					System.out.println("Ratings table created");
					
					
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
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

	@Override
	public ArrayList<Department> getDeptList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDept(Department dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Course> getCourseList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getCourseAdviceList(String CourseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAccountAdviceList(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}
}
