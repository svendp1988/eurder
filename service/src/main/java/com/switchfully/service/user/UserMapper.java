package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.service.user.dto.UserDtoBuilder.userDtoBuilder;

@Component
public class UserMapper {
    private AddressMapper addressMapper;
    private UserRoleMapper userRoleMapper;

    @Autowired
    public UserMapper(AddressMapper addressMapper, UserRoleMapper userRoleMapper) {
        this.addressMapper = addressMapper;
        this.userRoleMapper = userRoleMapper;
    }

    public User toNewUser(CreateUserDto createUserDto) {
        return userBuilder()
                .withFirstName(createUserDto.getFirstName())
                .withLastName(createUserDto.getLastName())
                .withEmail(createUserDto.getEmail())
                .withPassword(createUserDto.getPassword())
                .withAddress(addressMapper.toAddress(createUserDto.getAddressDto()))
                .buildCustomer();
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                userRoleMapper.toDto(user.getRole()),
                user.getPassword(),
                addressMapper.toDto(user.getAddress())
        );
    }

    public Collection<UserDto> toDtoCollection(Collection<User> memberCollection) {
        return memberCollection.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
