package com.eams.dtos;
import com.eams.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotNull(message="user id cannot be null")
	private Long user_id;
	
	@NotNull(message="name cannot be null")
	private String name;
	
	@NotNull(message="email cannot be null")
	@Email(message="Email should be valid")
	private String email;
	
	@Pattern(regexp="^(?=.[a-z])(?=.[A-Z])(?=.[0-9](?=.[!@#$%^&*.]{8,}$))",message="Give a strong and valid passsword ! ")
	@NotNull(message="Password should not be null")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
