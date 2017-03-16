package edu.ycp.cs320.tjones50.model;

public class Advice{
	private boolean approved;
	private Rating adviceRating;
	private int userClassYear;
	private String userMajor;
	private Double userGPA;
	private Double gradeRecieved;
	private int flag;
	private String semester;
	private int classYear;
	
	public Advice(){
		
	}
	
	public void adviceApproved(boolean approved){
		this.approved = approved;
	}
	
	public boolean getAdviceApproved(){
		return this.approved;
	}
	
	public void setAdviceRating(Rating adviceRating){
		this.adviceRating = adviceRating;
	}
	
	public Rating getAdviceRating(){
		return this.adviceRating;
	}
	
	public void setUserClassYear(int userClassYear){
		this.userClassYear = userClassYear;
	}
	
	public int getUserClassYear(){
		return this.userClassYear;
	}
	
	public void setUserMajor(String userMajor){
		this.userMajor = userMajor;
	}
	
	public String getUserMajor(){
		return this.userMajor;
	}
	
	public void setUserGPA(Double userGPA){
		this.userGPA = userGPA;
	}
	
	public Double getUserGPA(){
		return this.userGPA;
	}
	
	public void setGradeRecieved(Double gradeRecieved){
		this.gradeRecieved = gradeRecieved;
	}
	
	public Double getGradeRecieved(){
		return this.gradeRecieved;
	}
	
	public void setFlags(int flag){
		this.flag = flag;
	}
	
	public int getFlags(){
		return this.flag;
	}
	
	public void setSemester(String semester){
		this.semester = semester;
	}
	
	public String getSemester(){
		return this.semester;
	}
	
	public void setClassYear(int classYear){
		this.classYear = classYear;
	}
	
	public int getClassYear(){
		return this.classYear;
	}
}