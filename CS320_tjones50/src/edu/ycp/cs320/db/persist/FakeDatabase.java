package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;

public class FakeDatabase implements IDatabase {

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
