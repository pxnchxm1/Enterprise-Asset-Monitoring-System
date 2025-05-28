package com.eams.controller;
import com.eams.dtos.UserDTO;
import com.eams.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
    @PutMapping("/{id}/role/reqPerson")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id,@RequestParam String role,@RequestParam String reqPerson){
        
    	if(us.updateUserRole(reqPerson,id,role)) {
    		 return ResponseEntity.ok("User role updated successfully");
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or role update failed");
    	}
       
    }
}
