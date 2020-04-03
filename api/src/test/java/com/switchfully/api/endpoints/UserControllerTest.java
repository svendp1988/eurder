package com.switchfully.api.endpoints;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.UserMapper;
import com.switchfully.service.user.UserService;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.switchfully.api.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static com.switchfully.service.address.AddressDtoBuilder.addressDtoBuilder;
import static com.switchfully.service.user.dto.UserDtoBuilder.userDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    CreateUserDto newCustomer = userDtoBuilder()
            .withFirstName("firstName")
            .withLastName("lastName")
            .withEmail("email")
            .withPassword("password")
            .withAddress(addressDtoBuilder()
                    .withStreet("street")
                    .withStreetNumber("streetNumber")
                    .withPostalCode("postalCode")
                    .withCity("city")
                    .build())
            .buildCreateUserDto();
    UserDto userDto = testUserDtoBuilder().buildCustomerDto();
//    UserRepository userRepository = new UserRepository();
//    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());

    @Mock
    UserService userService = new UserService(new UserRepository(), new UserMapper(new AddressMapper(), new UserRoleMapper()));

    @InjectMocks
    UserController userController = new UserController(userService);

    @Test
    void registeringANewCustomer_returnsDtoHoldingTheSameData() {
//        when(userService.registerAsCustomer(newCustomer)).thenReturn(userDto);
        UserDto actualDto = userController.register(newCustomer);
        assertThat(actualDto).isEqualTo(userDto);
    }
}