package com.sudip.users.controller;

import javax.validation.Valid;

import org.apache.http.protocol.ResponseServer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudip.users.dto.UserDto;
import com.sudip.users.service.UsersService;
import com.sudip.users.ui.model.CreateUserRequestModel;
import com.sudip.users.ui.model.CreateUserResponseModel;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/status/check")
	public String userServiceStatus()
	{
		return "Working on port :: " + env.getProperty("local.server.port");
	}
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = usersService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

}
