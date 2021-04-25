package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
//@RolesAllowed(value = {"MODERATOR", "ADMIN", "USER"})
public class UsersController {
    private PasswordEncoder encoder;

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userService.createUser(userDto);
    }


}
