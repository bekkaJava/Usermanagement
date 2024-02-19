package com.example.userservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message, Object... args) {
        super(String.format(message, args));
    }
}
