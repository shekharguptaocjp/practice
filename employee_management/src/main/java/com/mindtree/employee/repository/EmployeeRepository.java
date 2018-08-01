package com.mindtree.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.employee.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>  {

	Employee findEmployeeByUserNameAndPassword(String userName, String password);
	
}
