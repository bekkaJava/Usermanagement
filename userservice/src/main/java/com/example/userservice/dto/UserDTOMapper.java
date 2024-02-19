package com.example.userservice.dto;

import com.example.userservice.model.User;

public class UserDTOMapper {

    public static UserDTO  mapToUserDTO(User user) {

        return new UserDTO(user.getUserName());
    }

}
