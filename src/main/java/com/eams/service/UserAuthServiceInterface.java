package com.eams.service;


import com.eams.dtos.UserAuthDTO;
import com.eams.dtos.UserLoginDTO;
import com.eams.entity.User;

public interface UserAuthServiceInterface {
	
    

    public  UserAuthDTO registerUser(User user) ;
	
	public  boolean loginUser(UserLoginDTO userLoginDto);
	

}
