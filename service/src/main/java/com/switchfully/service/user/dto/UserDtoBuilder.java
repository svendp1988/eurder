package com.switchfully.service.user.dto;

import com.switchfully.service.address.AddressDto;
import com.switchfully.service.user.role.UserRoleDto;

public class UserDtoBuilder {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRoleDto role;
    private String password;
    private AddressDto address;

    protected UserDtoBuilder () {
    }

    public static UserDtoBuilder userDtoBuilder() {
        return new UserDtoBuilder();
    }

    public UserDto build() {
        return new UserDto(this);
    }

    public UserDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public UserDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder withRole(UserRoleDto role) {
        this.role = role;
        return this;
    }


    public UserDtoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDtoBuilder withAddress(AddressDto address) {
        this.address = address;
        return this;
    }

    public String getId() {
        return id;
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

    public UserRoleDto getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public AddressDto getAddress() {
        return address;
    }
}