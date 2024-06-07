package com.rushi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rushi.entity.User;

public interface UserService{
	public User addUser(User user,MultipartFile file) throws Exception;
	
	public User login(User user);
	
	public User getUserById(Integer userId);
	
	public List<User> getAllUser();
	
	public User deleteUserById(Integer userId);
}
