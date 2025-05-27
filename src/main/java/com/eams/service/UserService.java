package com.eams.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.DTO.UserDTO;
import com.eams.entity.User;
import com.eams.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> getAllUser(){
		List<User> users= userRepository.findAll();
		return users.stream()
				.map(user -> new UserDTO(user.getUser_id(), user.getName(),user.getEmail(),user.getRole()))
				.collect(Collectors.toList());
	}
}
