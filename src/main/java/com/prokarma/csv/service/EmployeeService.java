package com.prokarma.csv.service;

import java.util.List;

import com.prokarma.csv.beans.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmpoyees();

	public int saveCsvEmployeeData(List<Employee> employees, String filePath);
}
