package com.rushi.exception;

public class OrderServiceException extends RuntimeException{
	
	private String errCode;
	
	OrderServiceException(String msg,String errCode){
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
