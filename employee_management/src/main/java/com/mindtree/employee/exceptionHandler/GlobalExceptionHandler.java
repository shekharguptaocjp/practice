package com.mindtree.employee.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EmployeeCustomException.class)
	public ResponseEntity<Response> customException(String  message) {
		return new ResponseEntity<>(new Response(404, "failed", message, null), HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Response> incorrectJSONFormat() {
		return new ResponseEntity<>(new Response(400, "failed", "Incorrect JSON format", null), HttpStatus.BAD_REQUEST);
	}

}
