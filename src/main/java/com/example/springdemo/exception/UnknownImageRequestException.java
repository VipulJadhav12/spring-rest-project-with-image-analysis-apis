package com.example.springdemo.exception;

public class UnknownImageRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownImageRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownImageRequestException(String message) {
		super(message);
	}

	public UnknownImageRequestException(Throwable cause) {
		super(cause);
	}

}
