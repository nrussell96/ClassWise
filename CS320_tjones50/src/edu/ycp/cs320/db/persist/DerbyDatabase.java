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
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;



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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/User/workspacelibrary.db;create=true");		
		
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
							"	class_year varchar(20)" +
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
							"   approved boolean, " + 
							"   text varchar(500)" +
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
	
//  loads sample users, advice, ratings into db for testing
	public void loadSampleData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				PreparedStatement stmt7 = null;
				PreparedStatement stmt8 = null;
				PreparedStatement stmt9 = null;
				PreparedStatement stmt10 = null;
				PreparedStatement stmt11 = null;
				PreparedStatement stmt12 = null;
				PreparedStatement stmt13 = null;
				PreparedStatement stmt14 = null;
				PreparedStatement stmt15 = null;
			
				try {
					
					stmt1 = conn.prepareStatement(
						"insert into users (email, password, activated, email_verified, major, gpa, class_year) " +
						"values ('student1@ycp.edu', 'password', true, true, 'Computer Science', 3.7, 'Junior')"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"insert into users (email, password, activated, email_verified, major, gpa, class_year) " +
							"values ('student2@ycp.edu', 'password', true, true, 'Computer Engineering', 3.8, 'Freshman')"
						);	
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
							"insert into users (email, password, activated, email_verified, major, gpa, class_year) " +
							"values ('student3@ycp.edu', 'password', true, true, 'Philosophy', 4.0, 'Senior')"
						);	
					stmt3.executeUpdate();
					
					System.out.println("User data inserted");
					
					stmt4 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (1, 858, 'Spring', 'Professor Hake', 0, 4, 2017, true, 'great class, lots of work')"
					);
					stmt4.executeUpdate();
					
					stmt5 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (1, 857, 'Fall', 'Professor Hovemeyer', 0, 4, 2016, true, 'helpful class, prepared me for CS320')"
					);
					stmt5.executeUpdate();
					
					stmt6 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (2, 889, 'Fall', 'Professor Moscola', 0, 3, 2016, true, 'easy class, I love circuits!')"
					);
					stmt6.executeUpdate();
					
					stmt7 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (2, 890, 'Spring', 'Professor Moscola', 0, 3.5, 2017, true, 'I learned a lot! ECE 220 helped!')"
					);
					stmt7.executeUpdate();
					
					stmt8 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (3, 1063, 'Summer', 'Professor Guy', 0, 3, 2016, true, 'this class opened my mind, I am renewed')"
					);
					stmt8.executeUpdate();
					
					stmt9 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (3, 1075, 'Spring', 'Professor Woman', 0, 4.0, 2017, true, 'This class taught me how to be a good person')"
					);
					stmt9.executeUpdate();
					System.out.println("Advice added");
					
					stmt10 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (1,1,1,1,1)"
					);
					stmt10.executeUpdate();
					
					stmt11 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (2,2,3,4,5)"
					);
					stmt11.executeUpdate();
					
					stmt12 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (3,5,4,1,4)"
					);
					stmt12.executeUpdate();
					
					stmt13 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (4,5,5,5,4)"
					);
					stmt13.executeUpdate();
					
					stmt14 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (5,1,2,3,3)"
					);
					stmt14.executeUpdate();
					
					stmt15 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (6,1,1,1,5)"
					);
					stmt15.executeUpdate();
					
					System.out.println("Ratings added");
					
					
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
		
		System.out.println("Loading sample data...");
		db.loadSampleData();
		
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
	public Department getDepartmentByName (String name) {
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
							" where departments.name = ?"
					);
					
					stmt.setString(1, name);
					
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
	public Course getCourseByName(String name) {
		return executeTransaction(new Transaction<Course>() {
			@Override
			public Course execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve course
				try {
					stmt = conn.prepareStatement(
							"select * from courses" + 
							" where courses.name = ?"
					);
					
					stmt.setString(1, name);
					
					// establish the Course object to receive the result
					Course course = new Course();
					
					// execute the query, get the results, and assemble them in course object
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
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices where " +
							"advices.course_id= ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// establish the Advice Object to receive the result
					Advice advice = new Advice();
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadAdvice(advice, resultSet, 1);
						Rating rating = getRatingFromAdvice(advice);
						advice.setAdviceRating(rating);
						adviceList.add(advice);
					}
					return adviceList;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Rating getRatingFromAdvice(Advice advice) {
		return executeTransaction(new Transaction<Rating>() {
			@Override
			public Rating execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Rating
				try {
					stmt = conn.prepareStatement(
							"select * from ratings where " +
							"ratings.advice_id = ?"
					);
					
					stmt.setInt(1, advice.getAdviceId());
					
					// establish the Rating Object to receive the result
					Rating rating  = new Rating();
					
					// execute the query, get the results, and assemble them in the object
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadRating(rating, resultSet, 1);
					}
					return rating;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			
		});
	}

	@Override
	public ArrayList<Advice> getAccountAdviceList(int userId) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();
				
				// try to retrieve Rating
				try {
					stmt = conn.prepareStatement(
							"select * from advices, users where " +
							"advices.user_id = ? and advices.user_id = users.user_id"
					);
					
					stmt.setInt(1, userId);
					
					
					
					// execute the query, get the results, and assemble them in the object
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						
						loadAdvice(advice, resultSet, 1);
						adviceList.add(advice);
					}
					return adviceList;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			
		});
	}

	@Override
	public void addAdviceToCourse(User user, Course course, double grade, String semester, int year, String professor,
			Rating rating) {
		
		
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByGrade(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedBySemester(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByYear(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByProfessor(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByDifficulty(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByInstruction(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedBySupplyCost(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByEnjoyment(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUserAccount(String major, double GPA, int year, String email, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void flagAdvice(Advice advice) {
		// TODO Auto-generated method stub
		
	}
	
	// retrieves Department information from query result set
	private void loadDepartment(Department dept, ResultSet resultSet, int index) throws SQLException {
		dept.setDepartmentId(resultSet.getInt(index++));
		dept.setName(resultSet.getString(index++));
	}
	
	// retrieves Course information from query result set
	private void loadCourse(Course course, ResultSet resultSet, int index) throws SQLException {
		course.setCourseId(resultSet.getInt(index++));
		course.setDepartmentId(resultSet.getInt(index++));
		course.setName(resultSet.getString(index++));
	}
		
	// retrieves Advice information from query result set
	private void loadAdvice(Advice advice, ResultSet resultSet, int index) throws SQLException {
		advice.setAdviceId(resultSet.getInt(index++));
		advice.setUserId(resultSet.getInt(index++));
		advice.setCourseId(resultSet.getInt(index++));
		advice.setSemester(resultSet.getString(index++));
		advice.setProfessor(resultSet.getString(index++));
		advice.setFlags(resultSet.getInt(index++));
		advice.setGradeReceived(resultSet.getInt(index++));
		advice.setClassYear(resultSet.getInt(index++));
		advice.setApproved(resultSet.getBoolean(index++));
		advice.setText(resultSet.getString(index++));
		
	}
	
	private void loadRating(Rating rating, ResultSet resultSet, int index) throws SQLException {
		rating.setAdviceId(resultSet.getInt(index++));
		rating.setDifficulty(resultSet.getDouble(index++));
		rating.setInstruction(resultSet.getDouble(index++));
		rating.setSuppliesCost(resultSet.getDouble(index++));
		rating.setEnjoyment(resultSet.getDouble(index++));
	}

	@Override
	public Rating getAdviceRating(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating getCourseRating(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByGPA(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByMajor(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListByGrade(Course course, double grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListByGPA(Course course, double GPA) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSemester(Course course, String semester) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListMajor(Course course, String major) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListYear(Course course, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListProfessor(Course course, String professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListDifficulty(Course course, double difficulty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListInstruction(Course course, double instruction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListSupplyCost(Course course, double supplyCost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAdviceListEnjoyment(Course course, double Enjoyment) {
		// TODO Auto-generated method stub
		return null;
	}
}
