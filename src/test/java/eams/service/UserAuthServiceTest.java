package eams.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eams.dtos.UserAuthDTO;
import com.eams.dtos.UserLoginDTO;
import com.eams.entity.Role;
import com.eams.entity.User;
import com.eams.repository.UserRepository;
import com.eams.service.UserAuthService;
import com.eams.util.PasswordEncoderUtility;


class UserAuthServiceTest {
	
	@InjectMocks
	private UserAuthService userAuthService;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private PasswordEncoderUtility encoderutility;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void userRegistration() {
		
		User user = new User(1L,"Priya","priya123@gmail.com","pri_09P",Role.OPERATOR);
		//if user exists then no proceeding..if not proceed
		when(userRepo.existsByEmail(user.getEmail())).thenReturn(false);
		when(encoderutility.encodeMyRawPassword("pri_09P")).thenReturn("encodedpri_09P");
		
		UserAuthDTO response = userAuthService.registerUser(user);
		assertEquals("priya123@gmail.com",response.getEmail());
		assertEquals("Priya",response.getName());
		assertEquals("**********",response.getPassword());
		assertEquals(Role.OPERATOR,response.getRole());
		verify(userRepo, times(1)).save(any(User.class));
	}
	
	@Test
	void userLogin() {
	    UserLoginDTO loginDto = new UserLoginDTO("priya123@gmail.com", "pri_09P");
	    User userInDB = new User(1L, "Priya", "priya123@gmail.com", "encodedpri_09P", Role.OPERATOR);

	    when(userRepo.findByEmail("priya123@gmail.com")).thenReturn(java.util.Optional.of(userInDB));
	    when(encoderutility.matchMyPasswords("pri_09P", "encodedpri_09P")).thenReturn(true);

	    Boolean response = userAuthService.loginUser(loginDto);
	    assertTrue(response);

	    verify(userRepo, times(1)).findByEmail("priya123@gmail.com");
	    verify(encoderutility, times(1)).matchMyPasswords("pri_09P", "encodedpri_09P");
	}

	

}
