package edu.ycp.cs320.tjones50.model;

public class Account{
	private boolean activated = false;
	private String email;
	private String password;
	
	public Account(){
		
	}
	
	public Account(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	public void setActivated(boolean activated){
		this.activated = activated;
	}
	
	public boolean getApproved(){
		return this.activated;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	
}