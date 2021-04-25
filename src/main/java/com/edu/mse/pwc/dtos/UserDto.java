package com.edu.mse.pwc.dtos;

import com.edu.mse.pwc.persistence.entities.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private RoleEnum role;
    private String username;
    private String password;
}
