package com.switchfully.service.user;

import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.address.AddressMapper;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.service.testbuilders.TestUserDtoBuilder.testUserDtoBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserServiceTest {
    UserMapper userMapper = new UserMapper(new AddressMapper(), new UserRoleMapper());
    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository, userMapper);

    @Test
    void whenRegisteringAsCustomer_aDtoIsReturnedWithCorrectValues() {
        CreateUserDto newCustomerToCreate = testUserDtoBuilder().buildCreateUserDto();
        UserDto newCustomer = userService.registerAsCustomer(newCustomerToCreate);
        assertEquals(newCustomer.getFirstName(), newCustomerToCreate.getFirstName());
        assertEquals(newCustomer.getLastName(), newCustomerToCreate.getLastName());
        assertEquals(newCustomer.getEmail(), newCustomerToCreate.getEmail());
        assertTrue(verifyHash(newCustomerToCreate.getPassword(), newCustomer.getPassword()));
        assertEquals(newCustomer.getAddressDto(), newCustomerToCreate.getAddressDto());
    }
//
//    @Test
//    void viewAllCustomers_returnsACollectionOfUserDtos_andAdminsAreNotShown() {
////        User newCustomer = testUserBuilder().buildCustomer();
////        User newAdmin = testUserBuilder().buildAdmin();
//        when(userRepository.getAllCustomers()).thenReturn(List.of(newCustomer));
////        assertThat(userService.viewAllCustomers()).containsExactly(userMapper.toUserDto(newCustomer));
////        assertThat(userService.viewAllCustomers()).doesNotContain(userMapper.toUserDto(newAdmin));
//    }
//
//    @Test
//    void getById_returnsCustomerDto_withGivenId() {
//        User newCustomer = testUserBuilder().buildCustomer();
//        when(userRepository.getById("1")).thenReturn(newCustomer);
////        assertThat(userService.getById("1")).isEqualTo(userMapper.toUserDto(newCustomer));
//}

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}