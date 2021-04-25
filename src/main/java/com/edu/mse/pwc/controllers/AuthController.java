package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/token")
public class AuthController {
    private PasswordEncoder encoder;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        System.out.println("HHHHHH");
        //userDto.setPassword(encoder.encode(userDto.getPassword()));
        return null;
    }


}