package com.mindtree.employee.testService;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.employee.model.Employee;
import com.mindtree.employee.repository.EmployeeRepository;
import com.mindtree.employee.service.EmployeeService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiceTest  {
   
	

	EmployeeRepository empRepo;
	
	@Autowired
	EmployeeService empService;

	
	@Test
	public void validateGetAllEmp()
	{
		 List<Employee> emp =empService.getAllEmp();
		System.out.println(emp.size());
		Assert.assertEquals(emp.size(), 1);	
	}
	
	@Test
	public void validateGetEmpById()
	{
		 Employee emp =empService.getEmpById(1046458L);
		Assert.assertEquals(emp.getEmpId(), 1046458L);	
	}
	
	
	@Test
	public void validateGetEmpByIdReturnNull()
	{
		 Employee emp =empService.getEmpById(101L );
		Assert.assertNull(emp);
	}
	
	
	@Test
	@Rollback
	public void validateAddEmployee()
	{
		Employee emp = new Employee(101, "abc", "12345", "shekhar", "shekhar.gupta@gmail.com", "06/04/1992", "Male", "abs", "qwe");
		
		 Employee result =empService.addEmployee(emp);
		Assert.assertEquals(result.getFullName(), "shekhar");	
	}
	
	@Test
	@Rollback
	public void validateDeleteById()
	{
		Employee emp = new Employee(101, "abc", "12345", "shekhar", "shekhar.gupta@gmail.com", "06/04/1992", "Male", "abs", "qwe");
		
		 empService.deleteById(1046458L);
		Assert.assertEquals(true, true);	
	}
	
	@Test
	@Rollback
	public void validateCheckLogin()
	{
		Employee emp = new Employee(1046458, "ShekharG", "abcd", "shekhar", "shekhar.gupta@gmail.com", "06/04/1992", "Male", "abs", "qwe");
		
		Employee result  = empService.checkLogin(emp);

		Assert.assertEquals(result.getEmpId(), emp.getEmpId());	
	}
	
	
}
