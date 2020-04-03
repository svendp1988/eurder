package com.switchfully.api.endpoints;

import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.UserMapper;
import com.switchfully.service.user.UserService;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;

import static com.switchfully.api.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {

    CreateUserDto newCustomer = testUserDtoBuilder().buildCreateUserDto();
    UserDto userDto = testUserDtoBuilder().buildCustomerDto();
    UserRepository userRepository = new UserRepository();
    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());
    UserService userService = new UserService(new UserRepository(), new UserMapper(new AddressMapper(), new UserRoleMapper()));
    UserController userController = new UserController(userService);

    @Test
    void registeringANewCustomer_returnsDtoHoldingTheSameData() {
        UserDto actualDto = userController.register(newCustomer);
        assertThat(actualDto).isEqualTo(userDto);
    }
}