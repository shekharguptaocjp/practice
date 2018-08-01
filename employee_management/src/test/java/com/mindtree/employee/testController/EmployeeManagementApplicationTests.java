package com.mindtree.employee.testController;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.StyleConstants.ColorConstants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.employee.controller.EmployeeController;
import com.mindtree.employee.exceptionHandler.EmployeeCustomException;
import com.mindtree.employee.model.Employee;
import com.mindtree.employee.service.EmployeeService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeManagementApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks
	EmployeeController mockEmpController;

	@Mock
	EmployeeService mockEmpService;
	Employee empRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(mockEmpController).build();

	}

	// =========================================== Get All Users

	@Test
	public void validateGetAllEmpMethod() throws Exception {
		List<Employee> emp = new LinkedList<Employee>();
		emp.add(new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com", "06/04/1992",
				"Male", "Birth Place", "Bhopal"));

		String result= "{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"\",\"emp\": [   {\"empId\": 1046458,\"userName\": \"ShekharG\",\"password\": \"abcd\",\"fullName\": \"Shekhar Gupta\",\"emailId\": \"shekhar.gupta@mindtree.com\",\"dateOfBirth\": \"06/04/1992\",\"gender\": \"Male\",\"securityQuestion\": \"Birth Place\",\"securityAnswer\": \"Bhopal\" }]}";
		when(mockEmpService.getAllEmp()).thenReturn(emp);

		mockMvc.perform(get("/EmpMgt/getAllEmpDetails")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result));

		
	}
	
	// =========================================== Exception Get All Users

		@Test
		public void validateGetAllEmpMethodException() throws Exception {
			List<Employee> emp = new LinkedList<Employee>();
		//	emp.add(new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com", "06/04/1992",
			//		"Male", "Birth Place", "Bhopal"));
			when(mockEmpService.getAllEmp()).thenReturn(emp);
			String result= "{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"no record present in database\",\"emp\":null}";

			mockMvc.perform(get("/EmpMgt/getAllEmpDetails")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(status().is2xxSuccessful())
					.andExpect(content().json(result));

			
		}

		
		// =========================================== Exception Get All Users

				@Test
				public void validateGetAllEmpMethodException2() throws Exception {
					List<Employee> emp = new LinkedList<Employee>();
				//	emp.add(new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com", "06/04/1992",
					//		"Male", "Birth Place", "Bhopal"));
					when(mockEmpService.getAllEmp()).thenThrow(EmployeeCustomException.class);
					String result= "{\"statusCode\": 400,\"status\": \"Failure\",\"message\": \"Exception while fetching recore from db\",\"emp\":null}";

					mockMvc.perform(get("/EmpMgt/getAllEmpDetails")).andExpect(status().isOk())
							.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
							.andExpect(status().is2xxSuccessful())
							.andExpect(content().json(result));

					
				}

	// =========================================== Get User by id
	@Test
	public void verifyGetEmpbyId() throws Exception {
	
		Employee emp = new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com",
				"06/04/1992", "Male", "Birth Place", "Bhopal");

		String result= "{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"\",\"emp\": [   {\"empId\": 1046458,\"userName\": \"ShekharG\",\"password\": \"abcd\",\"fullName\": \"Shekhar Gupta\",\"emailId\": \"shekhar.gupta@mindtree.com\",\"dateOfBirth\": \"06/04/1992\",\"gender\": \"Male\",\"securityQuestion\": \"Birth Place\",\"securityAnswer\": \"Bhopal\" }]}";
		when(mockEmpService.getEmpById(1L)).thenReturn(emp);
		mockMvc.perform(get("/EmpMgt/getByEmpId/{empId}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result));
	}
	// =========================================== Get User by id when it is not present

	@Test
	public void verifyGetEmployeeException() throws Exception {
	
		String result ="{\"statusCode\": 200,\"status\": \"Success\",\"message\":\"failed to get the Employee from database with id 1\",\"emp\":[]}";
	
		when(mockEmpService.getEmpById(Matchers.anyLong())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/{empId}", 1)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result));
	}
	
	// =========================================== Get User by id when it is not present

		@Test
		public void verifyGetEmployeeException1() throws Exception {
		
			String result ="{\"statusCode\": 400,\"status\": \"Failure\",\"message\":\"Exception while fetching record from db\",\"emp\":null}";
		
			when(mockEmpService.getEmpById(Matchers.anyLong())).thenThrow(EmployeeCustomException.class);
			mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getByEmpId/{empId}", 1)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
			//		.andExpect(status().is4xxClientError())
					.andExpect(content().json(result));
		}
	
	// =========================================== Method to test the add Employee into the database
	
	@Test
	public void verifyAddEmployee() throws Exception
	{
		Employee emp = new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com",
				"06/04/1992", "Male", "Birth Place", "Bhopal");
		String input ="{\"empId\": 1046458,\"userName\": \"ShekharG\",\"password\": \"abcd\",\"fullName\": \"Shekhar Gupta\",\"emailId\": \"shekhar.gupta@mindtree.com\",\"dateOfBirth\": \"06/04/1992\",\"gender\": \"Male\",\"securityQuestion\": \"Birth Place\",\"securityAnswer\": \"Bhopal\" }";
		String result ="{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"Employee data inserted successfully\",\"emp\": [   {\"empId\": 1046458,\"userName\": \"ShekharG\",\"password\": \"abcd\",\"fullName\": \"Shekhar Gupta\",\"emailId\": \"shekhar.gupta@mindtree.com\",\"dateOfBirth\": \"06/04/1992\",\"gender\": \"Male\",\"securityQuestion\": \"Birth Place\",\"securityAnswer\": \"Bhopal\"  }]}";
		when(mockEmpService.addEmployee(Matchers.any(Employee.class))).thenReturn(emp);
		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/addEmp") 
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(input)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result)); 
		
	}
	
	

	// =========================================== Exception :: Method to test the add Employee into the database
	
		@Test
		public void verifyAddEmployeeException() throws Exception
		{
			Employee emp = new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com",
					"06/04/1992", "Male", "Birth Place", "Bhopal");
			String input ="{\"empId\": 1046458,\"userName\": \"ShekharG\",\"password\": \"abcd\",\"fullName\": \"Shekhar Gupta\",\"emailId\": \"shekhar.gupta@mindtree.com\",\"dateOfBirth\": \"06/04/1992\",\"gender\": \"Male\",\"securityQuestion\": \"Birth Place\",\"securityAnswer\": \"Bhopal\" }";
			String result ="{\"statusCode\": 400,\"status\": \"failure\",\"message\": \"failed to add the data into the database\",\"emp\": null}";
			when(mockEmpService.addEmployee(Matchers.any(Employee.class))).thenThrow(EmployeeCustomException.class);
			mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/addEmp") 
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(input)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(content().json(result)); 
			
		}
	
	
	//===================== Method to test the deleteEmployee from the database based on emp id
	@Test 
	public void verifyDeleteEmployeeMethod() throws Exception
	{
		String result =	"{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"Employee data successfully deleted\",\"emp\": null}";
		Mockito.doNothing().when(mockEmpService).deleteById(Matchers.anyLong());
		mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/{empId}", 1)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result));
		 
	}
	
	//===================== Exception :: Method to test the deleteEmployee from the database based on emp id
	@Test
	public void verifyDeleteEmployeeMethodWhenException() throws Exception
	{
		String result =	"{\"statusCode\": 400,\"status\": \"failure\",\"message\": \"Employee data not present with id ::499\", \"emp\": null}";
		Mockito.doThrow(EmptyResultDataAccessException.class).when(mockEmpService).deleteById(Matchers.anyLong());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/{empId}", 499)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(content().json(result));
		
	}
	
	
	//=====================   Method to validate the Emp username and password
	
	@Test
	public void verifyCheckLoginMethod() throws Exception
	{
		
		Employee emp = new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com",
				"06/04/1992", "Male", "Birth Place", "Bhopal");
		String input ="{\"userName\": \"ShekharG\",\"password\": \"abcd\"}";
		String result ="{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"Employee has authenticated successfully\",\"emp\":null}";
		when(mockEmpService.checkLogin((Matchers.any(Employee.class)))).thenReturn(emp);
		mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/checkLogin") 
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(input)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json(result)); 	
	}
	
	
	//=====================If no record with reuested empName :: Method to validate the Emp username and password
	
		@Test
		public void verifyCheckLoginMethodException1() throws Exception
		{
			
			Employee emp = new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com",
					"06/04/1992", "Male", "Birth Place", "Bhopal");
			String input ="{\"userName\": \"ShekharG\",\"password\": \"abcd\"}";
			String result ="{\"statusCode\": 200,\"status\": \"Success\",\"message\": \"Requested username/password is not present in database\",\"emp\":null}";
			when(mockEmpService.checkLogin((Matchers.any(Employee.class)))).thenReturn(null);
			mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/checkLogin") 
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(input)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().is2xxSuccessful())
					.andExpect(content().json(result)); 	
		}
		
		//=====================If no record with reuested empName :: Method to validate the Emp username and password
		
			@Test
			public void verifyCheckLoginMethodException2() throws Exception
			{
				
				Employee emp = new Employee(1046458, "ShekharG", "abcd", "Shekhar Gupta", "shekhar.gupta@mindtree.com",
						"06/04/1992", "Male", "Birth Place", "Bhopal");
				String input ="{\"userName\": \"ShekharG\",\"password\": \"abcd\"}";
				String result ="{\"statusCode\": 400,\"status\": \"Failure\",\"message\": \"Exception while fetching recore from db\",\"emp\":null}";
				when(mockEmpService.checkLogin((Matchers.any(Employee.class)))).thenThrow(EmployeeCustomException.class);
				mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/checkLogin") 
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(input)
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is2xxSuccessful())
						.andExpect(content().json(result)); 	
			}
}
