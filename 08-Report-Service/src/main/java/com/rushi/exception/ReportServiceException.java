package com.rushi.exception;


public class ReportServiceException extends RuntimeException{
	
	private String errCode;
	
	ReportServiceException(){
		
	}
	ReportServiceException(String msg, String errCode){
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
