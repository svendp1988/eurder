package com.switchfully.domain.user.builders;

import org.junit.jupiter.api.Test;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static org.junit.jupiter.api.Assertions.*;

class AddressBuilderTest {

    @Test
    void whenBuildingAddres_correctValuesAreReturned() {
        AddressBuilder addressBuilder = addressBuilder()
                .withCity("city")
                .withStreet("street")
                .withStreetNumber("streetNumber")
                .withPostalCode("postalCode");
        assertEquals("city", addressBuilder.getCity());
        assertEquals("street", addressBuilder.getStreet());
        assertEquals("streetNumber", addressBuilder.getStreetNumber());
        assertEquals("postalCode", addressBuilder.getPostalCode());
    }
}