package com.eams.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PasswordEncoderUtility  {
	private  final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	public  String encodeMyRawPassword(String password){
		return encoder.encode(password);
	}
	public boolean matchMyPasswords(String raw,String dbStoredPassword){
		return encoder.matches(raw,dbStoredPassword);
	}

}
