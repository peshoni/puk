package com.edu.mse.pwc.dtos;

import com.edu.mse.pwc.persistence.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    private String password;
    private Date createdAt;
    private Date modifiedAt;
}
