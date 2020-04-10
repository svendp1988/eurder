package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.service.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    User admin = userBuilder()
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
            .buildAdmin();
    CreateUserDto createUserDto = testUserDtoBuilder().buildCreateUserDto();
    UserDto customerDto = testUserDtoBuilder().buildCustomerDto();
    UserDto adminDto = testUserDtoBuilder().buildAdminDto();
    Collection<User> database = List.of(customer);
    Collection<UserDto> dtoDatabase = List.of(customerDto);

    @Mock
    UserMapper userMapper;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenRegisteringAsCustomer_aDtoIsReturnedWithCorrectValues() {
        when(userRepository.registerAsCustomer(customer)).thenReturn(customer);
        when(userMapper.toNewUser(createUserDto)).thenReturn(customer);
        when(userMapper.toUserDto(customer)).thenReturn(customerDto);
        UserDto actualCustomerDto = userService.registerAsCustomer(createUserDto);
        assertEquals(actualCustomerDto.getFirstName(), createUserDto.getFirstName());
        assertEquals(actualCustomerDto.getLastName(), createUserDto.getLastName());
        assertEquals(actualCustomerDto.getEmail(), createUserDto.getEmail());
        assertEquals(actualCustomerDto.getPassword(), createUserDto.getPassword());
        assertEquals(actualCustomerDto.getAddressDto(), createUserDto.getAddressDto());
    }

    @Test
    void viewAllCustomers_returnsACollectionOfUserDtos_andAdminsAreNotShown() {
        when(userRepository.getAllCustomers()).thenReturn(database);
        when(userMapper.toDtoCollection(List.of(customer))).thenReturn(dtoDatabase);
        assertThat(userService.viewAllCustomers()).containsExactlyInAnyOrder(customerDto);
    }

    @Test
    void getById_returnsCustomerDto_withGivenId() {
        when(userRepository.getById("id")).thenReturn(customer);
        when(userMapper.toUserDto(customer)).thenReturn(customerDto);
        assertEquals(customerDto, userService.getById("id"));
    }
}