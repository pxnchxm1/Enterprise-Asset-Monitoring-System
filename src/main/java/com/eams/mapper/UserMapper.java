package com.eams.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eams.DTO.UserDTO;
import com.eams.entity.User;
import com.eams.repository.UserRepository;

@Component
public class UserMapper {
	@Autowired
	private UserRepository userRepo;
	
	public UserDTO userToDto(User user) {
		User u = new User();
		
	}
}
