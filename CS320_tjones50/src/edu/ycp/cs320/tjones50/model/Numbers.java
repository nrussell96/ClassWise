package edu.ycp.cs320.tjones50.model;

public class Numbers {
	private double addNum1, addNum2, addNum3;
	private Double addResult;
	private double multNum1, multNum2;
	private Double multResult;
	private String errorMessage;

	public Numbers() {
		errorMessage = null;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setAddNums(double addNum1, double addNum2, double addNum3) {
		this.addNum1 = addNum1;
		this.addNum2 = addNum2;
		this.addNum3 = addNum3;
	}

	public void setAddResult(Double addResult) {
		this.addResult = addResult;
	}

	public void setMultNums(double multNum1, double multNum2) {
		this.multNum1 = multNum1;
		this.multNum2 = multNum2;
	}

	public void setMultResult(Double multResult) {
		this.multResult = multResult;
	}

	public double getAddNum1() {
		return this.addNum1;
	}

	public double getAddNum2() {
		return this.addNum2;
	}

	public double getAddNum3() {
		return this.addNum3;
	}

	public Double getAddResult() {
		return this.addResult;
	}

	public double getMultNum1() {
		return this.multNum1;
	}

	public double getMultNum2() {
		return this.multNum2;
	}

	public Double getMultResult() {
		return this.multResult;
	}

}
