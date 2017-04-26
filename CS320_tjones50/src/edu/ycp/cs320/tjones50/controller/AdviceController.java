package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.tjones50.model.Advice;

public class AdviceController{
	private Advice model;
	
	public void setModel(Advice model) {
		this.model = model;
	}
	
	public Advice getModel(){
		return this.model;
	}
	
	public void flagAdvice() {
		model.setFlags(model.getFlags() + 1);
		
	}
}