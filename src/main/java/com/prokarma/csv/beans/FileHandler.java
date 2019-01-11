package com.prokarma.csv.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.prokarma.csv.configuration.Configuration;
import com.prokarma.csv.configuration.Constants;

@Component
public class FileHandler {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public boolean createCsv(String path, List<Employee> employees) {
		if (employees !=null && !employees.isEmpty() && employees.size() > 0) {
			try (FileWriter fileWriter = new FileWriter(path)) {
				fileWriter.append(Constants.CSV_HEADER.getStringValue());
				fileWriter.append('\n');
				for (Employee Employee : employees) {
					fileWriter.append(String.valueOf(Employee.getEmpId()));
					fileWriter.append(',');
					fileWriter.append(Employee.getPrefix());
					fileWriter.append(',');
					fileWriter.append(Employee.getFirstName());
					fileWriter.append(',');
					fileWriter.append(Employee.getLastName());
					fileWriter.append(',');
					fileWriter.append(Employee.getMiddleName());
					fileWriter.append(',');
					fileWriter.append(String.valueOf(Employee.getSalary()));
					fileWriter.append(',');
					fileWriter.append(Employee.getGender());
					fileWriter.append(',');
					fileWriter.append(Employee.getStreet());
					fileWriter.append(',');
					fileWriter.append(Employee.getCity());
					fileWriter.append(',');
					fileWriter.append(String.valueOf(Employee.getActive()));
					fileWriter.append('\n');
				}
				log.info("Write CSV successfully!");
				return true;
			} catch (IOException e) {
				log.error("Writing CSV error!" + e.getMessage());
			}
		}
		return false;
	}

	public List<Employee> readCsv(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			String line = "";
			List<Employee> employees = new ArrayList<Employee>();
			try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
				line = fileReader.readLine(); //for avoiding header
				while ((line = fileReader.readLine()) != null) {
						String[] tokens = line.split(",");
						if (tokens.length > 0) {
							
							Employee employee = new Employee(
									Integer.parseInt(Configuration.checkNullCondition(tokens[Constants.EMP_ID.getIntValue()])),
									Configuration.checkNullCondition(tokens[Constants.PREFIX.getIntValue()]),
									Configuration.checkNullCondition(tokens[Constants.FIRST_NAME.getIntValue()]),
									Configuration.checkNullCondition(tokens[Constants.LAST_NAME.getIntValue()]),
									Configuration.checkNullCondition(tokens[Constants.MIDDLE_NAME.getIntValue()]),
									Double.parseDouble(Configuration.checkNullCondition(tokens[Constants.SALARY.getIntValue()])),
									Configuration.checkNullCondition(tokens[Constants.GENDER.getIntValue()]),
									Configuration.checkNullCondition(tokens[Constants.STREET.getIntValue()]),
									Configuration.checkNullCondition(tokens[Constants.CITY.getIntValue()]),
									Boolean.parseBoolean(Configuration.checkNullCondition(tokens[Constants.ACTIVE.getIntValue()])));
							employees=null;
							employees.add(employee);
						}
				}
				return employees;
			} catch (IOException e) {
				System.out.println("Reading CSV Error!" + e.getMessage());
			}
			
		}
		return null;
	}
}
