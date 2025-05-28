package com.eams.dtos;

import org.springframework.stereotype.Component;

import com.eams.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthDTO {
	
	
	@Email(message = "Invalid email format ! Please enter a valid email")
	@NotNull(message="Email shouldnt be null ! ")
	private String email;
	
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$",message="Give a strong and valid passsword ! ")
	@NotNull(message="Password should not be null")
	private String password;
	
	
	private Role role;

}
