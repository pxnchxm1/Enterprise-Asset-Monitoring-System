package com.eams.exception;


import org.springframework.stereotype.Component;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
