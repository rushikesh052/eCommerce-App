package com.rushi.exception;

public class ExceptionResponse {
	
	private String errMessage;
	private String errCode;

	public ExceptionResponse() {
		
	}
	
	public ExceptionResponse(String errMessage, String errCode) {
		super();
		this.errMessage = errMessage;
		this.errCode = errCode;
	}


	public String getErrCode() {
		return errCode;
	}


	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}


	public String getErrMessage() {
		return errMessage;
	}

	
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
	

}
