package com.example.userservice.service;

import com.example.userservice.dto.UserAddRequest;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.UserAlreadyExistsException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void should_successfully_find_user_by_name() {

        String userName = "JohnDoe";

        UserDTO expectedDto = new UserDTO(userName);

        //given
        User user = User.builder()
                .userName(userName)
                .password("password")
                .enabled(true)
                .build();


        when(userRepository.findById(userName))
                .thenReturn(Optional.of(user));

        //when
        UserDTO dbUserDto = userService.findById(userName);


        //then
        assertEquals(dbUserDto.userName(), expectedDto.userName());

        verify(userRepository, times(1)).findById(userName);


    }


    @Test
    public void should_successfully_save_a_user() {

        //given
        UserAddRequest request = new UserAddRequest(
                "JohnBrown",
                "password");

        //when
        UserDTO responseDto = userService.createUser(request);

        //then
        assertEquals(responseDto.userName(), request.userName());

        verify(userRepository, times(1)).save(any(User.class));


    }


    @Test
    public void should_throw_user_not_found_exception_when_user_not_found() {

        //given
        String userName = "JohnDoe";

        when(userRepository.findById(userName))
                .thenReturn(Optional.empty());

        //then
        assertThrows(UserNotFoundException.class, () ->
                userService.findById(userName));

    }

    @Test
    public void should_not_throw_user_not_found_exception_when_exists() {

        //given
        String userName = "JohnDoe";

        when(userRepository.findById(userName))
                .thenReturn(Optional.of(new User()));

        //then
        assertDoesNotThrow(() -> userService.findById(userName));

    }


    @Test
    public void should_throw_user_already_exists_exception_when_user_already_exists() {

        //given
        String userName = "JohnDoe";

        UserAddRequest userAddRequest = new UserAddRequest
                (userName, "password");

        when(userRepository.findById(userName))
                .thenReturn(Optional.of(new User()));

        //then
        assertThrows(UserAlreadyExistsException.class, () ->
                userService.createUser(userAddRequest));


    }


    @Test
    public void should_not_throw_user_already_exists_exception_when_user_not_exists() {

        //given
        String userName = "JohnDoe";

        UserAddRequest userAddRequest = new UserAddRequest
                (userName, "password");

        when(userRepository.findById(userName))
                .thenReturn(Optional.empty());

        //then
        assertDoesNotThrow(() -> userService.createUser(userAddRequest));
    }


}
