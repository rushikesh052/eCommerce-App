package com.rushi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	
	@ExceptionHandler(value = CartServiceException.class)
	public ResponseEntity<ExceptionResponse> handleAuthServiceEx(CartServiceException ce){
		ExceptionResponse exres=new ExceptionResponse();
		exres.setErrMsg(ce.getMessage());
		exres.setErrCode(ce.getErrCode());
		return new ResponseEntity<>(exres,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
