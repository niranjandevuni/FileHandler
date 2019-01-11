package com.prokarma.csv.configuration;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prokarma.csv.beans.Employee;
@RunWith(SpringJUnit4ClassRunner.class)
public class EmpRowMapperTest {
	
	
	@InjectMocks
	EmpRowMapper empRowMapper;
	
	@Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        
    }
	
	@Test
	public void testDataExists() throws Exception {
		Employee employee = new ResultSetExtractor<Employee>() {
	        public Employee extractData(final ResultSet rs) throws SQLException{
	            if (rs.next()) {
	            	Employee employee = new Employee(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),
	            			rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getBoolean(10));
	                return employee ;
	            } else
	                return null;
	        }
	    }.extractData(getMockResultSet());
	    assertEquals(employee.getEmpId(), 1);
	    assertEquals(employee.getFirstName(), "Niranjan");
	    
	}
	
	List<Employee> employees = Arrays
			.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	
	private ResultSet getMockResultSet() throws Exception {
		
	    ResultSet rs = Mockito.mock(ResultSet.class);
	    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
	    Mockito.when(rs.getInt(1)).thenReturn(1);
	    Mockito.when(rs.getString(2)).thenReturn("Mr");
	    Mockito.when(rs.getString(3)).thenReturn("Niranjan");
	    Mockito.when(rs.getString(4)).thenReturn("Devuni");
	    Mockito.when(rs.getString(5)).thenReturn("");
	    Mockito.when(rs.getDouble(6)).thenReturn(1100.00);
	    Mockito.when(rs.getString(7)).thenReturn("Male");
	    Mockito.when(rs.getString(8)).thenReturn("Uppal");
	    Mockito.when(rs.getString(9)).thenReturn("HYD");
	    Mockito.when(rs.getBoolean(10)).thenReturn(true);
	    return rs;
	}
	
	private ResultSet getEmptyMockResultSet() throws Exception {
	    ResultSet rs = Mockito.mock(ResultSet.class);
	    Mockito.when(rs.next()).thenReturn(false);
	    return rs;
	}
}
