package com.switchfully.api.testbuilders;

import com.switchfully.domain.user.builders.AddressBuilder;
import com.switchfully.service.address.AddressDto;
import com.switchfully.service.address.AddressDtoBuilder;

public class TestAddressDtoBuilder extends AddressDtoBuilder {
    private String street = "street";
    private String streetNumber = "streetNumber";
    private String postalCode = "postalCode";
    private String city = "city";

    private TestAddressDtoBuilder() {
    }

    public static TestAddressDtoBuilder testAddressDtoBuilder() {
        return new TestAddressDtoBuilder();
    }

    public AddressDto build(){
        return new AddressDto(this);
    }

    public TestAddressDtoBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public TestAddressDtoBuilder withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public TestAddressDtoBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public TestAddressDtoBuilder withCity(String city) {
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
