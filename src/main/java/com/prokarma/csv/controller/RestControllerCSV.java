package com.prokarma.csv.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.beans.FileHandler;
import com.prokarma.csv.service.EmployeeService;

@RestController
@RequestMapping(path = "/csv")
public class RestControllerCSV {

	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	private FileHandler fileHandler;

	@Value("${file.path}")
	private String filePath;

	@Inject
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getAllEmpoyees(), HttpStatus.OK);
	}

	@PostMapping("/create_file")
	public ResponseEntity<String> createCSVFile(@RequestBody List<Employee> employees) {
		if (fileHandler.createCsv(filePath, employees))
			return new ResponseEntity("Success in Creating CSV file", HttpStatus.OK);
		else 
			return new ResponseEntity("Failed in Creating CSV file", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// @Scheduled(fixedRate = 10000)
	@GetMapping("/read_save_file")
	public ResponseEntity<List<Employee>> readCsvData() {
		List<Employee> employees = fileHandler.readCsv(filePath);
		employeeService.saveCsvEmployeeData(employees, filePath);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
}
