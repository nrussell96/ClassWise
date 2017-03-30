package edu.ycp.cs320.tjones50.model;

public class Rating {
	private Integer difficulty;
	private Integer instruction;
	private Integer suppliesCost;
	private Integer enjoyment;
	
	public Rating() {
		
	}
	
	public Rating(Integer difficulty, Integer instruction, Integer suppliesCost, Integer enjoyment) {
		this.difficulty = difficulty;
		this.instruction = instruction;
		this.suppliesCost = suppliesCost;
		this.enjoyment = enjoyment;
	}
	
	public void setDifficulty(Integer difficulty){
		this.difficulty = difficulty;
	}
	public Integer getDifficulty(){
		return this.difficulty;
	}
	
	public void setInstruction(Integer instruction){
		this.instruction = instruction;
	}
	public Integer getInstruction(){
		return this.instruction;
	}
	
	public void setSuppliesCost(Integer suppliesCost){
		this.suppliesCost = suppliesCost;
	}
	public Integer getSuppliesCost(){
		return this.suppliesCost;
	}
	
	public void setEnjoyment(Integer enjoyment){
		this.enjoyment = enjoyment;
	}
	public Integer getEnjoyment(){
		return this.enjoyment;
	}
	
	
		

}
