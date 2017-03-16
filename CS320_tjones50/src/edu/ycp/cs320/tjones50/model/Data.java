package edu.ycp.cs320.tjones50.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	
private Department dept;
private Course course;
private ArrayList<Department> depts;

	public Data(){
		course = new Course();
		dept = new Department();
		depts = new ArrayList<Department>();
	}
	// Found online, parses from csv files
	public void populate() {
		
		//Input file which needs to be parsed
        String fileToParse = "All classes2.csv";
        BufferedReader fileReader = null;
         
        //Delimiter used in CSV file (this is what separates each piece of data)
        final String DELIMITER = ",";
        try
        {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));
             String currentDept = "";
             String previousDept = "";
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) 
            {
            	
                //Get all tokens available in line, token[0] is the general department, token[1] is the sub department and token[2] is the name of the course
                String[] tokens = line.split(DELIMITER);
                currentDept = tokens[1];
                if(!previousDept.equals(currentDept) && previousDept!=""){
                	depts.add(dept);
                	dept = new Department();
                	course = new Course();
                	//courses = new ArrayList<Course>();
                }
                course = new Course();
                dept.setName(tokens[1]);
                course.setDepartment(dept);
            	course.setName(tokens[2]);       	
            	dept.addCourse(course);
            
                previousDept = tokens[1];
            }
            depts.add(dept);
        }
        
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
		}


	public Department getDept(String dept) {
		for(int i = 0; i< this.depts.size(); i++){
			if(depts.get(i).getName().equals(dept)){
				return depts.get(i);
			}
		}
		return new Department("Error");
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public ArrayList<Department> getDepts() {
		return depts;
	}


	public void setDepts(ArrayList<Department> depts) {
		this.depts = depts;
	}	
}
