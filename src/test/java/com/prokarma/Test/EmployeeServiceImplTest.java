package com.prokarma.Test;

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
	List<Employee> employees = Arrays
			.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeDAO employeeDAO;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	private String filePath = "src//main//resources//file//Employee.csv";

	@Test
	public void saveDataTest() {

		when(employeeDAO.saveData(employees, filePath)).thenReturn(1);
		int result = employeeServiceImpl.saveData(employees, filePath);
		assertEquals(1, result);
	}

	@Test
	public void saveDataForEmptyListTest() {

		when(employeeDAO.saveData(null, filePath)).thenReturn(0);
		int result = employeeServiceImpl.saveData(null, filePath);
		assertEquals(0, result);
	}

	@Test
	public void saveDataForEmptyFilePathTest() {

		when(employeeDAO.saveData(employees, "")).thenReturn(0);
		int result = employeeServiceImpl.saveData(employees, "");
		assertEquals(0, result);
	}

	@Test
	public void getEmployeesTest() {
		when(employeeDAO.getEmployees()).thenReturn(employees);
		List<Employee> result = employeeServiceImpl.getEmployees();
		assertEquals(1, result.size());

	}

	@Test
	public void getAllEmpoyeesForNullCheckTest() {
		when(employeeDAO.getEmployees()).thenReturn(null);
		List<Employee> result = employeeServiceImpl.getEmployees();
		assertEquals(null, result);
	}
}
