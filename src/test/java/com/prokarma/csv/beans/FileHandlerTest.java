package com.prokarma.csv.beans;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.csv.configuration.FileHandler;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileHandlerTest {

	List<Employee> employees = Arrays
			.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	private String filePath = "src//main//resources//file//Employee.csv";

	@InjectMocks
	FileHandler fileHandler;

	@Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testCreateCsv() {
		assertEquals(true, fileHandler.createCsv(filePath, employees));

	}

	@Test
	public void testCreateCsvForNullCheck() {
		assertEquals(false, fileHandler.createCsv(filePath, null));

	}
	
	@Test
	public void testReadCsv() {
		assertEquals(employees.size(), fileHandler.readCsv(filePath).size());
	}

}
