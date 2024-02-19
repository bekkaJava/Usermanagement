package com.example.userservice.handler;

import com.example.userservice.exception.UserAlreadyExistsException;
import com.example.userservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class EntityRestExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> userNotFound(UserNotFoundException e) {

        ApiErrorResponse error = new ApiErrorResponse(
                e.getMessage(),
                NOT_FOUND,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> userAlreadyExists(UserAlreadyExistsException e) {

        ApiErrorResponse error = new ApiErrorResponse(
                e.getMessage(),
                BAD_REQUEST,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> globalException(Exception e) {

        ApiErrorResponse error = new ApiErrorResponse(
                e.getMessage(),
                INTERNAL_SERVER_ERROR,
                System.currentTimeMillis());

        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }

}
