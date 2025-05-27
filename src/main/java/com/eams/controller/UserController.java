package com.eams.controller;
import com.eams.dtos.UserDTO;
import com.eams.service.UserService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/users")
public class UserController {
  
  //TODO: use explicit check in service layer to check if its manager and apply changes(reason: not using java security as of now)
	//TODO : use autowired tag and use dependency injection for using Service layer rather than using as static methods..
    @GetMapping("/")
  // @PreAuthorize("hasRole('MANAGER')")  
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(UserService.getAllUser());
    }
    @PutMapping("/{id}/role")
  //  @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id,@RequestParam String role){
        UserService.updateUserRole(id,role);
        return ResponseEntity.ok("User role updated successfully");
    }
}
