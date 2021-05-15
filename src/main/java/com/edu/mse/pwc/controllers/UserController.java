package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.services.UserService;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@RolesAllowed(value = {"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
public class UserController {

    private final UserService userService;

    @PostMapping
    @RolesAllowed(value = {"ROLE_ADMIN"})
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @PostMapping("/{username}/")
    public Long getMyId(@PathVariable String username) {
        UserEntity entity = userService.getUserByUsername((username));
        return entity == null ? 0 : entity.getId();
    }

    @GetMapping
    @RolesAllowed(value = {"ROLE_ADMIN"})
    public List<UserDto> listUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    @RolesAllowed(value = {"ROLE_ADMIN"})
    public UserDto updateUsers(@RequestBody UserDto user) {
        P.syso(user.toString());
        return userService.updateUser(user);
    }


//
//    @PostMapping
//    public UserDto createUser(@RequestBody UserDto userDto) {
//        System.out.println("Create user");
//        String pass = encoder.encode(userDto.getPassword());
//        P.syso(pass);
//        userDto.setPassword(pass);
//        return userService.createUser(userDto);
//    }
//
//    @GetMapping("/test")
//    public String testPoint() {
//
//        return "Hello";
//    }

}
