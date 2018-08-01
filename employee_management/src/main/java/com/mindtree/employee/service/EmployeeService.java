package com.mindtree.employee.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.omg.PortableServer.ThreadPolicyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindtree.employee.exceptionHandler.EmployeeCustomException;
import com.mindtree.employee.exceptionHandler.Response;
import com.mindtree.employee.model.Employee;
import com.mindtree.employee.repository.EmployeeRepository;

@Service
@CacheConfig
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	Response response;
	
	
	@Transactional
	public Employee addEmployee(Employee emp)
	{
	//	try
		//{
			Employee employee = empRepository.save(emp);
			return employee;
	//	}
		//catch (Exception e)
		//{
			//throw new EmployeeCustomException("DB exception :: failed to save the record [message :: " +e.getMessage());
			
	//	}
			
	}

	@Transactional
	@Cacheable("employee.all")
	public List<Employee> getAllEmp()
	{
		//try
		//{
				return (List<Employee>) empRepository.findAll();	
	//	}
		//catch(Exception e)
		//{
			//throw new EmployeeCustomException("DB error :: failed to get the employee list {message ::" + e.getMessage());
	//	}
		
	}
	
	@Transactional
	public Employee getEmpById(Long id)
	{
		//try
		//{
			Optional<Employee> emp =  empRepository.findById(id);
			if(emp.isPresent())
			{
				return emp.get();
			}
	//	}
		//catch (Exception e)
		//{
			//throw new EmployeeCustomException("DB error :: failed to get the emp {message ::" + e.getMessage());
	//	}
		return null;
	}
	
	
	@Transactional
	public void deleteById(long empId)
	{
		   empRepository.deleteById(empId);
	}
	
	@Transactional
	public Employee checkLogin(Employee emp)
	{
		//try
		//{
		  Employee employee = empRepository.findEmployeeByUserNameAndPassword(emp.getUserName(), emp.getPassword());
		  return employee;
		//}
		//catch(Exception e)
		//{
			//throw new EmployeeCustomException("DB error :: failed to check the login details {message :: " + e.getMessage()); 
		//}
	}
	
}
