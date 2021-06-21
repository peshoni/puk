package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.ApiResponse;
import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.mappers.UserMapper;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.services.UserService;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@RolesAllowed(value = {"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @PostMapping("/{username}/")
    public UserDto getMyId(@PathVariable String username) {
        UserEntity entity = userService.getUserByUsername((username));
        if (entity != null) {
            UserDto dto = userMapper.userEntityToDto((entity));
            P.clearUserSensitiveData(dto);
            return dto;
        }
        return null;
    }

    @GetMapping
    public ApiResponse<List<UserDto>> listUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ApiResponse<List<UserDto>>(HttpStatus.OK.value(), "Fetched successfully",
                users, users.size());
    }

    @PostMapping("/up/")
    @RolesAllowed(value = {"ROLE_ADMIN"})
    public UserDto updateUsers(@RequestBody UserDto user) {
        P.syso(user.toString());
        return userService.updateUser(user);
    }
}
