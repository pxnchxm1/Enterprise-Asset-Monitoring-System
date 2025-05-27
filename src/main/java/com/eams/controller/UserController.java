package com.eams.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eams.DTO.UserDTO;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@GetMapping
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(UserService.getAllUsers());
	}
	
	@PutMapping("/{id}/role")
	@PreAuthorize("hasRole('MANAGER')")
	public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestParam String role){
		UserService.updateUser(id,role);
		return ResponseEntity.ok("User role updated successfully");
	}
}
