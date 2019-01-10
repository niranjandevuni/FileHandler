package com.prokarma.csv.dao;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prokarma.csv.beans.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	NamedParameterJdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmpoyees() {
		return jdbcTemplate.query("select * from employee",(ResultSet rs, int rowNum) -> {
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
		});
	}
	
	@Override
	public int saveCsvEmployeeData(List<Employee> employees, String filePath) {
		try {
			if (!employees.isEmpty() && employees.size() > 0 && filePath.length()>0) {
				
				String sql = "insert into employee (empid,prefix,firstName,lastName,middleName,salary,gender,street,city,active)"
						+ "values(:empid,:prefix,:firstName,:lastName,:middleName,:salary,:gender,:street,:city,:active)";
				
				List<Map<String, Object>> batchValues = new ArrayList<>(employees.size());
 
				for (Employee employee : employees) {
				    batchValues.add(new MapSqlParameterSource("empid", employee.getEmpId())
				                    .addValue("prefix", employee.getPrefix())
				                    .addValue("firstName", employee.getFirstName())
				                    .addValue("lastName", employee.getLastName())
				                    .addValue("middleName", employee.getMiddleName())
				                    .addValue("salary", employee.getSalary())
				                    .addValue("gender", employee.getGender())
				                    .addValue("street", employee.getStreet())
				                    .addValue("city", employee.getCity())
				                    .addValue("active", employee.getActive())
				                    .getValues());
				}
			
					int[] updateCounts = jdbcTemplate.batchUpdate(sql,batchValues.toArray(new Map[employees.size()]));	
					log.info(" saved data in DB "+updateCounts);
				
				File file = new File(filePath);
				if (file.delete()) {
					log.info(file.getName() + " is deleted!");
				} else {
					log.info("Delete operation is failed.");
				}
				return 1;
			}
		} catch (Exception e) {
			log.error("error ==>" + e.getMessage());
		}
		return 0;
	}

}
