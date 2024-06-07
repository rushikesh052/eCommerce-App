package com.rushi.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rushi.constants.AppConstants;
import com.rushi.entity.User;
import com.rushi.props.AppProperties;
import com.rushi.response.ApiResponse;
import com.rushi.service.UserService;


@RestController
public class UserRestController {
	
	private Logger log= LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties appProps;
	
	

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestParam("user") String userJson,@RequestParam("file") MultipartFile file)throws Exception{
		
		log.info("User registration process started");
		
		ApiResponse<User> response=new ApiResponse<>();
		Map<String,String> messages=appProps.getMessages();
		
		ObjectMapper mapper=new ObjectMapper();
		User user = mapper.readValue(userJson,User.class);
		User addedUser=userService.addUser(user, file);
		
		if(addedUser==null) {
			response.setStatus(201);
			response.setMessage(messages.get(AppConstants.USER_REG));
			response.setData(addedUser);
		}else {
			log.info("user already present with the Email"+ user.getEmail());
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.USER_REG_ERROR));
		}
		log.info("user registration process completed");
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> login(@RequestBody User user){
		
		log.info("user login process started");
		
		ApiResponse<User> response=new ApiResponse<>();
		Map<String,String> messages=appProps.getMessages();
		
		User login = userService.login(user);
		if(login !=null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.LOGIN));
			response.setData(login);
		}else {
			log.error("user login failed");
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.LOGIN_ERR));
		}
		
		log.info("User login process completed");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
	}
	
	
}
