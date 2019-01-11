package com.prokarma.csv.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.prokarma.csv.beans.Employee;

public class EmpRowMapper implements RowMapper<Employee>
{
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setEmpId(rs.getInt("EMPID"));
		emp.setFirstName(rs.getString("FIRSTNAME"));
		emp.setLastName(rs.getString("LASTNAME"));
		emp.setMiddleName(rs.getString("MIDDLENAME"));
		emp.setSalary(rs.getDouble("SALARY"));
		emp.setGender(rs.getString("GENDER"));
		emp.setStreet(rs.getString("STREET"));
		emp.setCity(rs.getString("CITY"));
		emp.setActive(rs.getBoolean("ACTIVE"));
		return emp;
	}
}