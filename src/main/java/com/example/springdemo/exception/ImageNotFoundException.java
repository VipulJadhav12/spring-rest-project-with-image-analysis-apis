package com.example.springdemo.exception;

public class ImageNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImageNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImageNotFoundException(String message) {
		super(message);
	}

	public ImageNotFoundException(Throwable cause) {
		super(cause);
	}

}
