package edu.ycp.cs320.tjones50.controller;

import edu.ycp.cs320.db.persist.DerbyDatabase;
import edu.ycp.cs320.tjones50.model.Advice;

public class AdviceController {
	private Advice advice;
	private DerbyDatabase database = new DerbyDatabase();

	public AdviceController() {

	}

	public AdviceController(int adviceId) {
		this.advice = database.getAdviceByAdviceId(adviceId);
		this.advice.setUserActivated(database.getUserByAdvice(this.advice).getApproved());
	}

	public void setModel(Advice advice) {
		this.advice = advice;
	}

	public Advice getModel() {
		this.advice.setUserActivated(database.getUserByAdvice(this.advice).getApproved());
		return this.advice;
	}

	public void flagAdvice() {
		database.setFlags(advice, advice.getFlags() + 1);
		this.advice = database.getAdviceByAdviceId(advice.getAdviceId());
		if (advice.getFlags() > 2) {
			database.disapproveAdvice(advice);
		}
		this.advice = database.getAdviceByAdviceId(advice.getAdviceId());
		this.advice.setUserActivated(database.getUserByAdvice(this.advice).getApproved());
	}
}