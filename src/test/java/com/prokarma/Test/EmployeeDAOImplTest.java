package com.prokarma.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.configuration.EmpRowMapper;
import com.prokarma.csv.dao.EmployeeDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDAOImplTest {
	List<Employee> employees = Arrays.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	private String filePath="src//main//resources//file//Employee.csv";
	
	@InjectMocks
	private EmployeeDAOImpl employeeDAOImpl;
	
	@Mock
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllEmpoyees(){
		
		when(jdbcTemplate.query(anyString(), any(EmpRowMapper.class))).thenReturn(employees);
		List<Employee> result = employeeDAOImpl.getEmployees();
		assertEquals(1, result.size());
	}
	
	@Test
	public void testGetAllEmpoyeesForNullCheck(){
		
		when(jdbcTemplate.query(anyString(), any(EmpRowMapper.class))).thenReturn(null);
		List<Employee> result = employeeDAOImpl.getEmployees();
		assertEquals(null, result);
	}
	
	@Test
	public void testSaveData(){
		int[]  returnValue = {1,2,3};
		
		List<Map<String, Object>> batchValues = new ArrayList<>(employees.size());
		when(jdbcTemplate.batchUpdate(anyString(), (Map<String, Object>[])any())).thenReturn(returnValue);
		int result = employeeDAOImpl.saveData(employees,filePath);
		assertEquals(1, result);
	}
	
	
	@Test
	public void testSaveDataForException(){
		int[]  returnValue = {1,2,3};
		
		List<Map<String, Object>> batchValues = new ArrayList<>(employees.size());
		when(jdbcTemplate.batchUpdate(anyString(), (Map<String, Object>[])any())).thenThrow(NullPointerException.class);
		int result = employeeDAOImpl.saveData(null,null);
		assertEquals(0, result);
	}
	
}
