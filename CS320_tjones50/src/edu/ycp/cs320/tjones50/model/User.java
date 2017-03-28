package edu.ycp.cs320.tjones50.model;

import java.util.ArrayList;

public class User extends Account {
	private boolean approved = false;
	private String email;
	private String password;
	private int accountId;
	private String userClassYear;
	private String major;
	private double GPA;
	private boolean emailVerified = false;
	private ArrayList<Advice> adviceList =  new ArrayList<Advice>();

	public User() {

	}

	public User(String email, String password, String userClassId, String major, double GPA) {
		this.email = email;
		this.password = password;
		this.userClassYear = userClassId;
		this.major = major;
		this.GPA = GPA;
	}

	public void setApproved(boolean activated) {
		this.approved = activated;
	}

	public boolean getApproved() {
		return this.approved;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
	
	@Override
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public int getAccountId() {
		return this.accountId;
	}
	
	public void setUserClassYear(String userClassYear){
		this.userClassYear = userClassYear;
	}
	
	public String getUserClassYear(){
		return this.userClassYear;
	}
	
	public void setMajor(String major){
		this.major = major;
	}
	
	public String getMajor(){
		return this.major;
	}
	
	public void setGPA(double GPA){
		this.GPA = GPA;
	}
	
	public double getGPA(){
		return this.GPA;
	}
	
	public void setEmailVerified(boolean emailVerified){
		this.emailVerified = emailVerified;
	}
	
	public boolean getEmailVerified(){
		return this.emailVerified;
	}

	public void setAdviceList(ArrayList<Advice> adviceList) {
		this.adviceList = adviceList;
	}

	public ArrayList<Advice> getAdviceList() {
		return this.adviceList;
	}

	public void addAdvice(Advice advice) {
		this.adviceList.add(advice);
	}

	public Advice getAdvice(Advice advice) {
		return this.adviceList.get(this.adviceList.indexOf(advice));
	}

	public boolean removeAdvice(Advice advice) {
		return this.adviceList.remove(advice);
	}
	
	
	@Override
	public void verifyEmail() {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void sendEmail() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void login() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void logout() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void deleteAccount() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void forgotPassword() {
		throw new UnsupportedOperationException();

	}
}
