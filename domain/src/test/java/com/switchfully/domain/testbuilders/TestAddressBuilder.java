package com.switchfully.domain.testbuilders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.builders.AddressBuilder;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;

public class TestAddressBuilder {
    private String street = "street";
    private String streetNumber = "streetNumber";
    private String postalCode = "postalCode";
    private String city = "city";

    private TestAddressBuilder() {
    }

    public static TestAddressBuilder testAddressBuilder() {
        return new TestAddressBuilder();
    }

    public Address build(){
        return addressBuilder()
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withPostalCode(postalCode)
                .withCity(city)
                .build();
    }

    public TestAddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public TestAddressBuilder withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public TestAddressBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public TestAddressBuilder withCity(String city) {
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
