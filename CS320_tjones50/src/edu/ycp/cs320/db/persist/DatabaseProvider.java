package edu.ycp.cs320.db.persist;
/*Example pulled from CS320_Library_Example_2017 on the course web page
 * from Dr. Hake and modified for our program.
 */
public class DatabaseProvider {
	private static IDatabase theInstance;
	
	public static void setInstance(IDatabase db) {
		theInstance = db;
	}
	
	public static IDatabase getInstance() {
		if (theInstance == null) {
			throw new IllegalStateException("IDatabase instance has not been set!");
		}
		return theInstance;
	}
}
