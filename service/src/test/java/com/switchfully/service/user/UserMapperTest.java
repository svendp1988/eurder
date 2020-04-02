package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static com.switchfully.service.testbuilders.TestUserBuilder.testUserBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class UserMapperTest {
    UserMapper userMapper = new UserMapper();

    @Test
    void toNewUser_createsAndReturnsANewUserObject() {
        CreateUserDto createUserDto = new CreateUserDto(testUserBuilder());
        assertEquals(testUserBuilder().buildCustomer(), userMapper.toNewUser(createUserDto));
    }

    @Test
    void toUserDto_createsDtoOutOfExistingUser() {
        User user = testUserBuilder().buildCustomer();
        UserDto userDto = new UserDto(user);
        assertEquals(userDto, userMapper.toUserDto(user));
    }

    @Test
    void toDtoCollection_returnsCollectionOfUserDtos() {
        User customer = testUserBuilder().buildCustomer();
        User admin = testUserBuilder().buildAdmin();
        UserDto customerDto = userMapper.toUserDto(customer);
        UserDto adminDto = userMapper.toUserDto(admin);
        Collection<User> userCollection = List.of(customer, admin);
        assertThat(userMapper.toDtoCollection(userCollection)).containsExactlyInAnyOrder(customerDto, adminDto);
    }
}