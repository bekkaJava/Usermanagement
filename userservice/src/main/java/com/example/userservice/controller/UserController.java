package com.example.userservice.controller;

import com.example.userservice.dto.UserAddRequest;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> findById(@PathVariable String username) {

        return ok(userService.findById(username));

    }


    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserAddRequest userAddRequest) {

        return new ResponseEntity<>(userService.createUser(userAddRequest), CREATED);

    }

}
