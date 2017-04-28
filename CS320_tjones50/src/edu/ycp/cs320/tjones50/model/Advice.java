package edu.ycp.cs320.tjones50.model;

public class Advice{
	private boolean approved = false;
	private Rating adviceRating;
	private String userClassYear;
	private String userMajor;
	private double userGPA;
	private double gradeReceived;
	//private int helpfulFlags = 0;
	private int flags = 0;
	private String semester;
	private int classYear;
	private String text = null;
	private String errorMessage;
	private int adviceId;
	private int userId;
	private int courseId;
	private String professor;
	private boolean userActivated;
	
	public Advice(){
		
	}
	
	public Advice(Rating adviceRating, String userClassYear, String userMajor, double userGPA, double gradeRecieved, String semester, int classYear, String professor){
		this.adviceRating = adviceRating;
		this.userClassYear = userClassYear;
		this.userMajor = userMajor;
		this.userGPA = userGPA;
		this.gradeReceived = gradeRecieved;
		this.semester = semester;
		this.classYear = classYear;
		this.professor = professor;
	}
	
	
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public void setUserActivated(boolean userActivated){
		this.userActivated = userActivated;
	}
	
	public boolean getUserActivated(){
		return this.userActivated;
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
	
	public void setUserGPA(double userGPA){
		this.userGPA = userGPA;
	}
	
	public double getUserGPA(){
		return this.userGPA;
	}
	
	public void setGradeReceived(double gradeReceived){
		this.gradeReceived = gradeReceived;
	}
	
	public double getGradeReceived(){
		return this.gradeReceived;
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
	
	public void setProfessor(String professor){
		this.professor = professor;
	}
	
	public String getProfessor(){
		return this.professor;
	}
	
	public void setClassYear(int classYear){
		this.classYear = classYear;
	}
	
	public int getClassYear(){
		return this.classYear;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setAdviceId(int adviceId){
		this.adviceId = adviceId;
	}
	
	public int getAdviceId(){
		return this.adviceId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public int getUserId(){
		return this.userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	
}