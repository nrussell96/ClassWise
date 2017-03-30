package edu.ycp.cs320.tjones50.controller;

import java.util.ArrayList;

import edu.ycp.cs320.tjones50.model.Advice;
import edu.ycp.cs320.tjones50.model.Course;
import edu.ycp.cs320.tjones50.model.Rating;

public class CourseController {
	
	private Course model;
	
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
			aveGrade += arrAdvice.get(i).getGradeRecieved();
		}
		
		aveGrade = aveGrade/arrAdvice.size();
		model.setAveGrade(aveGrade);
	}
	
}
