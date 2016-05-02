package com.coderwurst.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		ex.printStackTrace();
		return "errorpage";		// user sent to error page	
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(DataAccessException ex) {
		ex.printStackTrace();
		return "denied";		// user sent to denied page	
	}

}
