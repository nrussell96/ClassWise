package edu.ycp.cs320.db.persist;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.tjones50.model.Admin;
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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	//  creates the database tables
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
				PreparedStatement stmt16 = null;
				PreparedStatement stmt17 = null;
				PreparedStatement stmt18 = null;
				
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
					
					stmt16 = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text) " +
							"values (2, 858, 'Fall', 'Professor Hake', 0, 3, 2017, true, 'Tough class, but very rewarding!')"
					);
					stmt16.executeUpdate();
					
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
							"values (1,2,3,4,5)"
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
					
					stmt17 = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment) " +
							"values (7,4,4,4,4)"
					);
					stmt17.executeUpdate();
					
					System.out.println("Ratings added");
					
					stmt18 = conn.prepareStatement(
							"insert into admins (email, password, activated, email_verified) " +
							"values ('admin@ycp.edu', 'password', true, true)"
					);
					stmt18.executeUpdate();
					
					System.out.println("admin added");
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
							"select * from departments order by name"
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

				// try to retrieve department
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

				// try to retrieve department
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
							"advices.course_id= ? order by advices.advice_id desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());

						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getCourseAdviceListSortedByOldest(Course course) {
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
							"advices.course_id= ? order by advices.advice_id asc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());

						advice.setUserActivated(user.getApproved());
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
	
	public Rating getRatingByAdvice(Advice advice) {
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
				
				// try to retrieve advice from account
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
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());

						advice.setUserActivated(user.getApproved());
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
	public Integer addAdviceToCourse(User user, Course course, String semester, String professor, double grade, int year, String text) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				// insert advice into db
				try {
					stmt = conn.prepareStatement(
							"insert into advices (user_id, course_id, semester, professor, flags, grade, class_year, approved, text)" +
							" values(?,?,?,?,0,?,?,true,?)"
					);
					
					stmt.setInt(1, user.getAccountId());
					stmt.setInt(2, course.getCourseId());
					stmt.setString(3, semester);
					stmt.setString(4, professor);
					stmt.setDouble(5, grade);
					stmt.setInt(6, year);
					stmt.setString(7, text);
					
					stmt.executeUpdate();
					
					//Get advice object just inserted
					stmt2 = conn.prepareStatement(
							"select * from advices where user_id = ? and course_id = ? and semester = ?"
							+ " and professor = ? and grade = ? and class_year = ? and text = ?"
					);
					
					stmt2.setInt(1, user.getAccountId());
					stmt2.setInt(2, course.getCourseId());
					stmt2.setString(3, semester);
					stmt2.setString(4, professor);
					stmt2.setDouble(5, grade);
					stmt2.setInt(6, year);
					stmt2.setString(7, text);
					
					resultSet2 = stmt2.executeQuery();
					
					// establish the Advice Object to receive the result
					Advice advice = new Advice();
					
					while (resultSet2.next()) {
						loadAdvice(advice, resultSet2, 1);
					}

					return advice.getAdviceId();
				} finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
			}

			
		});
		
	}
	
	@Override
	public Integer insertRating(int adviceId, double difficulty, double instruction, double supplyCost, double enjoyment) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				// insert rating into db
				try {
					stmt = conn.prepareStatement(
							"insert into ratings (advice_id, difficulty, instruction, supply_cost, enjoyment)" +
							" values(?,?,?,?,?)"
					);
					
					stmt.setInt(1, adviceId);
					stmt.setDouble(2, difficulty);
					stmt.setDouble(3, instruction);
					stmt.setDouble(4, supplyCost);
					stmt.setDouble(5, enjoyment);
					
					stmt.executeUpdate();
					
					//Get Rating object just inserted
					stmt2 = conn.prepareStatement(
							"select * from ratings where ratings.advice_id = ?"
					);
					
					stmt2.setInt(1, adviceId);
					
					resultSet2 = stmt2.executeQuery();
					
					// establish the Rating object to receive the result
					Rating rating = new Rating();
					
					while (resultSet2.next()) {
						loadRating(rating, resultSet2, 1);
					}

					return rating.getRatingId();
				} finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
			}

			
		});
	}
	
	public User getUserFromUserId(int userId) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve User
				try {
					stmt = conn.prepareStatement(
							"select * from users where " +
							"users.user_id = ?"
					);
					
					stmt.setInt(1, userId);
					
					// establish the User Object to receive the result
					User user = new User();

					// execute the query, get the results, and assemble them in the object
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadUser(user, resultSet, 1);
					}
					return user;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			
		});
	}
	
	public Advice getAdviceByAdviceId(int adviceId) {
		return executeTransaction(new Transaction<Advice>() {
			@Override
			public Advice execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Advice
				try {
					stmt = conn.prepareStatement(
							"select * from advices where " +
							"advices.advice_id = ?"
					);
					
					stmt.setInt(1, adviceId);
					
					// establish the Advice Object to receive the result
					Advice advice = new Advice();
					
					// execute the query, get the results, and assemble them in the object
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
					}
					return advice;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			
		});
	}
	
	public Rating getRatingByRatingId(int ratingId) {
		return executeTransaction(new Transaction<Rating>() {
			@Override
			public Rating execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Rating
				try {
					stmt = conn.prepareStatement(
							"select * from ratings where " +
							"ratings.rating_id = ?"
					);
					
					stmt.setInt(1, ratingId);
					
					// establish the Rating Object to receive the result
					Rating rating = new Rating();
					
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
	public ArrayList<Advice> getAdviceListSortedByGrade(Course course) {
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
							"advices.course_id= ? order by advices.grade desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedBySemester(Course course) {
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
							"advices.course_id= ? order by advices.semester asc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedByYear(Course course) {
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
							"advices.course_id= ? order by advices.class_year desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedByProfessor(Course course) {
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
							"advices.course_id= ? order by advices.professor"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedByDifficulty(Course course) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id order by ratings.difficulty desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedByInstruction(Course course) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id order by ratings.instruction desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedBySupplyCost(Course course) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id order by ratings.supply_cost desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedByEnjoyment(Course course) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id order by ratings.enjoyment desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public Boolean createUserAccount(String major, double GPA, String year, String email, String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				int userId = 0;
				Boolean added = false;
				
				// check if user exists in database
				try {
					stmt1 = conn.prepareStatement(
							"select user_id from users where email = ?"
					);
					
					stmt1.setString(1, email);
					
					
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()){
						userId = resultSet1.getInt(1);
					}
					else
					{
							// if the User is new, insert new User into Users table
							if (userId <= 0) {
								// prepare SQL insert statement to add User to Users table
								stmt2 = conn.prepareStatement(
										"insert into users (email,password,activated,email_verified,major,gpa,class_year) " +
										"  values(?, ?, true, true, ?, ?, ?) "
								);
								stmt2.setString(1, email);
								stmt2.setString(2, password);
								stmt2.setString(3, major);
								stmt2.setDouble(4, GPA);
								stmt2.setString(5, year);
								
								// execute the update
								stmt2.executeUpdate();
								
								added = true;
						}
					}
					
					return added;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt3);
				}
			}

			
		});
		
	}

	@Override
	public Boolean login(String email, String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				Boolean loggedIn = false;
				
				// check if user exists
				try {
					stmt1 = conn.prepareStatement(
							"select user_id from users where email = ? and password = ?"
					);
					
					stmt1.setString(1, email);
					stmt1.setString(2, password);
					
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()){
						loggedIn = true;
					}
					
					return loggedIn;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}

			
		});
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
	
	// retrieves Rating information from query result set
	private void loadRating(Rating rating, ResultSet resultSet, int index) throws SQLException {
		rating.setRatingId(resultSet.getInt(index++));
		rating.setAdviceId(resultSet.getInt(index++));
		rating.setDifficulty(resultSet.getDouble(index++));
		rating.setInstruction(resultSet.getDouble(index++));
		rating.setSuppliesCost(resultSet.getDouble(index++));
		rating.setEnjoyment(resultSet.getDouble(index++));
	}
	
	// retrieves User information from query result set
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setAccountId(resultSet.getInt(index++));
		user.setEmail(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setApproved(resultSet.getBoolean(index++));
		user.setEmailVerified(resultSet.getBoolean(index++));
		user.setMajor(resultSet.getString(index++));
		user.setGPA(resultSet.getDouble(index++));
		user.setUserClassYear(resultSet.getString(index++));
	}
	
	// retrieves Admin information from query result set
	private void loadAdmin(Admin admin, ResultSet resultSet, int index) throws SQLException {
		admin.setAccountId(resultSet.getInt(index++));
		admin.setEmail(resultSet.getString(index++));
		admin.setPassword(resultSet.getString(index++));
		admin.setApproved(resultSet.getBoolean(index++));
		admin.setEmailVerified(resultSet.getBoolean(index++));
	}


	@Override
	public ArrayList<Rating> getCourseRatings(Course course) {
		return executeTransaction(new Transaction<ArrayList<Rating>>() {
			@Override
			public ArrayList<Rating> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve ratings in course
				try {
					stmt = conn.prepareStatement(
							"select * from ratings, advices where "
							+ "ratings.advice_id = advices.advice_id"
							+ " and advices.course_id = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// establish the ArrayList of Rating objects to receive the result
					ArrayList<Rating> ratings = new ArrayList<Rating>();
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Rating rating = new Rating();
						loadRating(rating, resultSet, 1);
						ratings.add(rating);
					}
					
					return ratings;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Advice> getAdviceListSortedByGPA(Course course) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, users where " +
							"advices.course_id = ? and advices.user_id = users.user_id order by users.gpa desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListSortedByMajor(Course course) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, users where " +
							"advices.course_id = ? and advices.user_id = users.user_id order by users.major desc"
					);
					
					stmt.setInt(1, course.getCourseId());
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						advice.setUserActivated(user.getApproved());
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
	public ArrayList<Advice> getAdviceListByGrade(Course course, double grade) {
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
							"advices.course_id = ? and advices.grade = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setDouble(2, grade);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListByGPA(Course course, double GPA) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

			
				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, users where " +
							"advices.course_id = ? and users.user_id = advices.user_id and users.gpa = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setDouble(2, GPA);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListSemester(Course course, String semester) {
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
							"advices.course_id = ? and advices.semester = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setString(2, semester);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListMajor(Course course, String major) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, users where " +
							"advices.course_id = ? and users.user_id = advices.user_id and users.major = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setString(2, major);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListYear(Course course, int year) {
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
							"advices.course_id = ? and advices.class_year = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setInt(2, year);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListProfessor(Course course, String professor) {
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
							"advices.course_id = ? and advices.professor = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setString(2, professor);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListDifficulty(Course course, double difficulty) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id and ratings.difficulty = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setDouble(2, difficulty);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListInstruction(Course course, double instruction) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id and ratings.instruction = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setDouble(2, instruction);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListSupplyCost(Course course, double supplyCost) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id and ratings.supply_cost = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setDouble(2, supplyCost);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public ArrayList<Advice> getAdviceListEnjoyment(Course course, double Enjoyment) {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();

				// try to retrieve advice list, without ratings
				try {
					stmt = conn.prepareStatement(
							"select * from advices, ratings where " +
							"advices.course_id = ? and ratings.advice_id = advices.advice_id and ratings.enjoyment = ?"
					);
					
					stmt.setInt(1, course.getCourseId());
					stmt.setDouble(2, Enjoyment);
					
					// execute the query, get the results, and assemble them in an ArrayList
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						
						// establish the Advice Object to receive the result
						Advice advice = new Advice();
						loadAdvice(advice, resultSet, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
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
	public Integer setFlags(Advice advice, int flagNumber) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				// set flags
				try {
					stmt1 = conn.prepareStatement(
							"update advices set flags = ? where advices.advice_id = ?"
					);
					
					stmt1.setInt(1, flagNumber);
					stmt1.setInt(2, advice.getAdviceId());
					
					stmt1.executeUpdate();
					
					return flagNumber;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}

			
		});
	}

	@Override
	public User getUserByEmail(String email) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve user
				try {
					stmt = conn.prepareStatement(
							"select * from users where users.email = ?"
					);
					
					stmt.setString(1, email);
					
					// establish the User Object to receive the result
					User user = new User();
					
					// execute the query, get the results, and assemble them in the object
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						loadUser(user, resultSet, 1);
					}
					return user;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			
		});
	}

	@Override
	public User getUserByAdvice(Advice advice) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve user
				try {
					stmt = conn.prepareStatement(
							"select * from users,advices where advices.user_id = users.user_id"
							+ " and advices.advice_id = ?"
					);

					stmt.setInt(1, advice.getAdviceId());

					// establish the User Object to receive the result
					User user = new User();

					// execute the query, get the results, and assemble them in the object
					resultSet = stmt.executeQuery();

					while (resultSet.next()) {
						loadUser(user, resultSet, 1);
					}
					return user;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			
		});
	}

	@Override
	public Integer deleteAdvice(Advice advice) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet2 = null;
				
				try {
					//delete rating associated with advice
					stmt2 = conn.prepareStatement(
							"delete from ratings where advice_id = ?"
					);
					
					stmt2.setInt(1, advice.getAdviceId());
					
					stmt2.executeUpdate();

					// delete advice into db
					
						stmt = conn.prepareStatement(
								"delete from advices where advice_id = ?"
						);
						
						stmt.setInt(1, advice.getAdviceId());
						
						stmt.executeUpdate();
						
					return advice.getAdviceId();
				} finally {
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
			}

			
		});
	}
	
	//Only to be used for deleting empty accounts (no advice/ratings associated to it)
	@Override
		public Boolean deleteAccount(User user) {
			return executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					ResultSet resultSet2 = null;
					Boolean userDeleted = false;
					
					try {
						//check if user is in database
						stmt2 = conn.prepareStatement(
								"select * from users where email = ?"
						);
						
						stmt2.setString(1, user.getEmail());
						
						resultSet2 = stmt2.executeQuery();
						
						if(resultSet2.next()){
							//delete rating associated with advice
							stmt1 = conn.prepareStatement(
									"delete from users where user_id = ?"
							);
							
							stmt1.setInt(1, user.getAccountId());
							
							stmt1.executeUpdate();
							
							userDeleted = true;
						}
	
							return userDeleted;
					} finally {
						DBUtil.closeQuietly(resultSet2);
						DBUtil.closeQuietly(stmt1);
					}
				}

				
			});
	}

	@Override
	public Integer approveAdvice(Advice advice) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;

				try {
					// approve advice
					stmt = conn.prepareStatement(
							"update advices set approved = true where advice_id = ? "
					);
					
					stmt.setInt(1, advice.getAdviceId());
					
					stmt.executeUpdate();

					return advice.getAdviceId();
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}	
		});
	}

	@Override
	public Integer disapproveAdvice(Advice advice) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;

				try {
					// disapprove advice
					stmt = conn.prepareStatement(
							"update advices set approved = false where advice_id = ? "
					);
					
					stmt.setInt(1, advice.getAdviceId());
					
					stmt.executeUpdate();

					return advice.getAdviceId();
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}	
		});
	}

	@Override
	public Admin getAdminByEmail(String email) {
		return executeTransaction(new Transaction<Admin>() {
			@Override
			public Admin execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet1 = null;
				try {
					// get admin from email
					stmt = conn.prepareStatement(
							"select * from admins where email = ?"
					);
					
					stmt.setString(1, email);
					
					resultSet1 = stmt.executeQuery();
					Admin admin = new Admin();
					
					while (resultSet1.next()) {
						loadAdmin(admin, resultSet1, 1);
					}
					
					
					return admin;
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}	
		});
	}

	@Override
	public Integer deactivateUser(User user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					// deactivate user
					stmt = conn.prepareStatement(
							"update users set activated = false where user_id = ?"
					);
					
					stmt.setInt(1, user.getAccountId());
					
					stmt.executeUpdate();
					
					return user.getAccountId();
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}	
		});
	}
	
	@Override
	public Integer activateUser(User user) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					// activate user
					stmt = conn.prepareStatement(
							"update users set activated = true where user_id = ?"
					);
					
					stmt.setInt(1, user.getAccountId());
					
					stmt.executeUpdate();
					
					return user.getAccountId();
					
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}	
		});
	}

	@Override
	public Boolean adminLogin(String email, String password) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				Boolean loggedIn = false;
				
				// check if admin exists
				try {
					stmt1 = conn.prepareStatement(
							"select admin_id from admins where email = ? and password = ?"
					);
					
					stmt1.setString(1, email);
					stmt1.setString(2, password);
					
					resultSet1 = stmt1.executeQuery();
					
					if(resultSet1.next()){
						loggedIn = true;
					}
					
					return loggedIn;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	@Override
	public ArrayList<Advice> getUnapprovedAdvice() {
		return executeTransaction(new Transaction<ArrayList<Advice>>() {
			@Override
			public ArrayList<Advice> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet resultSet1 = null;
				ArrayList<Advice> adviceList = new ArrayList<Advice>();
				// retrieve all disapproved advice
				try {
					stmt1 = conn.prepareStatement(
							"select * from advices, courses where advices.course_id = courses.course_id "
							+ "and advices.approved = false"
					
					);
					
					resultSet1 = stmt1.executeQuery();
					
					
					
					while(resultSet1.next()){
						Advice advice = new Advice();
						loadAdvice(advice, resultSet1, 1);
						User user = getUserByAdvice(advice);
						Rating rating = getRatingByAdvice(advice);
						advice.setAdviceRating(rating);
						advice.setUserClassYear(user.getUserClassYear());
						advice.setUserGPA(user.getGPA());
						advice.setUserId(user.getAccountId());
						advice.setUserMajor(user.getMajor());
						
						advice.setUserActivated(user.getApproved());
						adviceList.add(advice);
					}

					return adviceList;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
}
