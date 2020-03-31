package com.tecnositaf.centrobackend.exception;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.Errors;

public class FailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;

	public FailureException(Errors error) {
		super(error.description);
		this.errorCode = error.code;
		this.errorMessage = error.description;
		this.httpStatus = HttpStatus.CONFLICT;
	}

	public FailureException(Errors error, HttpStatus httpStatus) {
		super(error.description);
		this.errorCode = error.code;
		this.errorMessage = error.description;
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
