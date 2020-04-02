package com.switchfully.service.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.switchfully.service.address.AddressDtoBuilder;

public class AddressDto {
    String street;
    String streetNumber;
    String postalCode;
    String city;

    @JsonCreator
    public AddressDto(AddressDtoBuilder addressDtoBuilder) {
        this.street = addressDtoBuilder.getStreet();
        this.streetNumber = addressDtoBuilder.getStreetNumber();
        this.postalCode = addressDtoBuilder.getPostalCode();
        this.city = addressDtoBuilder.getCity();
    }


}
