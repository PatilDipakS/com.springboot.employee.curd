package com.blogApplication.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogApplication.api.payloads.ApiResponse;

@RestControllerAdvice
public class GobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> gobalExcepationHandler(ResourceNotFoundException ex) {

		String massage = ex.getMessage();
		ApiResponse apiRes = new ApiResponse(massage, false);
		return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.NOT_FOUND);

	}

}
