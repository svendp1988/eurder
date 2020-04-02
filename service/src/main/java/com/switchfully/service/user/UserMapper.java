package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;

@Component
public class UserMapper {
    public User toNewUser(CreateUserDto createUserDto) {
        return userBuilder()
                .withFirstName(createUserDto.getFirstName())
                .withLastName(createUserDto.getLastName())
                .withEmail(createUserDto.getEmail())
                .withPassword(createUserDto.getPassword())
                .withAddress(createUserDto.getAddress())
                .buildCustomer();
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user);
    }

    public Collection<UserDto> toDtoCollection(Collection<User> memberCollection) {
        return memberCollection.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
