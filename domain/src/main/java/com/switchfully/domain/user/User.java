package com.switchfully.domain.user;

import com.switchfully.domain.user.builders.UserBuilder;
import com.switchfully.domain.user.feature.UserRole;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String id = UUID.randomUUID().toString();
    private final String firstName;
    private final String lastName;
    private final String email;
    private final UserRole role;
    private String password;
    private Address address;

    public User(UserBuilder userBuilder) {
        firstName = userBuilder.getFirstName();
        lastName = userBuilder.getLastName();
        email = userBuilder.getEmail();
        role = userBuilder.getRole();
        password = userBuilder.getPassword();
        address = userBuilder.getAddress();
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

    public UserRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(role, user.role) &&
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, role, address);
    }
}