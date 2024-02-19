package com.example.userservice.dto;

public record UserAddRequest(
        String userName,
        String password
) {
}
