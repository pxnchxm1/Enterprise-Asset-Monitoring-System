package com.eams.mapper;

import org.springframework.stereotype.Component;

import com.eams.dto.UserAuthDTO;
import com.eams.entity.User;

@Component
public class UserAuthMapper {
	
	
	
	public UserAuthDTO userEntityToUserAuthDTO (User user) {
		UserAuthDTO u = new UserAuthDTO();
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		return u;	
	}
	public User userAuthDtoToEntity(UserAuthDTO udto) {
		User user = new User();
		user.setEmail(udto.getEmail());
		user.setPassword(udto.getPassword());
		return user;
	}

}
