package com.eams.service;


import com.eams.dtos.UserDTO;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.mapper.UserMapper;
import com.eams.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
	@Autowired
    private  UserRepository userRepository;
	
	// the method checks for role if the role matches manager, then it returns all the users ,or else if the role is not manager ,then the method throws exception
	public  List<UserDTO> getAllUser(){
		
		return userRepository.findAll().stream()
                .map(UserMapper::userToDto)
                .collect(Collectors.toList());			
	}
	
	//This method finds the user by userid, if found checks if the role is manager,if manager ,then updates the user role and if user is not manager ,then the method throws exceptions
    public boolean updateUserRole(String reqPersonMail,Long user_id, String role){
    	try {
        User u=userRepository.findByEmail(reqPersonMail).orElseThrow();
        User user = userRepository.findById(user_id).orElseThrow();
        if(u.getRole() == Role.MANAGER) {
        	user.setRole(Role.valueOf(role.toUpperCase()));
            userRepository.save(u);
            return true;
        }
    	}
        catch(Exception e) {
        	System.out.println("Error while updating user role: " + e.getMessage());
        }
        return false;
    }
    
    //Deletes the user only when the role is manager 
    public boolean deleteUser(String reqPersonMail,Long userid,String role) {
    	try {
    		User u=userRepository.findByEmail(reqPersonMail).orElseThrow();
    		
    		if(u.getRole() != Role.MANAGER) {
    			return false;
    		}
    		User idToDelete=userRepository.findById(userid).orElseThrow();
    		userRepository.delete(idToDelete);
    		return true;
    	}    	
    		catch(Exception e) {
    			System.err.println("Error while deleting: "+e.getMessage());
    		}
    	return false;
    }
    
    //Finds the user by user email
    public User getbyEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
    
    
}
