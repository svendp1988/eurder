package com.switchfully.service.address;

import com.switchfully.domain.user.Address;
import org.springframework.stereotype.Component;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.service.address.AddressDtoBuilder.addressDtoBuilder;

@Component
public class AddressMapper {
    public Address toAddress(AddressDto addressDto) {
        return addressBuilder()
                .withStreet(addressDto.getStreet())
                .withStreetNumber(addressDto.getStreetNumber())
                .withPostalCode(addressDto.getPostalCode())
                .withCity(addressDto.getCity())
                .build();
    }

    public AddressDto toDto(Address address) {
        return new AddressDto(
                address.getStreet(),
                address.getStreetNumber(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}

