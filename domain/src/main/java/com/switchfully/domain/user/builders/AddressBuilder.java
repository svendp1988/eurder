package com.switchfully.domain.user.builders;

import com.switchfully.domain.user.Address;


public class AddressBuilder {

    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;

    private AddressBuilder() {
    }

    public static AddressBuilder addressBuilder() {
        return new AddressBuilder();
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