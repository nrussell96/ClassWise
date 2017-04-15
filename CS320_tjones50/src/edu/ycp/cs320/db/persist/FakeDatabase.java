package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

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
	public ArrayList<Course> getCourseList(Department dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getCourseAdviceList(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Advice> getAccountAdviceList(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAdviceToCourse(User user, Course course, double grade, String semester, int year, String professor,
			Rating rating) {
		// TODO Auto-generated method stub
		
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

	@Override
	public Course getCourseByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDepartmentByName(String name) {
		// TODO Auto-generated method stub
		return null;
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
