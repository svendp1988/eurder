package com.switchfully.service.user;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.AddressRepository;
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

@Component
public class UserMapper {
    private final AddressMapper addressMapper;
    private final UserRoleMapper userRoleMapper;
    private final AddressRepository addressRepository;

    @Autowired
    public UserMapper(AddressMapper addressMapper, UserRoleMapper userRoleMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.userRoleMapper = userRoleMapper;
        this.addressRepository = addressRepository;
    }

    public User toNewUser(CreateUserDto createUserDto) {
        Address address = addressMapper.toAddress(createUserDto.getAddressDto());
        addressRepository.save(address);
        return userBuilder()
                .withFirstName(createUserDto.getFirstName())
                .withLastName(createUserDto.getLastName())
                .withEmail(createUserDto.getEmail())
                .withPassword(createUserDto.getPassword())
                .withAddress(address)
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
