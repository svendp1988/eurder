package com.switchfully.service.address;

import java.util.Objects;

public class AddressDto {
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;

    public AddressDto() {
    }

    public AddressDto(String street, String streetNumber, String postalCode, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(street, that.street) &&
                Objects.equals(streetNumber, that.streetNumber) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, postalCode, city);
    }
}
