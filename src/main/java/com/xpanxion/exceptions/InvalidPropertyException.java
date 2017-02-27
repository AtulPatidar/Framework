package com.xpanxion.exceptions;

public class InvalidPropertyException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidPropertyException() {
		super("Invalid property name");
	}
	public InvalidPropertyException(String error) {
		super(error);
	}

}
