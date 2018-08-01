package com.mindtree.employee.exceptionHandler;

public class EmployeeCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeCustomException(String message) {
		super(message);
	}

	public EmployeeCustomException() {
		super();
	}
}