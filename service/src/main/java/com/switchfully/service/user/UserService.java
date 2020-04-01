package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto registerAsCustomer(CreateUserDto createUserDto) {
        User registeredUser = userRepository.registerAsCustomer(userMapper.toNewUser(createUserDto));
        return userMapper.toUserDto(registeredUser);
    }

    public Collection<UserDto> viewAllCustomers() {
        return userMapper.toDtoCollection(userRepository.getAllCustomers());
    }

    public UserDto getById(String id) {
        return userMapper.toUserDto(userRepository.getById(id));
    }
}
