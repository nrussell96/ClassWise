package edu.ycp.cs320.db.persist;
/*Example pulled from CS320_Library_Example_2017 on the course web page
 * from Dr. Hake and modified for our program.
 */
public class PersistenceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PersistenceException(String msg) {
		super(msg);
	}
	
	public PersistenceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
