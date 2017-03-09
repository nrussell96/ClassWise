package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Numbers;

public class NumbersController {
	
	private Numbers model;
	
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	public void add(Double first, Double second, Double third) {
		this.model.setAddNums(first, second, third);
		this.model.setAddResult(first + second + third);
	}
	
	public void mult(Double first, Double second) {
		this.model.setMultNums(first, second);
		this.model.setMultResult(first * second);
	}
}
