package com.switchfully.service.address;

public class AddressDto {
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;

    public AddressDto() {
    }

    public AddressDto(AddressDtoBuilder addressDtoBuilder) {
        this.street = addressDtoBuilder.getStreet();
        this.streetNumber = addressDtoBuilder.getStreetNumber();
        this.postalCode = addressDtoBuilder.getPostalCode();
        this.city = addressDtoBuilder.getCity();
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
