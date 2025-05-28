package com.eams.mapper;

import org.springframework.stereotype.Component;

import com.eams.dtos.UserDTO;
import com.eams.entity.User;

@Component
public class UserMapper {
	public static UserDTO userToDto(User user) {
		UserDTO u = new UserDTO();
		u.setEmail(user.getEmail());
		u.setName(user.getName());
		u.setRole(user.getRole());
		u.setPassword(user.getPassword());
		u.setUser_id(user.getUser_id());
		return u;	
	}
	public static User userDtoToUser(UserDTO userdto) {
		User u = new User();
		u.setEmail(userdto.getEmail());
		u.setName(userdto.getName());
		u.setRole(userdto.getRole());
		u.setPassword(userdto.getPassword());
		u.setUser_id(userdto.getUser_id());
		return u;	
	}
}
