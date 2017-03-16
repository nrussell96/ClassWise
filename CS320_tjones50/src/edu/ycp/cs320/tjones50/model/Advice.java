package edu.ycp.cs320.tjones50.model;

public class Advice{
	private boolean approved = false;
	private Rating adviceRating;
	private String userClassYear;
	private String userMajor;
	private Double userGPA;
	private Double gradeRecieved;
	private int flags = 0;
	private String semester;
	private int classYear;
	
	public Advice(){
		
	}
	
	public Advice(Rating adviceRating, String userClassYear, String userMajor, Double userGPA, Double gradeRecieved, String semester, int classYear){
		this.adviceRating = adviceRating;
		this.userClassYear = userClassYear;
		this.userMajor = userMajor;
		this.userGPA = userGPA;
		this.gradeRecieved = gradeRecieved;
		this.semester = semester;
		this.classYear = classYear;
	}
	
	public void setApproved(boolean approved){
		this.approved = approved;
	}
	
	public boolean getApproved(){
		return this.approved;
	}
	
	public void setAdviceRating(Rating adviceRating){
		this.adviceRating = adviceRating;
	}
	
	public Rating getAdviceRating(){
		return this.adviceRating;
	}
	
	public void setUserClassYear(String userClassYear){
		this.userClassYear = userClassYear;
	}
	
	public String getUserClassYear(){
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
		this.flags = flag;
	}
	
	public int getFlags(){
		return this.flags;
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