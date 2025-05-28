package com.eams.dtos;
import com.eams.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@NotBlank(message="user id cannot be null")
	private Long user_id;
	
	@NotNull(message="name cannot be null")
	private String name;
	
	@NotNull(message="email cannot be null")
	@Email(message="email should be valid")
	private String email;
	
	@NotNull(message="Role cannot be null")
	@Enumerated(EnumType.STRING)
	private Role role;
}
