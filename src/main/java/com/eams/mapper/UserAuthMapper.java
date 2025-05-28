package com.eams.mapper;

import org.springframework.stereotype.Component;

import com.eams.dtos.UserAuthDTO;
import com.eams.entity.User;

@Component
public class UserAuthMapper {
	public UserAuthDTO userEntityToUserAuthDTO (User user) {
		UserAuthDTO u = new UserAuthDTO();
		u.setUserId(user.getUser_id());
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setRole(user.getRole());
		return u;	
	}
	public User userAuthDtoToEntity(UserAuthDTO userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		user.setUser_id(user.getUser_id());
		return user;
	}

}
