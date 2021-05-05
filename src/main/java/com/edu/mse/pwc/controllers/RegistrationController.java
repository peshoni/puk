package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reg")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserDto> registration(@RequestBody UserDto newUser) {
        try {
            userService.getUserByUsername(newUser.getUsername());
            return new ApiResponse<UserDto>(HttpStatus.OK.value(), "Username already exist", null);
        } catch (Exception e) {
            UserDto dto = userService.createUser(newUser);
            return new ApiResponse<UserDto>(HttpStatus.OK.value(), "Created successfully", dto);
        }
    }
}
