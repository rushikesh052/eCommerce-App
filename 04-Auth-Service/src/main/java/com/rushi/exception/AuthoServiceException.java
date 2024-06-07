package com.rushi.exception;

public class AuthoServiceException extends RuntimeException{
	
	private String errCode;
	
	AuthoServiceException(){

	}
	AuthoServiceException(String msg,String errCode){
		super(msg);
		this.errCode=errCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
