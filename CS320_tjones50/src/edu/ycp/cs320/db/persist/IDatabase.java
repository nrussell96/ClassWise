package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public interface IDatabase {
	
	// get lists/objects
	
	abstract public ArrayList<Department> getDeptList();	//derby done
	
	abstract public Department getDept(Department dept);	//derby done
	
	abstract public Department getDepartmentByName (String name);	//derby done
	
	abstract public ArrayList<Course> getCourseList(Department dept);	//derby done
	
	abstract public Course getCourse(Course course);	//derby done
	
	abstract public Course getCourseByName (String name);	//derby done
	
	abstract public ArrayList<Advice> getCourseAdviceList(Course course);	//derby done
	
	abstract public ArrayList<Advice> getAccountAdviceList(int accountId);	//derby done

	abstract public Rating getRatingByAdvice(Advice advice);	//derby done
	
	abstract public Integer addAdviceToCourse (User user, Course course, String semester, String professor, double grade, int year, String text); 	//derby done
			
	abstract public Integer insertRating (int adviceId, double difficulty, double instruction, double supplyCost, double enjoyment);	//derby done
	
	abstract public ArrayList<Rating> getCourseRatings (Course course);		//derby done
	
	abstract public User getUserFromUserId (int userId);	//derby done
	
	abstract public Advice getAdviceByAdviceId(int adviceId);	//derby done
	
	abstract public Rating getRatingByRatingId(int ratingId);	//derby done
	
	abstract public User getUserByAdvice(Advice advice);	//derby done

	abstract public Integer deleteAdvice(Advice advice);	//derby done
	
	// get sorted lists
	
	abstract public ArrayList<Advice> getAdviceListSortedByGrade (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByGPA (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedBySemester (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByMajor (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByYear (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByProfessor (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByDifficulty (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByInstruction (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedBySupplyCost (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByEnjoyment (Course course);	//derby done
	
	// get specific searches
	
	abstract public ArrayList<Advice> getAdviceListByGrade (Course course, double grade);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListByGPA (Course course, double GPA);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSemester (Course course, String semester);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListMajor (Course course, String major);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListYear (Course course, int year);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListProfessor (Course course, String professor);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListDifficulty (Course course, double difficulty);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListInstruction (Course course, double instruction);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSupplyCost (Course course, double supplyCost);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListEnjoyment (Course course, double enjoyment);	//derby done
	
	// account methods
	
	abstract public Integer createUserAccount (String major, double GPA, String year, String email, String password);	//derby done
	
	abstract public Boolean login (String email, String password);	//derby done
	
	abstract public Integer flagAdviceAsHelpful (Advice advice);	//derby done
	
	abstract public Integer setHelpfulFlags(Advice advice, int flagNumber);	//derby done
	
	abstract public User getUserByEmail(String email);	//derby done
	
	abstract public Integer deleteAccount(User user);	//Should we really do this?
	
	//Admin methods
	
	abstract public Integer approveAdvice(Advice advice);	//derby done
	
	abstract public Integer disapproveAdvice (Advice advice);	//derby done,
	
	abstract public Admin getAdminByEmail(String email);	//derby done
	
	abstract public Advice getFlaggedAdvice();		//Are we going to be flagging advice?
	
	abstract public Integer deactivateUser(User user);	//derby done
	
	abstract public Integer activateUser(User user);	//derby done
	
	
	
	
	
	
}
