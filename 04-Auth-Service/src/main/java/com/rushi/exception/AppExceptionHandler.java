package com.rushi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value=AuthoServiceException.class)
	public ResponseEntity<ExceptionResponse> handleAuthServiceEx(AuthoServiceException ae){
		ExceptionResponse exres=new ExceptionResponse();
		exres.setErrMsg(ae.getMessage());
		exres.setErrCode(ae.getErrCode());
		return new ResponseEntity<>(exres,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
