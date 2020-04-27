package com.switchfully.service.user;

import com.switchfully.domain.exceptions.UserNotFoundException;
import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.AddressRepository;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto registerAsCustomer(CreateUserDto createUserDto) {
        User registeredUser = userRepository.save(userMapper.toNewUser(createUserDto));
        return userMapper.toUserDto(registeredUser);
    }

    public Collection<UserDto> viewAllCustomers() {
        return userMapper.toDtoCollection(userRepository.findAllByRole(CUSTOMER));
    }

    public UserDto getById(long id) {
        return userMapper.toUserDto(findUserOrThrowException(id));
    }

    private User findUserOrThrowException(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
