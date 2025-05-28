package com.eams.controller;
import com.eams.dtos.UserDTO;
import com.eams.service.UserService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users")
@Validated
public class UserController {
    @Autowired
    private UserService us;
    
    //This method is for fetching all the users from the user database
	@GetMapping 
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(us.getAllUser());
    }
	
	//Here we are checking for the values for validation, the method also validates the attributes below
    @PutMapping("/{id}/role/reqPerson")
    public ResponseEntity<String> updateUserRole(
    		@PathVariable Long id,
    		@RequestParam @NotBlank(message="role cannot be blank") String role,
    		@RequestParam @NotBlank(message="requesting person's email cannot be blank")
    				      @Email(message="email should be valid") String reqPerson){
        
    	if(us.updateUserRole(reqPerson,id,role)) {
    		 return ResponseEntity.ok("User role updated successfully");
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or role update failed");
    	}       
    }   
}
