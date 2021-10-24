package com.salonspa.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.salonspa.example.entity.ExceptionResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ExceptionResponse> ShopHandler(CommonException ex){
		ExceptionResponse resp = 
				new ExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	
}
