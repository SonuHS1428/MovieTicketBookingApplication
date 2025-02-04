package com.example.demo.movies.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.movies.exception.MovieNotFoundException;
 
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MovieNotFoundException.class)
	ResponseEntity<String> handleResourceNotFound(MovieNotFoundException ex){
		
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	
	}
 
}