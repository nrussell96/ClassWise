package edu.ycp.cs320.tjones50.model;
/*Example pulled from Lab2A Web Apps on the course web page
 * from Dr. Hake to assist in the structure of our servlets.
 */
public class GuessingGame {
	private int min, max;
	
	public GuessingGame() {
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	public int getMax() {
		return max;
	}
	
	public boolean isDone() {
		return min == max;
	}
	
	public int getGuess() {
		return min + (max-min)/2;
	}
	
	public void setIsLessThan(int guess) {
		this.max = guess-1;
	}
	
	public void setIsGreaterThan(int guess) {
		this.min = guess+1;
	}
}
