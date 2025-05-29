package com.eams.service;


import com.eams.dtos.UserDTO;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.mapper.UserMapper;
import com.eams.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserServiceInterface{
	@Autowired
    private  UserRepository userRepository;
	
	// the method  returns all the users ,or else if the role is not manager ,then the method throws exception
	
	public  List<UserDTO> getAllUser(String reqPersonMail){
		User requestMail=userRepository.findByEmail(reqPersonMail).orElseThrow();
		if(requestMail.getRole() != Role.MANAGER) {
			log.warn("Only manager has access to fetch all the users");
			throw new SecurityException("Only Manager can get all users");
		}
		log.info("Users are fetched by :"+reqPersonMail);
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
            log.info("User updated successfully");
            return true;
        }
    	}
        catch(Exception e) {
        	log.error("Error while updating user role: " + e.getMessage());
        }
        return false;
    }
    
    //Deletes the user only when the role is manager 
    
    public boolean deleteUser(String reqPersonMail,Long userid) {
    	try {
    		User u=userRepository.findByEmail(reqPersonMail).orElseThrow();
    		
    		if(u.getRole() == Role.MANAGER) {
    			userRepository.deleteById(userid);
    			log.info("User is deleted by Manager");
        		return true;
    		} 	
    	}    	
    		catch(Exception e) {
    			log.error("Error while deleting: "+e.getMessage());
    			
    		}
    	return false;
    }
    
    //Finds the user by user email
    @Override
    public User getbyEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
    
    
}
