/*package com.prokarma.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.controller.CreateCSVFile;
import com.prokarma.csv.controller.ReadCSV;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreateAndReadingCSVDataTest {
	List<Employee> employees = Arrays.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	private String filePath="src//main//resources//file//Employee.csv";
	
	@InjectMocks
	private ReadCSV readCSV;
	
	@InjectMocks
	private CreateCSVFile createCSVFile;
	
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		createCSVFile.createCSVFile(filePath, employees);
	}
	
	
	@Test
	public void readCSVDataTest(){
		List<Employee> result = readCSV.readingCSVData(filePath);
		assertEquals(1, result.size());
	}
	
	
	
}
*/