package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

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
	
	abstract public Integer addAdviceToCourse (User user, Course course, String semester, String professor, double grade, int year, String text, Rating rating); 	//derby done
			
	abstract public Integer insertRating (Advice advice, double difficulty, double instruction, double supplyCost, double enjoyment);	//derby done
	
	abstract public Rating getAdviceRating (Advice advice);
	
	abstract public Rating getCourseRating (Course course);
	
	abstract public User getUserFromUserId (int userId);	//derby done
	
	abstract public Advice getAdviceByAdviceId(int adviceId);
	
	abstract public Rating getRatingByRatingId(int ratingId);
	
	// get sorted lists
	
	abstract public ArrayList<Advice> getAdviceListSortedByGrade (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByGPA (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedBySemester (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByMajor (Course course);
	
	abstract public ArrayList<Advice> getAdviceListSortedByYear (Course course);	//derby done
	
	abstract public ArrayList<Advice> getAdviceListSortedByProfessor (Course course);	//derby done
	
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
	
	abstract public ArrayList<Advice> getAdviceListEnjoyment (Course course, double enjoyment);
	
	// account methods
	
	abstract public Integer createUserAccount (String major, double GPA, int year, String email, String password);	//derby done
	
	abstract public Boolean login (String email, String password);	//derby done
	
	abstract public Integer flagAdvice (Advice advice);	//derby done
	
	  
	
	
	
	
}
