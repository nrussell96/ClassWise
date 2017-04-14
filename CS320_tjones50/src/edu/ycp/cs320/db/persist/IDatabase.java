package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public interface IDatabase {
	
	abstract public ArrayList<Department> getDeptList();
	
	abstract public Department getDept(Department dept);
	
	abstract public ArrayList<Course> getCourseList(Department dept);
	
	abstract public Course getCourse(Course course);
	
	abstract public ArrayList<Advice> getCourseAdviceList(Course course);
	
	abstract public ArrayList<Advice> getAccountAdviceList(int accountId);
	
	abstract public void addAdviceToCourse (User user, Course course, double grade, String semester, int year, String professor, Rating rating);
	
	abstract public Rating getRating (Rating rating);
	
	abstract public ArrayList<Advice> getAdviceListSortedByGrade (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedBySemester (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByYear (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByProfessor (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByDifficulty (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByInstruction (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedBySupplyCost (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByEnjoyment (Course course);
	
	abstract public void createUserAccount (String major, double GPA, int year, String email, String password);
	
	abstract public boolean login (String email, String password);
	
	abstract public void flagAdvice (Advice advice);
	
	// add methods to get a specific list of advice, i.e when user sorts advice by criteria  
	
	
	
	
}
