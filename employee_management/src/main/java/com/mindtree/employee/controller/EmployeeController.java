package com.mindtree.employee.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.employee.exceptionHandler.EmployeeCustomException;
import com.mindtree.employee.exceptionHandler.Response;
import com.mindtree.employee.model.Employee;
import com.mindtree.employee.service.EmployeeService;

@RestController
@RequestMapping("/EmpMgt")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	/*
	 * Method to add the Employee into the database
	 * 
	 * Response:: 200 : If record successfully inserted 400 : If incorrect JSON
	 * format 400 : if server side exception
	 * 
	 */

	@RequestMapping(method = RequestMethod.POST, path = "/addEmp")
	public Response addEmployee(@RequestBody Employee emp) {
		Collection<Employee> employees = new LinkedList<Employee>();

		try {
			Employee createdEmployee = empService.addEmployee(emp);
			employees.add(createdEmployee);
			return new Response(200, "Success", "Employee data inserted successfully", employees);

		} 
		catch (Exception eObj) 
		{
			return new Response(400, "failure", "failed to add the data into the database", null);
		}
	}

	/*
	 * Method to delete the Employee from the database based on emp id
	 * 
	 * Response:: 200 : If record successfully deleted 400 : If failed to delete
	 * the employee
	 * 
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteEmp/{empId}")
	public Response deleteEmployee(@PathVariable("empId") long empId) {

		try {
			empService.deleteById(empId);
			return new Response(200, "Success", "Employee data successfully deleted", null);
		} catch (EmptyResultDataAccessException e) {
			return new Response(400, "failure", "Employee data not present with id ::" + empId, null);
		}
	}

	/*
	 * Method to fetch all the Emp from the database
	 * 
	 * Response:: 200 : If record successfully fetched 400 : If failed to get
	 * the record
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/getAllEmpDetails")
	public Response getAllEmp() {
		try
		{
			Collection<Employee> emp = empService.getAllEmp();
			
			if (!emp.isEmpty())
			{
				return new Response(200, "Success", "", emp);
			}
			else
			{
				return new Response(200, "Success", "no record present in database", null);
			}
		}
		catch (Exception e)
		{
			return new Response(400, "Failure", "Exception while fetching recore from db",null);
		}
	}

	/*
	 * Method to fetch the Emp from the database based on empid
	 * 
	 * Response:: 200 : If record successfully fetched 400 : If failed to get
	 * the record
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/getByEmpId/{empId}")
	public Response getEmpById(@PathVariable("empId") Long empId) {

		Collection<Employee> employees = new LinkedList<Employee>();
		try
		{
			Employee employee = empService.getEmpById(empId);
			if (employee == null) {
				return new Response(200, "Success", "failed to get the Employee from database with id " + empId, employees);
			}
			employees.add(employee);
			return new Response(200, "Success", "", employees);
		}
		catch(Exception e)
		{
			return new Response(400, "Failure", "Exception while fetching record from db",null);
		}
	}

	/*
	 * Method to validate the Emp username and password
	 * 
	 * Response:: 200 : If Requested username is not present in database 200 :
	 * If Employee has authenticated successfully 200 : If Invalid Username and
	 * Password 400 : Failed to fetch the details from DB
	 * 
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response checkLogin(@RequestBody Employee employee) {
		Employee emp = null;
		
		try
		{
			 emp = empService.checkLogin(employee);

			 if (emp == null)
			 {
				 return new Response(200, "Success", "Requested username/password is not present in database", null);
			 }
			 else 
			 { 
				return new Response(200, "Success", "Employee has authenticated successfully", null);
		   	 }
		}
		catch(Exception e)
		{
			return new Response(400, "Failure", "Exception while fetching recore from db",null);
		}
	}
}
