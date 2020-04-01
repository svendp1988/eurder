package com.switchfully.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.switchfully.domain.user.builders.AddressBuilder;

import java.util.UUID;

public class Address {
    private final String id;
    private final String street;
    private final String streetNumber;
    private final String postalCode;
    private final String city;

    @JsonCreator
    public Address(AddressBuilder addressBuilder) {
        id = UUID.randomUUID().toString();
        street = addressBuilder.getStreet();
        streetNumber = addressBuilder.getStreetNumber();
        postalCode = addressBuilder.getPostalCode();
        city = addressBuilder.getCity();
    }
}