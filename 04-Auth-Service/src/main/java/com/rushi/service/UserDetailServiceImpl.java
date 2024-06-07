package com.rushi.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rushi.entity.User;
import com.rushi.repo.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPwd(),Collections.emptyList());
		
	}
	

}
