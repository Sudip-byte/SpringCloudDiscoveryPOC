package com.sudip.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sudip.users.data.UserEntity;
import com.sudip.users.data.UsersRepository;
import com.sudip.users.dto.UserDto;

@Service
public class UserServiceImpl implements UsersService{
	
	UsersRepository usersRepository;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UsersRepository usersReportRepository,BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.usersRepository = usersReportRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		usersRepository.save(userEntity);
		
		
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	
	

}
