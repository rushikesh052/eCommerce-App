package com.rushi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = ReportServiceException.class)
	public ResponseEntity<ExceptionResponse> handleAuthServiceEx(ReportServiceException  oe){
		ExceptionResponse exres=new ExceptionResponse();
		exres.setErrMessage(oe.getMessage());
		exres.setErrCode(oe.getErrCode());
		return new ResponseEntity<>(exres,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
