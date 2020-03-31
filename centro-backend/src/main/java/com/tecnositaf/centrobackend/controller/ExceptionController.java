package com.tecnositaf.centrobackend.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.response.Response;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleConflictResourceNotFound(RuntimeException ex, HttpServletRequest request) {
		log.error("RESOURCE NOT FOUND EXCEPTION", ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Response(-1, Errors.FAILURE, request.getRequestURI()));
	}

	@ExceptionHandler(value = { FailureException.class })
	protected ResponseEntity<Object> handleConflictFailure(FailureException ex, HttpServletRequest request) {
		log.error("FAILURE EXCEPTION", ex);
		return ResponseEntity.status(ex.getHttpStatus())
				.body(new Response(-1, ex.getErrorMessage(), request.getRequestURI()));
	}

	/*** caso pozzo ***/
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, HttpServletRequest request) {
		log.error("EXCEPTION", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Response(-1, Errors.FAILURE, request.getRequestURI()));
	}
}
