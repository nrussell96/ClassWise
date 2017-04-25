package edu.ycp.cs320.tjones50.controller;

import java.util.ArrayList;

import edu.ycp.cs320.db.persist.DatabaseProvider;
import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.db.persist.IDatabase;
import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Rating;
import edu.ycp.cs320.tjones50.model.User;

public class CourseController {
	
	private Course model;
	private IDatabase db = null;
	
	public CourseController() {
		// creating DB instance here
				DatabaseProvider.setInstance(new DerbyDatabase());
				db = DatabaseProvider.getInstance();	
	}
	
	public void setModel(Course model) {
		this.model = model;
	}
	
	public Course getModel(){
		return this.model;
	}
	
	public void computeAveRating(){
		ArrayList<Advice> arrAdvice = model.getArrAdvice();
		double difficultySum = 0;
		double instructionSum = 0;
		double suppliesCostSum = 0;
		double enjoymentSum = 0;
		
		for(int i = 0; i<arrAdvice.size();i++){
			difficultySum += arrAdvice.get(i).getAdviceRating().getDifficulty();
			instructionSum += arrAdvice.get(i).getAdviceRating().getInstruction();
			suppliesCostSum += arrAdvice.get(i).getAdviceRating().getSuppliesCost();
			enjoymentSum += arrAdvice.get(i).getAdviceRating().getEnjoyment();
		}
		
		difficultySum = difficultySum/arrAdvice.size();
		instructionSum = instructionSum/arrAdvice.size();
		suppliesCostSum = suppliesCostSum/arrAdvice.size();
		enjoymentSum = enjoymentSum/arrAdvice.size();
		Rating aveRatings = new Rating(difficultySum, instructionSum, suppliesCostSum, enjoymentSum);
		model.setAveRatings(aveRatings);
	}
	
	public void computeAveGrade(){
		ArrayList<Advice> arrAdvice = model.getArrAdvice();
		double aveGrade = 0;
		
		for(int i = 0; i<arrAdvice.size();i++){
			aveGrade += arrAdvice.get(i).getGradeReceived();
		}
		
		aveGrade = aveGrade/arrAdvice.size();
		model.setAveGrade(aveGrade);
	}
	//adds advice 
	public void addAdviceAndRatingToCourse(User user, Course course, String semester, String professor, double grade, int year, String text, double difficulty, double instruction, double suppliesCost, double enjoyment) {
		int adviceId = db.addAdviceToCourse(user, course, semester, professor, grade, year, text);
		int ratingId = db.insertRating(adviceId, difficulty, instruction, suppliesCost, enjoyment);
	}
	
	public void FlagAdviceAsHelpful(Advice advice){
		db.flagAdviceAsHelpful(advice);
	}
	
	public void setHelpfulFlags(){
		
	}
}
