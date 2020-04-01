package com.switchfully.domain.user;

import org.junit.jupiter.api.Test;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testEquals() {
        Address address1 = testAddressBuilder().build();
        Address address2 = addressBuilder()
                .withCity("city")
                .withStreet("street")
                .withStreetNumber("streetNumber")
                .withPostalCode("postalCode")
                .build();
        assertTrue(address1.equals(address2));
    }
}