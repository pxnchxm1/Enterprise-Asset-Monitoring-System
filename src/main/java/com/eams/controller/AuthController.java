package com.eams.controller;

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
	private  UserAuthService authservice;
	
	//function to register user 
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody @Valid User u){
		return ResponseEntity.ok(authservice.registerUser(u));
	}
	//function to login user
	
	@PostMapping("/login")
	public String loginUser(@RequestBody @Valid UserAuthDTO user) {
		if(authservice.loginUser(user)) {
			return "Successully logged in ";
		}
		return "Invalid Credentials !!";
	}

}
