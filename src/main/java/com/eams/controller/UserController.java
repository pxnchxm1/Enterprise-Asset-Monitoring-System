package com.eams.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
//	@GetMapping
//	@PreAuthorize("hasRole('MANAGER')")
//	public ResponseEntity<List<UserDTO>> getAllUsers(){
//		return ResponseEntity.ok(UserService.getAllUsers());
//	}
//	
//	@PutMapping("/{id}/role")
//	@PreAuthorize("hasRole('MANAGER')")
//	public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestParam String role){
//		UserService.updateUser(id,role);
//		return ResponseEntity.ok("User role updated successfully");
//	}
}
