package com.xpanxion.exceptions;

public class InvalidHubUrlException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidHubUrlException() {
		super("Invalid appium hub URL, please check capabilities / configuration");
	}
	public InvalidHubUrlException(String message) {
		super(message);
	}
	
}
