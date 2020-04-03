package com.switchfully.service.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.switchfully.service.address.AddressDto;
import com.switchfully.service.user.role.UserRoleDto;
import com.switchfully.service.user.view.View;

import java.util.Objects;

public class UserDto {
    @JsonView(View.Public.class)
    private String id;
    @JsonView(View.Public.class)
    private String firstName;
    @JsonView(View.Public.class)
    private String lastName;
    @JsonView(View.Public.class)
    private String email;
    @JsonView(View.Restricted.class)
    private UserRoleDto role;
    @JsonView(View.Restricted.class)
    private String password;
    @JsonView(View.Restricted.class)
    private AddressDto addressDto;

    public UserDto() {
    }

    public UserDto(UserDtoBuilder userDtoBuilder) {
        this.id = userDtoBuilder.getId();
        this.firstName = userDtoBuilder.getFirstName();
        this.lastName = userDtoBuilder.getLastName();
        this.email = userDtoBuilder.getEmail();
        this.role = userDtoBuilder.getRole();
        this.password = userDtoBuilder.getPassword();
        this.addressDto = userDtoBuilder.getAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(role, userDto.role) &&
                Objects.equals(addressDto, userDto.addressDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, role, addressDto);
    }

    public String getId() {
        return id;
    }
}
