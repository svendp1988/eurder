package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.switchfully.service.testbuilders.TestUserBuilder.testUserBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserMapper.class, UserService.class, UserRepository.class})
class UserServiceTest {
    UserMapper userMapper = new UserMapper();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService(userRepository, userMapper);

    @Test
    void whenRegisteringAsCustomer_aDtoIsReturnedWithCorrectValues() {
        User newCustomer = testUserBuilder().buildCustomer();
        when(userRepository.registerAsCustomer(newCustomer)).thenReturn(newCustomer);
        assertThat(userService.registerAsCustomer(new CreateUserDto(testUserBuilder()))).isEqualTo(new UserDto(newCustomer));
    }

    @Test
    void viewAllCustomers_returnsACollectionOfUserDtos_andAdminsAreNotShown() {
        User newCustomer = testUserBuilder().buildCustomer();
        User newAdmin = testUserBuilder().buildAdmin();
        when(userRepository.getAllCustomers()).thenReturn(List.of(newCustomer));
        assertThat(userService.viewAllCustomers()).containsExactly(userMapper.toUserDto(newCustomer));
        assertThat(userService.viewAllCustomers()).doesNotContain(userMapper.toUserDto(newAdmin));
    }

    @Test
    void getById_returnsCustomerDto_withGivenId() {
        User newCustomer = testUserBuilder().buildCustomer();
        when(userRepository.getById("1")).thenReturn(newCustomer);
        assertThat(userService.getById("1")).isEqualTo(userMapper.toUserDto(newCustomer));
    }
}