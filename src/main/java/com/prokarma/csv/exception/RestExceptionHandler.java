package com.prokarma.csv.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Exception occured")
	@ExceptionHandler(Exception.class)
	public void handleAllExceptions(Exception ex, WebRequest request) {
		new ExceptionDeatils(new Date(), ex.getMessage(), request.getDescription(false));
	}
}