package com.prokarma.csv.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.configuration.FileHandler;
import com.prokarma.csv.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Running Schedular operations for Employees using CSV file"})
@RestController
@RequestMapping(path = "/csv")
public class SchedularRestController {

	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	private FileHandler fileHandler;

	@Value("${file.path}")
	private String filePath;

	@Inject
	private EmployeeService employeeService;


    @ApiOperation(value = "View a list of available employees", response = List.class)
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployees(), HttpStatus.OK);
	}

    @ApiOperation(value = "Create CSV file operation", response = String.class)
	@PostMapping("/create_file")
	public ResponseEntity<String> createCSVFile(@RequestBody List<Employee> employees) {
		if (fileHandler.createCsv(filePath, employees))
			return new ResponseEntity("Success in Creating CSV file", HttpStatus.OK);
		else 
			return new ResponseEntity("Failed in Creating CSV file", HttpStatus.INTERNAL_SERVER_ERROR);
	}

    @Scheduled(fixedRate = 10000)
    @ApiOperation(value = "Read and Save CSV file Opeation On DB ", response = List.class)
	@GetMapping("/read_save_file")
	public ResponseEntity<List<Employee>> saveCsvData() {
		List<Employee> employees = fileHandler.readCsv(filePath);
		if(employees !=null && employees.size()>0) {
			employeeService.saveData(employees, filePath);
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
