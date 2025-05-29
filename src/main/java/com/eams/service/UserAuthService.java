package com.eams.service;

import com.eams.dtos.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eams.dtos.UserAuthDTO;
import com.eams.entity.User;
import com.eams.repository.UserRepository;
import com.eams.util.PasswordEncoderUtility;
import com.eams.exception.UserAlreadyExistsException;

@Slf4j
@Service
public class UserAuthService implements UserAuthServiceInterface {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired 
	private PasswordEncoderUtility passwordEncoder;
    public UserAuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserAuthDTO registerUser(User user) {
		if(userRepo.existsByEmail(user.getEmail())) {
			log.warn("User with same email is already existing in database");
			throw  new UserAlreadyExistsException("User already exists");
		}
		else {
			User u = new User();
			u.setEmail(user.getEmail());
			u.setName(user.getName());
			u.setPassword(passwordEncoder.encodeMyRawPassword(user.getPassword()));
			u.setRole(user.getRole());
			userRepo.save(u);

            log.info("Successfully registered the User In !");

			UserAuthDTO responseDto = new UserAuthDTO();
			responseDto.setUserId(u.getUser_id());
			responseDto.setPassword("**********");
			responseDto.setRole(u.getRole());
			responseDto.setEmail(u.getEmail());
			responseDto.setName(u.getName());
			return responseDto;
		}
		
		
	}
	
	public boolean loginUser(UserLoginDTO userLoginDto) {
		User user = userRepo.findByEmail(userLoginDto.getEmail()).orElseThrow();
        return passwordEncoder.matchMyPasswords(userLoginDto.getPassword(), user.getPassword());
    }
	
	

}
