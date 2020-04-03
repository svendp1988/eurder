package com.switchfully.api.testbuilders;

import com.switchfully.domain.user.builders.AddressBuilder;
import com.switchfully.service.address.AddressDto;
import com.switchfully.service.address.AddressDtoBuilder;

import static com.switchfully.service.address.AddressDtoBuilder.addressDtoBuilder;

public class TestAddressDtoBuilder {
    private String street = "street";
    private String streetNumber = "streetNumber";
    private String postalCode = "postalCode";
    private String city = "city";

    private TestAddressDtoBuilder() {
    }

    public static TestAddressDtoBuilder testAddressDtoBuilder() {
        return new TestAddressDtoBuilder();
    }

    public AddressDto buildAddressDto(){
        return addressDtoBuilder()
                .withStreet(street)
                .withStreetNumber(streetNumber)
                .withPostalCode(postalCode)
                .withCity(city)
                .build();
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
