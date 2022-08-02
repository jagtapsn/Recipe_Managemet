package com.recipe.springbootrecipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.recipe.springbootrecipeapp.dto.response.ErrorResponse;

@ControllerAdvice

public class RestExceptionHandler {
	@ExceptionHandler(RecordNotFoundException.class) // handles specific business exception
	protected ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
