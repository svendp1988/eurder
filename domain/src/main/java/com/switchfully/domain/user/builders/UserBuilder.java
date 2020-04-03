package com.switchfully.domain.user.builders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.feature.UserRole;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String password;
    private Address address;

    private UserBuilder () {
    }

    public static UserBuilder userBuilder() {
        return new UserBuilder();
    }

    public User buildCustomer() {
        role = UserRole.CUSTOMER;
        return new User(this);
    }

    public User buildAdmin() {
        role = UserRole.ADMIN;
        return new User(this);
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = hash(password);
        return this;
    }

    public UserBuilder withAddress(Address address) {
        this.address = address;
        return this;
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

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}