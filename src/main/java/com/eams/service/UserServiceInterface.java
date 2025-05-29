package com.eams.service;

import java.util.List;
import com.eams.dtos.UserDTO;
import com.eams.entity.User;

public interface UserServiceInterface {

	public List<UserDTO> getAllUser(String reqPersonMail);
    public boolean updateUserRole(String reqPersonMail,Long user_id, String role);
    public boolean deleteUser(String reqPersonMail,Long userid);
    public User getbyEmail(String email);
}
