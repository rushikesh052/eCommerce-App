package com.rushi.exception;

import org.hibernate.query.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = OrderServiceException.class)
	public ResponseEntity<ExceptionResponse> handleAuthServiceEx(OrderServiceException  oe){
		ExceptionResponse exres=new ExceptionResponse();
		exres.setErrMsg(oe.getMessage());
		exres.setErrCode(oe.getErrCode());
		return new ResponseEntity<>(exres,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
