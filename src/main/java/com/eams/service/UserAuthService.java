package com.eams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.dtos.UserAuthDTO;
import com.eams.dtos.UserDTO;
import com.eams.entity.User;
import com.eams.mapper.UserMapper;
import com.eams.repository.UserRepository;

@Service
public class UserAuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	public UserDTO registerUser(User user) {
		User u = new User();
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setPassword(user.getPassword());
		u.setRole(user.getRole());
		userRepo.save(u);
		return UserMapper.userToDto(user);
		
	}
	
	public boolean loginUser(UserAuthDTO userdto) {
		User user = userRepo.findByEmail(userdto.getEmail()).orElseThrow();
		if(user.getPassword().equals(userdto.getPassword())) {
			return true;
		}
		return false;
		
	}
	
	

}
