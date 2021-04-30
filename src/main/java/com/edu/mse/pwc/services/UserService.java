package com.edu.mse.pwc.services;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.mappers.UserMapper;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.persistence.repository.UserRepository;
import com.edu.mse.pwc.utils.P;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto createUser(UserDto user) {
        UserEntity userEntity = userMapper.userDtoToEntity(user);
        UserEntity newUser = userRepository.save(userEntity);
        return userMapper.userEntityToDto(newUser);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userEntityToDto).collect(Collectors.toList());
    }

    public UserDto getUser(long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("No user found for id " + id);
        }
        return userMapper.userEntityToDto(byId.get());
    }

    public UserEntity getUserEntity(long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            throw new IllegalArgumentException("No user found for id " + id);
        }
        return byId.get();
    }

    public UserDto updateUser(UserDto user) {
        UserEntity newOne = userMapper.userDtoToEntity(user);
        P.syso(newOne);
        //  newOne.setId(entity.getId());
        newOne = userRepository.save(newOne);
        P.syso(newOne);
        return userMapper.userEntityToDto(newOne);
    }

}
