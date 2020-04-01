package com.switchfully.service.testbuilders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.builders.AddressBuilder;

public class TestAddressBuilder extends AddressBuilder {
    private String street = "street";
    private String streetNumber = "streetNumber";
    private String postalCode = "postalCode";
    private String city = "city";

    protected TestAddressBuilder() {
    }

    public static TestAddressBuilder testAddressBuilder() {
        return new TestAddressBuilder();
    }

    public Address build(){
        return new Address(this);
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public AddressBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}
