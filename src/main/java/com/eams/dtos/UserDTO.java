package com.eams.dtos;
import com.eams.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long user_id;
	private String name;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
}
