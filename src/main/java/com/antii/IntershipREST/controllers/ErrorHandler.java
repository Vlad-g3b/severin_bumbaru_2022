package com.antii.IntershipREST.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.antii.IntershipREST.helper.CustomException;

@ControllerAdvice
public class ErrorHandler {
	@ResponseBody
	  @ExceptionHandler(CustomException.class)
	  String employeeNotFoundHandler(CustomException ex) {
	    return ex.getMessage();
	  }
}
