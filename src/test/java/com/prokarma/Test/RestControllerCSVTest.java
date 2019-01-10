/*package com.prokarma.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.prokarma.csv.CsvApplication;
import com.prokarma.csv.beans.Employee;
import com.prokarma.csv.controller.CreateCSVFile;
import com.prokarma.csv.controller.ReadCSV;
import com.prokarma.csv.controller.RestControllerCSV;
import com.prokarma.csv.service.EmployeeService;

@ContextConfiguration(classes = {CsvApplication.class, RestControllerCSV.class })
public class RestControllerCSVTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	List<Employee> employees = Arrays.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	
	
	private MockMvc mockMvc;

	@InjectMocks
	private RestControllerCSV controller;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private CreateCSVFile createCSVFile;

	@Mock
	private ReadCSV readCSV;


	@Value("#{myProps['file.path']}")
	private String filePath;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "filePath", "src//main//resources//file//Employee.csv");
	}

	@Test
	public void testAllemployees() throws Exception {
		when(employeeService.getAllEmpoyees()).thenReturn(employees);
		mockMvc.perform(get("/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testAllemployeesNullChecking() throws Exception {
		when(employeeService.getAllEmpoyees()).thenReturn(null);
		mockMvc.perform(get("/all").accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(IsEmptyString.isEmptyOrNullString()));
	}

	@Test
	public void testCreateCSVFile() throws Exception {
		when(createCSVFile.createCSVFile(anyString(), any(List.class))).thenReturn(Boolean.TRUE);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(employees);
		this.mockMvc.perform(post("/create").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk())
		.andExpect(content().string("Success in Creating CSV file"));
	}


	@Test
	public void testReadCsvData() throws Exception {
		when(readCSV.readingCSVData(anyString())).thenReturn(employees);
		when(employeeService.saveCsvEmployeeData(any(List.class), anyString())).thenReturn(1);
		mockMvc.perform(get("/readCsv").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

}
*/