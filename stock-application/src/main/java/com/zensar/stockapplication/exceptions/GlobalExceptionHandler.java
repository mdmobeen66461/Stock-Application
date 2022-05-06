package com.zensar.stockapplication.exceptions;

import java.time.LocalTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(InvalidStockIdException.class)
	public ResponseEntity<CustomErrorResponse> handleStockError(HttpServletResponse response){
		
		CustomErrorResponse customErrorResponse=new CustomErrorResponse();
		customErrorResponse.setError("Invalid Id");
		customErrorResponse.setLocalTime(LocalTime.now());
		
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse,HttpStatus.BAD_REQUEST);
	}

}
