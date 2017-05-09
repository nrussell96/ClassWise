package edu.ycp.cs320.tjones50.model;

public abstract class Account {

	abstract public void setApproved(boolean approved);

	abstract public boolean getApproved();

	abstract public void setEmail(String email);

	abstract public String getEmail();

	abstract public void setPassword(String password);

	abstract public String getPassword();

	abstract public void setAccountId(int accountId);

	abstract public int getAccountId();

}