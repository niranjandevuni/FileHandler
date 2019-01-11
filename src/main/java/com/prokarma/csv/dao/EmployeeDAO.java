package com.prokarma.csv.dao;

import java.util.List;

import com.prokarma.csv.beans.Employee;

public interface EmployeeDAO {
	public List<Employee> getEmployees();
	public int saveData(List<Employee> employees, String filePath);
}
