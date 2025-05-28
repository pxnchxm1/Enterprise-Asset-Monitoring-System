package com.eams.dtos;

import org.springframework.stereotype.Component;

import com.eams.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthDTO {
	//TODO:Validation for each
	private String email;
	private String password;
	private Role role;

}
