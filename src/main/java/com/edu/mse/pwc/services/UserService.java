package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.mappers.UserMapper;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.userDtoToEntity(userDto);
        return userMapper.userEntityToDto(userRepository.save(userEntity));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userEntityToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(long id) {
        Optional<UserEntity> opt = userRepository.findById(id);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException("No user found for id " + id + "");
        }
        return userMapper.userEntityToDto(opt.get());
    }

}
