package com.switchfully.service.user;

import com.switchfully.domain.exceptions.UserNotFoundException;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.service.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    User customer = userBuilder()
            .withFirstName("firstName")
            .withLastName("lastName")
            .withEmail("email")
            .withPassword("password")
            .withAddress(addressBuilder()
                    .withStreet("street")
                    .withStreetNumber("streetNumber")
                    .withPostalCode("postalCode")
                    .withCity("city")
                    .build())
            .buildCustomer();
    CreateUserDto createUserDto = testUserDtoBuilder().buildCreateUserDto();
    UserDto customerDto = testUserDtoBuilder().buildCustomerDto();

    @Mock
    UserMapper userMapper;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenRegisteringAsCustomer_aDtoIsReturnedWithCorrectValues() {
        when(userRepository.save(any(User.class))).thenReturn(customer);
        when(userMapper.toNewUser(any(CreateUserDto.class))).thenReturn(customer);
        when(userMapper.toUserDto(customer)).thenReturn(customerDto);
        UserDto actualCustomerDto = userService.registerAsCustomer(createUserDto);
        assertThat(actualCustomerDto).isEqualToIgnoringGivenFields(createUserDto, "id", "role");
    }

    @Test
    void findById_throwsException_whenIdNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.getById(20L));
    }

    //    @Test
//    void getById_returnsCustomerDto_withGivenId() {
//        when(userRepository.findById(1L)).thenReturn(customer);
//        when(userMapper.toUserDto(customer)).thenReturn(customerDto);
//        assertEquals(customerDto, userService.getById("id"));
//    }
}