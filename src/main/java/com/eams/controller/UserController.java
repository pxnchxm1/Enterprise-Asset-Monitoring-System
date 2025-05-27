package com.eams.controller;
import com.eams.dtos.UserDTO;
import com.eams.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService us;
    
	@GetMapping 
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(us.getAllUser());
    }
	
    @PutMapping("/{id}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id,@RequestParam String role){
        us.updateUserRole(id,role);
        return ResponseEntity.ok("User role updated successfully");
    }
}
