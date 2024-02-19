package com.example.userservice.service;

import com.example.userservice.dto.UserAddRequest;
import com.example.userservice.dto.UserDTO;

public interface UserService {

    UserDTO findById(String name);

    UserDTO createUser(UserAddRequest userAddRequest);

}
