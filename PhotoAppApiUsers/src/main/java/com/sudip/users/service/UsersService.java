package com.sudip.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sudip.users.dto.UserDto;

public interface UsersService extends UserDetailsService {
	
	UserDto createUser(UserDto userDetails);
	
	UserDto getUserDetailsByEmail(String email);

}
