package com.prokarma.csv.service;

import java.util.List;

import com.prokarma.csv.beans.Employee;

public interface EmployeeService {
	public List<Employee> getEmployees();

	public int saveData(List<Employee> employees, String filePath);
}
