package com.example.demo.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<String> handleResourceNotFound(UserNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	
	}

}
