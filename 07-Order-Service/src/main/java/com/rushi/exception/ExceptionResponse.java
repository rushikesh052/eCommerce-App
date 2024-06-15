package com.rushi.exception;

public class ExceptionResponse {
	
	private String errMsg;
	private String errCode;
	
	
	public ExceptionResponse() {
		
	}
	
	public ExceptionResponse(String errMsg, String errCode) {
		super();
		this.errMsg = errMsg;
		this.errCode = errCode;
	}
	
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	

}
