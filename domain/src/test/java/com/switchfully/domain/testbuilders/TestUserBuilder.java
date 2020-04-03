package com.switchfully.domain.testbuilders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.builders.UserBuilder;
import com.switchfully.domain.user.feature.UserRole;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.domain.testbuilders.TestAddressBuilder.testAddressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;

public class TestUserBuilder {
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
        return userBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withAddress(address)
                .buildCustomer();
    }

    public User buildAdmin() {
        return userBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withAddress(address)
                .buildAdmin();
    }

    public TestUserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TestUserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TestUserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TestUserBuilder withPassword(String password) {
        this.password = hash(password);
        return this;
    }

    public TestUserBuilder withAddress(Address address) {
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
