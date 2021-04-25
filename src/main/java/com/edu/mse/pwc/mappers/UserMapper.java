package com.edu.mse.pwc.mappers;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto userEntityToDto(UserEntity userEntity);

    UserEntity userDtoToEntity(UserDto userDto);
}
