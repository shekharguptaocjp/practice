package com.mindtree.employee.exceptionHandler;

import java.util.Collection;
import org.springframework.stereotype.Component;
import com.mindtree.employee.model.Employee;

@Component
public class Response {
	private int statusCode;
	private String status;
	private String message;
	private Collection<Employee> emp;
	
	
	public Collection<Employee> getEmp() {
		return emp;
	}

	public Response()
	{
	}
	
	public void setEmp(Collection<Employee> emp) {
		this.emp = emp;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", status=" + status + ", message=" + message + ", emp=" + emp
				+ "]";
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Response(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response(int statusCode, String status, String message, Collection<Employee> emp) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.emp = emp;
	}

	



}