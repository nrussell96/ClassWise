package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;

public interface IDatabase {
	
	abstract public ArrayList<Department> getDeptList();
	
	abstract public Department getDept(Department dept);
	
	abstract public ArrayList<Course> getCourseList(Department dept);
	
	abstract public Course getCourse(Course course);
	
	abstract public ArrayList<Advice> getCourseAdviceList(Course course);
	
	abstract public ArrayList<Advice> getAccountAdviceList(int accountId);
	
	// add methods to get a specific list of advice, i.e when user sorts advice by criteria  
	
	
	
	
}
