package com.rushi.exception;

public class ProductServiceException extends RuntimeException{

	private String errorCode;
	
	public ProductServiceException(String msg, String  errorCode) {
		super(msg);
		this.errorCode=errorCode;
		
		
	}
}
