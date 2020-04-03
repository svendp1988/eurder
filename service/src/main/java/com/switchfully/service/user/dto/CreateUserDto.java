package com.switchfully.service.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchfully.service.address.AddressDto;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AddressDto addressDto;

    public CreateUserDto() {
    }

    public CreateUserDto(UserDtoBuilder userDtoBuilder) {
        this.firstName = userDtoBuilder.getFirstName();
        this.lastName = userDtoBuilder.getLastName();
        this.email = userDtoBuilder.getEmail();
        this.password = userDtoBuilder.getPassword();
        this.addressDto = userDtoBuilder.getAddress();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

}
