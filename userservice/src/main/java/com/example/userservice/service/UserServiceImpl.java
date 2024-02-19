package com.example.userservice.service;

import com.example.userservice.dto.UserAddRequest;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.dto.UserDTOMapper;
import com.example.userservice.exception.UserAlreadyExistsException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findById(String userName) {

        return userRepository.findById(userName)
                .map(UserDTOMapper::mapToUserDTO)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with name: %s, not found", userName));
    }

    @Override
    public UserDTO createUser(UserAddRequest userAddRequest) {

        final String userName = userAddRequest.userName();

        if (userRepository.findById(userName).isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with username: %s, already exists",  userName);
        }

        User savedUser = User.builder()
                .userName(userName)
                .password(passwordEncoder.encode(userAddRequest.password()))
                .enabled(true)
                .build();

        userRepository.save(savedUser);

        return UserDTOMapper.mapToUserDTO(savedUser);

    }

}
