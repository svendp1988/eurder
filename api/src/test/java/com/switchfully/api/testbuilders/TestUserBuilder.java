package com.switchfully.api.testbuilders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.builders.UserBuilder;
import com.switchfully.domain.user.feature.UserRole;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.api.testbuilders.TestAddressBuilder.testAddressBuilder;

public class TestUserBuilder extends UserBuilder {
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email";
    private UserRole role;
    private String password = "password";
    private Address address = testAddressBuilder().build();

    protected TestUserBuilder () {
    }

    public static TestUserBuilder testUserBuilder() {
        return new TestUserBuilder();
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
