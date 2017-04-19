package edu.ycp.cs320.tjones50.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DataTest {
	private Data data;
	
	@Before
	public void setUp() {
		data = new Data();
		data.populate();
		//System.out.println(data.getDepts().get(0).getCourses().get(0).getName());
	}
	
	@Test
	public void testPopulate(){
		
		assertEquals("Anthropology", data.getDepts().get(0).getName());
		assertEquals("Behavioral Sciences", data.getDepts().get(1).getName());
		assertEquals("Criminal Justice", data.getDepts().get(2).getName());
		assertEquals("Mathematics", data.getDepts().get(data.getDepts().size()-3).getName());
		assertEquals("Physical Science", data.getDepts().get(data.getDepts().size()-2).getName());
		assertEquals("Physics", data.getDepts().get(data.getDepts().size()-1).getName());
	}
	
	@Test
	public void testGetAndSetDept(){
		Department department = data.getDept("Mathematics");
		assertEquals(department, data.getDept(department.getName()));
		Department department2 = new Department("Wizardry");
		assertEquals("Error", data.getDept(department2.getName()).getName());
	}
	
	@Test
	public void testGetAndSetDepts(){
		assertEquals("Anthropology", data.getDepts().get(0).getName());
		assertEquals("Behavioral Sciences", data.getDepts().get(1).getName());
		assertEquals("Criminal Justice", data.getDepts().get(2).getName());
		assertEquals("Mathematics", data.getDepts().get(data.getDepts().size()-3).getName());
		assertEquals("Physical Science", data.getDepts().get(data.getDepts().size()-2).getName());
		assertEquals("Physics", data.getDepts().get(data.getDepts().size()-1).getName());
		ArrayList<Department> depts = new ArrayList<Department>();
		data.setDepts(depts);
		assertEquals(depts, data.getDepts());
	}
	
	@Test 
	public void testGetAndSetCourse(){	

		assertEquals("ANT 210 Introduction to Physical Anthropology", data.getDepts().get(0).getCourses().get(0).getName());
		assertEquals("ANT 499 Independent Study", data.getDepts().get(0).getCourses().get(data.getDepts().get(0).getCourses().size()-1).getName());
		assertEquals("BEH 260 Statistics for the Behavioral Sciences", data.getDepts().get(1).getCourses().get(0).getName());
		assertEquals("BEH 499 Independent Study", data.getDepts().get(1).getCourses().get(data.getDepts().get(1).getCourses().size()-1).getName());
		assertEquals("PSC 152 Concepts in Physics in Everyday Life", data.getDepts().get(data.getDepts().size()-2).getCourses().get(0).getName());
		assertEquals("PSC 482 Independent Study", data.getDepts().get(data.getDepts().size()-2).getCourses().get(data.getDepts().get(data.getDepts().size()-2).getCourses().size()-1).getName());
		assertEquals("PHY 110 General Physics I", data.getDepts().get(data.getDepts().size()-1).getCourses().get(0).getName());
		assertEquals("PHY 263 Engineering Physics Optics and Modern Physics", data.getDepts().get(data.getDepts().size()-1).getCourses().get(data.getDepts().get(data.getDepts().size()-1).getCourses().size()-1).getName());
		
		Course course =  data.getDepts().get(0).getCourses().get(0);
		assertEquals(course, data.getCourse(course));
	}
	
	@Test 
	public void testGetAndSetCourses(){	

		assertEquals("ANT 210 Introduction to Physical Anthropology", data.getCourses().get(0).getName());
		
		ArrayList<Course> courses =  new ArrayList<Course>();
		data.setCourses(courses);
		assertEquals(courses, data.getCourses());
	}
}
	
	