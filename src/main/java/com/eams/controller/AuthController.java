package com.eams.controller;

import com.eams.dtos.UserLoginDTO;
import com.eams.exception.InvalidUserRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eams.dtos.UserAuthDTO;
import com.eams.entity.User;
import com.eams.service.UserAuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private  UserAuthService authService;
    
	
    public AuthController(UserAuthService authservice) {
        this.authService = authservice;
    }

    //function to register user
	@PostMapping("/register")
	public ResponseEntity<UserAuthDTO> registerUser(@RequestBody @Valid User u){

		return ResponseEntity.ok(authService.registerUser(u));
	}

	//function to login user with
	@PostMapping("/login")
	public String loginUser(@RequestBody @Valid UserLoginDTO user) {
		if(authService.loginUser(user)) {
			return "Successully logged in with email : " + user.getEmail();
		}
		return "Failed Login !!";
	}

}
