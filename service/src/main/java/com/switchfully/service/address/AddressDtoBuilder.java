package com.switchfully.service.address;

public class AddressDtoBuilder {
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;

    protected AddressDtoBuilder() {
    }

    public static AddressDtoBuilder addressDtoBuilder() {
        return new AddressDtoBuilder();
    }

    public AddressDto build(){
        return new AddressDto(this);
    }

    public AddressDtoBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressDtoBuilder withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public AddressDtoBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public AddressDtoBuilder withCity(String city) {
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
