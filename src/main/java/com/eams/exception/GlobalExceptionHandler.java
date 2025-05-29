package com.eams.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import com.eams.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//handles @RequestBody and @Valid errors usually throws when method arguments fails validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	//handles the error when the data validation constraint is violated
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	//Handles error when user is not found
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElement(NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
    //incase user with given mail already exists in database
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException uae){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
	}

	//exception to handle if role is invalid .
	@ExceptionHandler(InvalidUserRoleException.class)
	public ResponseEntity<String> handleInvalidUserRole(InvalidUserRoleException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User. User should be MANAGER|OPERATOR");
	}

	//Exception to handle if asset_id is not found
	@ExceptionHandler(AssetNotFoundException.class)
	public ResponseEntity<String> handleAssetNotFound(AssetNotFoundException ax){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ax.getMessage());
	}
}
