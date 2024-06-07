package com.rushi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value=ProductServiceException.class)
	public ResponseEntity<ErrorResponse> handlerProductServiceEx(ProductServiceException pse){
		ErrorResponse resp=new ErrorResponse();
		resp.setErrorCode(resp.getErrorCode());
		resp.setMessage(resp.getMessage());
		
		return new ResponseEntity<>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
