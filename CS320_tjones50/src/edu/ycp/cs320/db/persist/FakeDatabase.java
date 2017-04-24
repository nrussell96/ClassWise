package edu.ycp.cs320.db.persist;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.controller.CourseController;
import edu.ycp.cs320.tjones50.model.Admin;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Data;
import edu.ycp.cs320.tjones50.model.Department;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class FakeDatabase implements IDatabase {
	private Data data = new Data();
	
	public FakeDatabase(){
		
	}

	@Override
	public ArrayList<Department> getDeptList() {
		data.populate();
		return data.getDepts();
	}

	@Override
	public Department getDept(Department dept) {
		data.populate();
		return data.getDept(dept.getName());
	}

	@Override
	public ArrayList<Course> getCourseList(Department dept) {
		data.populate();
		return data.getCourses();
	}

	@Override
	public Course getCourse(Course course) {
		data.populate();
		return data.getCourse(course);
	}

	@Override
	public ArrayList<Advice> getCourseAdviceList(Course course) {
		ArrayList<Advice> advices = new ArrayList<Advice>();
		
		Rating adviceRating = new Rating(7,4,0,8);
		Advice advice = new Advice(adviceRating, "Sophmore", "Computer Engineering", 3.91, 4.0, "Spring", 2017, "Hake");
		advice.setText("This class is the best and I am going to talk a lot so that I can see how it goes to display so much text I am running out of things to say so I'm just going to talk about how it snowed this week and we got a snow day and it was great but I just did homework during the day because I am an engineer and that's all I do I wonder if this is enough text I hope it is I am running out of stuff to say so I guess I am going to stop");
		advice.setApproved(true);
		advices.add(advice);
		
		Rating adviceRating2 = new Rating(1,5,4,2);
		Advice advice2 = new Advice(adviceRating2, "Senior", "Computer Science", 3.53, 3.5, "Fall", 2013, "Hake");
		advice2.setText("This class was the worst");
		advice2.setApproved(true);
		advices.add(advice2);
		
		Rating adviceRating3 = new Rating(1,1,1,1);
		Advice advice3 = new Advice(adviceRating3, "Junior", "Electrical Engineering", 3.22, 3.0, "Fall", 2018, "Hake");
		advice3.setText("This class was okay");
		advice3.setApproved(true);
		advices.add(advice3);
		
		return advices;
	}

	@Override
	public ArrayList<Advice> getAccountAdviceList(int accountId) {
		// TODO Auto-generated method stub
		return null;
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
	public Integer createUserAccount(String major, double GPA, String year, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer flagAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourseByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDepartmentByName(String name) {
		data.populate();
		ArrayList<Department> depts = data.getDepts();
		
		for(int i = 0; i< depts.size(); i++){
			if(depts.get(i).getName().equals(name)){
				return depts.get(i);
			}
		}
		return new Department("Error");
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

	@Override
	public Rating getRatingByAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserFromUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advice getAdviceByAdviceId(int adviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rating getRatingByRatingId(int ratingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rating> getCourseRatings(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer setFlags(Advice advice, int flagNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addAdviceToCourse(User user, Course course, String semester, String professor, double grade,
			int year, String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertRating(int adviceId, double difficulty, double instruction, double supplyCost,
			double enjoyment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAccount(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer approveAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer disapproveAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Advice getFlaggedAdvice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
