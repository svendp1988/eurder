package com.switchfully.api.endpoints;

import com.switchfully.service.user.UserService;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.switchfully.api.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    CreateUserDto newCustomer = testUserDtoBuilder().buildCreateUserDto();
    UserDto userDto = testUserDtoBuilder().buildCustomerDto();
//
//    @Mock
//    UserRepository userRepository = new UserRepository();
//    @Mock
//    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());
//    = new UserService(new UserRepository(), new UserMapper(new AddressMapper(), new UserRoleMapper()));

//    UserService userService = Mockito.mock(UserService.class);
    UserController userController;

    @Test
    void registeringANewCustomer_returnsDtoHoldingTheSameData() {
        UserService userService = Mockito.mock(UserService.class);
        UserController userController = new UserController(userService);
        when(userService.registerAsCustomer(newCustomer)).thenReturn(userDto);
        assertThat(userController.register(newCustomer)).isEqualTo(userDto);
    }
}