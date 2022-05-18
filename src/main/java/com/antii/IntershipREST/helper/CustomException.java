package com.antii.IntershipREST.helper;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1745271145537270217L;
	
	public CustomException() {
		super();
	}
	public CustomException(String cod, String message) {
		super(message);
	}
	
}
