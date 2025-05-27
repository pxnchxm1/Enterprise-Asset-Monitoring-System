package com.eams.service;


import com.eams.dtos.UserDTO;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.mapper.UserMapper;
import com.eams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	@Autowired
    private static UserRepository userRepository;
	public static List<UserDTO> getAllUser(){
		return userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());
	}
    public static void updateUserRole(Long user_id, String role){
        User u=userRepository.findById(user_id).orElseThrow();
        u.setRole(Role.valueOf(role.toUpperCase()));
        userRepository.save(u);
    }
    public User getbyEmail(String email){
        return userRepository.findByEmail(email).get();
    }
}
