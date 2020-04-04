package com.switchfully.api.endpoints;

import com.switchfully.service.user.UserService;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.switchfully.api.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    CreateUserDto newCustomer = testUserDtoBuilder().buildCreateUserDto();
    UserDto userDto = testUserDtoBuilder().buildCustomerDto();

    @Mock
    UserService userService;
    @InjectMocks
    UserController userController;

    @Test
    void registeringANewCustomer_returnsDtoHoldingTheSameData() {
        when(userService.registerAsCustomer(newCustomer)).thenReturn(userDto);
        assertThat(userController.register(newCustomer)).isEqualTo(userDto);
    }

    @Test
    void viewingAllCustomers_returnsCollectionOfCustomers() {
        when(userService.viewAllCustomers()).thenReturn(List.of(userDto));
        assertThat(userController.viewAllCustomers()).isEqualTo(List.of(userDto));
    }

    @Test
    void getById_returnsDtoWithGivenId() {
        when(userService.getById("id")).thenReturn(userDto);
        assertThat(userController.getById("id")).isEqualTo(userDto);
    }
}