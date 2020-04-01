package com.switchfully.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.switchfully.domain.user.builders.AddressBuilder;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, postalCode, city);
    }
}