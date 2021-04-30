package com.edu.mse.pwc.mappers;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserEntity userDtoToEntity(UserDto dto);

    UserDto userEntityToDto(UserEntity dto);

}
