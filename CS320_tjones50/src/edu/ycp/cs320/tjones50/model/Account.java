package edu.ycp.cs320.tjones50.model;

public abstract class Account{
	
	abstract public void setApproved(boolean approved);
	
	abstract public boolean getApproved();
	
	abstract public void setEmail(String email);
	
	abstract public String getEmail();
	
	abstract public void setPassword(String password);
	
	abstract public String getPassword();
	
	abstract public void setAccountId(int accountId);
	
	abstract public int getAccountId();
	
	abstract public void verifyEmail();
	
	abstract public void sendEmail();
	
	abstract public void login();
	
	abstract public void logout();
	
	abstract public void deleteAccount();
	
	abstract public void forgotPassword();
	
}