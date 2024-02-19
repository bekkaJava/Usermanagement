package com.example.userservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
