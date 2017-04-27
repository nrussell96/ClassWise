package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Advice;

public class AdviceController{
	private Advice advice;
	private DerbyDatabase database = new DerbyDatabase();
	
	public AdviceController() {
		
	}
	
	public AdviceController(int adviceId) {
		this.advice = database.getAdviceByAdviceId(adviceId);
	}
	
	public void setModel(Advice advice) {
		this.advice = advice;
	}
	
	
	public Advice getModel(){
		return this.advice;
	}
	
	public void flagAdvice() {
		database.setFlags(advice, advice.getFlags()+1);
		this.advice = database.getAdviceByAdviceId(advice.getAdviceId());
		if(advice.getFlags() > 3){
			database.disapproveAdvice(advice);
		}
		this.advice = database.getAdviceByAdviceId(advice.getAdviceId());
	}
}