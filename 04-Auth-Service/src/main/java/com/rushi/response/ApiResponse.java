package com.rushi.response;

import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
public class ApiResponse<T> {

	
	private String message;
	private Integer status;
	private T data;
}
