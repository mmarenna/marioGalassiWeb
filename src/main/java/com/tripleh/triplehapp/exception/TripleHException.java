package com.tripleh.triplehapp.exception;

import org.springframework.http.HttpStatus;

public class TripleHException extends Exception {
	
	private HttpStatus httpStatus;
	
	public static final long serialVersionUID= 1L;
	

	public TripleHException(String message, Throwable e, HttpStatus httpStatus) {
		super(message,e);
		this.httpStatus= httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	

}


