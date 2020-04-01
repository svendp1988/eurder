package com.switchfully.domain.user.builders;

import com.switchfully.domain.user.Address;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static org.junit.jupiter.api.Assertions.*;

class UserBuilderTest {
    @Test
    void userBuilder_returnsCorrectValues() {
        Address address = testAddressBuilder().build();
        UserBuilder userBuilder = userBuilder()
                .withFirstName("firstName")
                .withLastName("lastName")
                .withEmail("email")
                .withPassword("password")
                .withAddress(address);
        assertEquals("firstName", userBuilder.getFirstName());
        assertEquals("lastName", userBuilder.getLastName());
        assertEquals("email", userBuilder.getEmail());
        assertTrue(verifyHash("password", userBuilder.getPassword()));
        assertEquals(address, userBuilder.getAddress());
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}