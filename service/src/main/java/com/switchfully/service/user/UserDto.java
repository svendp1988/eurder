package com.switchfully.service.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.feature.UserRole;

import java.util.Objects;

public class UserDto {
    @JsonView(View.Public.class)
    private final String id;
    @JsonView(View.Public.class)
    private final String firstName;
    @JsonView(View.Public.class)
    private final String lastName;
    @JsonView(View.Public.class)
    private final String email;
    @JsonView(View.Restricted.class)
    private final UserRole role;
    @JsonView(View.Restricted.class)
    private String password;
    @JsonView(View.Restricted.class)
    private Address address;

    public UserDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        role = user.getRole();
        password = user.getPassword();
        address = user.getAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(role, userDto.role) &&
                Objects.equals(address, userDto.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, role, address);
    }
}
