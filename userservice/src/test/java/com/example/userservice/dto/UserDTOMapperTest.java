package com.example.userservice.dto;

import com.example.userservice.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOMapperTest {

    @Test
    public void should_Map_To_UserDTO() {

        //given
        User user = User.builder()
                .userName("JohnDoe")
                .password("password")
                .enabled(true)
                .build();

        //when
        UserDTO dto = UserDTOMapper.mapToUserDTO(user);

        //then
        assertEquals(dto.userName(), user.getUserName());


    }

}