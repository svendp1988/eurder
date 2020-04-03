package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleDto;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Collection;
import java.util.List;

import static com.switchfully.service.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class UserMapperTest {
    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());

    @Test
    void toNewUser_createsAndReturnsANewUserObject() {
        CreateUserDto createUserDto = testUserDtoBuilder().buildCreateUserDto();
        User user = userMapper.toNewUser(createUserDto);
        assertEquals(user.getFirstName(), createUserDto.getFirstName());
        assertEquals(user.getLastName(), createUserDto.getLastName());
        assertEquals(user.getEmail(), createUserDto.getEmail());
        assertTrue(verifyHash(createUserDto.getPassword(), user.getPassword()));
    }

    @Test
    void toUserDto_createsDtoOutOfExistingUser() {
        User user = testUserDtoBuilder().buildCustomer();
        UserDto userDto = userMapper.toUserDto(user);
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPassword(), user.getPassword());
        System.out.println(userDto.getRole());
        assertEquals(UserRoleDto.CUSTOMER, userDto.getRole());
    }

    @Test
    void toDtoCollection_returnsCollectionOfUserDtos() {
        User customer = testUserDtoBuilder().buildCustomer();
        User admin = testUserDtoBuilder().buildAdmin();
        UserDto customerDto = userMapper.toUserDto(customer);
        UserDto adminDto = userMapper.toUserDto(admin);
        Collection<User> userCollection = List.of(customer, admin);
        assertThat(userMapper.toDtoCollection(userCollection)).containsExactlyInAnyOrder(customerDto, adminDto);
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}