package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public interface IDatabase {
	
	// get lists/objects
	
	abstract public ArrayList<Department> getDeptList();
	
	abstract public Department getDept(Department dept);
	
	abstract public Department getDepartmentByName (String name);
	
	abstract public ArrayList<Course> getCourseList(Department dept);
	
	abstract public Course getCourse(Course course);
	
	abstract public Course getCourseByName (String name);
	
	abstract public ArrayList<Advice> getCourseAdviceList(Course course);
	
	abstract public ArrayList<Advice> getAccountAdviceList(int accountId);
	
	abstract public void addAdviceToCourse (User user, Course course, double grade, String semester, int year, String professor, Rating rating);
	
	abstract public Rating getAdviceRating (Advice advice);
	
	abstract public Rating getCourseRating (Course course);
	
	// get sorted lists
	
	abstract public ArrayList<Advice> getAdviceListSortedByGrade (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByGPA (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedBySemester (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByMajor (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByYear (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByProfessor (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByDifficulty (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByInstruction (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedBySupplyCost (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByEnjoyment (Course course);
	
	// get specific searches
	
	abstract public ArrayList<Advice> getAdviceListByGrade (Course course, double grade);
	
	abstract public ArrayList<Advice> getAdviceListByGPA (Course course, double GPA);
	
	abstract public ArrayList<Advice> getAdviceListSemester (Course course, String semester);
	
	abstract public ArrayList<Advice> getAdviceListMajor (Course course, String major);
	
	abstract public ArrayList<Advice> getAdviceListYear (Course course, int year);
	
	abstract public ArrayList<Advice> getAdviceListProfessor (Course course, String professor);
	
	abstract public ArrayList<Advice> getAdviceListDifficulty (Course course, double difficulty);
	
	abstract public ArrayList<Advice> getAdviceListInstruction (Course course, double instruction);
	
	abstract public ArrayList<Advice> getAdviceListSupplyCost (Course course, double supplyCost);
	
	abstract public ArrayList<Advice> getAdviceListEnjoyment (Course course, double Enjoyment);
	
	// account methods
	
	abstract public void createUserAccount (String major, double GPA, int year, String email, String password);
	
	abstract public boolean login (String email, String password);
	
	abstract public void flagAdvice (Advice advice);
	  
	
	
	
	
}
