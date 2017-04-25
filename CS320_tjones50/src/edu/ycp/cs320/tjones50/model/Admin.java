package edu.ycp.cs320.tjones50.model;

public class Admin extends Account {
	private boolean approved = false;
	private String email;
	private String password;
	private int accountId;
	private boolean emailVerified = true;
	
	public Admin(){
		
	}
	
	public Admin(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	public void setApproved(boolean activated){
		this.approved = activated;
	}
	
	public boolean getApproved(){
		return this.approved;
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
	
	@Override
	public void setAccountId(int accountId) {
		this.accountId = accountId;
		
	}

	@Override
	public int getAccountId() {
		return this.accountId;
	}
	
	public Advice approveAdvice(Advice advice){
		advice.setApproved(true);
		return advice;
		
	}
	
	public Advice disapproveAdvice( Advice advice){
		advice.setApproved(false);
		return advice;
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
	
	public void setEmailVerified(boolean emailVerified){
		this.emailVerified = emailVerified;
	}
	
	public boolean getEmailVerified(){
		return this.emailVerified;
	}
	

}