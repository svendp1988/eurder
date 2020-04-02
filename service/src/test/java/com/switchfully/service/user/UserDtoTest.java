package com.switchfully.service.user;

import com.switchfully.domain.user.Address;
import com.switchfully.service.user.dto.UserDto;
import org.junit.jupiter.api.Test;

import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.service.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.service.testbuilders.TestUserBuilder.testUserBuilder;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDtoTest {
    @Test
    void testEquals() {
//        UserDto userDto1 = new UserDto(testUserBuilder().buildCustomer());
//        Address address = testAddressBuilder().build();
//        UserDto userDto2 = new UserDto(userBuilder()
//                .withFirstName("firstName")
//                .withLastName("lastName")
//                .withEmail("email")
//                .withPassword("password")
//                .withAddress(address)
//                .buildCustomer());
//        assertTrue(userDto1.equals(userDto2));
    }
}