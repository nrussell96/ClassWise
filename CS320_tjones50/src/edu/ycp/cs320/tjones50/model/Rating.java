package edu.ycp.cs320.tjones50.model;

public class Rating {
	private double difficulty;
	private double instruction;
	private double suppliesCost;
	private double enjoyment;
	
	public Rating() {
		
	}
	

	public Rating(double difficulty, double instruction, double suppliesCost, double enjoyment) {
		this.difficulty = difficulty;
		this.instruction = instruction;
		this.suppliesCost = suppliesCost;
		this.enjoyment = enjoyment;
	}
	
	public void setDifficulty(double difficulty){
		this.difficulty = difficulty;
	}
	public double getDifficulty(){
		return this.difficulty;
	}
	
	public void setInstruction(double instruction){
		this.instruction = instruction;
	}
	public double getInstruction(){
		return this.instruction;
	}
	
	public void setSuppliesCost(Integer suppliesCost){
		this.suppliesCost = suppliesCost;
	}
	public double getSuppliesCost(){
		return this.suppliesCost;
	}
	
	public void setEnjoyment(double enjoyment){
		this.enjoyment = enjoyment;
	}
	public double getEnjoyment(){
		return this.enjoyment;
	}
	
	
		

}
