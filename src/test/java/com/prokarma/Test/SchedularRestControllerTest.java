package com.prokarma.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.text.IsEmptyString;
import org.junit.Before;
import org.junit.Test;
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
import com.prokarma.csv.configuration.FileHandler;
import com.prokarma.csv.controller.SchedularRestController;
import com.prokarma.csv.exception.RestExceptionHandler;
import com.prokarma.csv.service.EmployeeService;

@ContextConfiguration(classes = {CsvApplication.class, SchedularRestController.class})
public class SchedularRestControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	List<Employee> employees = Arrays.asList(new Employee(1, "Mr", "Niranjan", "Devuni", "", 1100, "Male", "Uppal", "HYD", true));
	
	
	private MockMvc mockMvc;

	@InjectMocks
	private SchedularRestController controller;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private FileHandler fileHandler;

	@Value("#{myProps['file.path']}")
	private String filePath;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		ReflectionTestUtils.setField(controller, "filePath", "src//main//resources//file//Employee.csv");
	}

	@Test
	public void testGetEmployees() throws Exception {
		when(employeeService.getEmployees()).thenReturn(employees);
		mockMvc.perform(get("/csv/employees").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testGetEmployeesForNullCheck() throws Exception {
		when(employeeService.getEmployees()).thenReturn(null);
		mockMvc.perform(get("/csv/employees").accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(IsEmptyString.isEmptyOrNullString()));
	}

	
	@Test
	public void testCreateCSVFile() throws Exception {
		when(fileHandler.createCsv(anyString(), any(List.class))).thenReturn(Boolean.TRUE);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(employees);
		this.mockMvc.perform(post("/csv/create_file").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk())
		.andExpect(content().string("Success in Creating CSV file"));
	}

	@Test
	public void testCreateCSVFileForNullCheck() throws Exception {
		when(fileHandler.createCsv(anyString(), any(List.class))).thenReturn(Boolean.FALSE);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(employees);
		this.mockMvc.perform(post("/csv/create_file").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().is5xxServerError())
		.andExpect(content().string("Failed in Creating CSV file"));
	}

	@Test
	public void testSaveCsvData() throws Exception {
		when(fileHandler.readCsv(anyString())).thenReturn(employees);
		when(employeeService.saveData(any(List.class), anyString())).thenReturn(1);
		mockMvc.perform(get("/csv/read_save_file").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void testSaveCsvDataForNullCheck() throws Exception {
		when(fileHandler.readCsv(anyString())).thenReturn(employees);
		when(employeeService.saveData(null, null)).thenReturn(0);
		mockMvc.perform(get("/csv/read_save_file").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void testSaveCsvDataForException() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new RestExceptionHandler())
	            .build();
		when(fileHandler.readCsv(anyString())).thenThrow(new RuntimeException());
		mockMvc.perform(get("/csv/read_save_file")).andExpect(status().is(500));
				
	}
}
