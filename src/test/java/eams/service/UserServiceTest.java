package eams.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eams.dtos.UserDTO;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.repository.UserRepository;
import com.eams.service.UserService;

public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	//test case to fetch all the users when the user is manager
	@Test
	public void testgetAllUser_whenManager() {
		String managerMail="manager@gmail.com";
		User manager=new User(null,"managerName",managerMail, "password", Role.MANAGER);
		
		User user1=new User(1L,"Sara",managerMail,"Sa_123SARA",Role.OPERATOR);
		User user2=new User(1L,"Virat",managerMail,"Virat_678",Role.OPERATOR);
		when(userRepository.findByEmail(managerMail)).thenReturn(Optional.of(manager));
		when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2));
		List<UserDTO> result=userService.getAllUser(managerMail);
		assertEquals(2,result.size());
	}
	
	
	//Test case to get all users when the user is not manager
	@Test
	public void testgetAllUsers_whenNotManager() {
		String userMail="user@gmail.com";
		User user1=new User(null,"Anushka",userMail,"anu_890",Role.OPERATOR);
		when(userRepository.findByEmail(userMail)).thenReturn(Optional.of(user1));
		assertThrows(SecurityException.class,() -> userService.getAllUser(userMail));
	}
	
	//Test case to test update user role only when the user is manager
	@Test
	public void testUpdateUserRole_whenManager() {
		String managerMail="manager@gmail.com";
		User manager=new User(1L,"managerName",managerMail, "password", Role.MANAGER);
		
		User user1=new User(null,"alia",managerMail,"Ala_234",Role.OPERATOR);
		when(userRepository.findByEmail(managerMail)).thenReturn(Optional.of(manager));
		when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
		boolean updated=userService.updateUserRole(managerMail,1L,"MANAGER");
		assertTrue(updated);
		assertEquals(Role.MANAGER,user1.getRole());
	}
	
	//Test case to test delete user role only when the user is manager
	@Test
	public void testDeleteUser_whenManager() {
		String managerMail="manager@gmail.com";
		User manager=new User(1L,"managerName",managerMail, "password", Role.MANAGER);
		when(userRepository.findByEmail(managerMail)).thenReturn(Optional.of(manager));
		boolean deleted=userService.deleteUser(managerMail,1L);
		assertTrue(deleted);
		verify(userRepository).deleteById(1L);
	}
	
	@Test
	public void testGetByEmail() {
		String email="user@gmail.com";
		User user1=new User(null,"sana",email,"Sa_12",Role.OPERATOR);
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user1));
		User result=userService.getbyEmail(email);
		assertNotNull(result);
		assertEquals(email,result.getEmail());
	}
}
