package com.example.userservice.handler;

import org.springframework.http.HttpStatus;

public record ApiErrorResponse(
        String message,
        HttpStatus httpStatus,
        long timeStamp

) {

}
