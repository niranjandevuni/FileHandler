/*package com.prokarma.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.dao.EmployeeDAO;
import com.prokarma.csv.service.EmployeeServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTest {
	List<Employee> employees = Arrays.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	
	@Mock
	private EmployeeDAO employeeDAO;
	
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	private String filePath="src//main//resources//file//Employee.csv";
	
	@Test
	public void saveCsvEmployeeDataForSaving(){
		
		when(employeeDAO.saveCsvEmployeeData(employees,filePath)).thenReturn(1);
		int result = employeeServiceImpl.saveCsvEmployeeData(employees, filePath);
		assertEquals(1, result);
		
	}
	
	
	@Test
	public void saveCsvEmployeeDataForEmptyList(){
		
		when(employeeDAO.saveCsvEmployeeData(null,filePath)).thenReturn(0);
		int result = employeeServiceImpl.saveCsvEmployeeData(null,filePath);
		assertEquals(0, result);
	}
	
	@Test
	public void saveCsvEmployeeDataForEmptyFilePath(){
		
		when(employeeDAO.saveCsvEmployeeData(employees,"")).thenReturn(0);
		int result = employeeServiceImpl.saveCsvEmployeeData(employees,"");
		assertEquals(0, result);
	}
	
	@Test
	public void getAllEmpoyeesTest(){
		when(employeeDAO.getAllEmpoyees()).thenReturn(employees);
		List<Employee> result = employeeServiceImpl.getAllEmpoyees();
		assertEquals(1, result.size());
		
	}
	
	@Test
	public void getAllEmpoyeesNull(){
		when(employeeDAO.getAllEmpoyees()).thenReturn(null);
		List<Employee> result = employeeServiceImpl.getAllEmpoyees();
		assertEquals(null, result);
	}
}
*/