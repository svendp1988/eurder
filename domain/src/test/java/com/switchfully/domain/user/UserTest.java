package com.switchfully.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.testbuilders.TestUserBuilder.testUserBuilder;
import static com.switchfully.domain.user.feature.UserRole.CUSTOMER;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User customer;
    User admin;
    Address address;

    @BeforeEach
    void init() {
        customer = testUserBuilder().buildCustomer();
        admin = testUserBuilder().buildAdmin();
        address = testAddressBuilder().build();
    }

    @Test
    void customerContainsCorrectValue_andIdIsAutomaticallyFilledIn() {
        assertNotNull(customer.getId());
        assertEquals("firstName", customer.getFirstName());
        assertEquals("lastName", customer.getLastName());
        assertEquals("email", customer.getEmail());
        assertEquals(CUSTOMER, customer.getRole());
        assertTrue(verifyHash("password", customer.getPassword()));
        assertEquals(address, customer.getAddress());
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}