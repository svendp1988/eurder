package com.switchfully.service.testbuilders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.builders.UserBuilder;
import com.switchfully.domain.user.feature.UserRole;
import com.switchfully.service.address.AddressDto;
import com.switchfully.service.user.dto.CreateUserDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.role.UserRoleDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.domain.user.builders.AddressBuilder.addressBuilder;
import static com.switchfully.domain.user.builders.UserBuilder.userBuilder;
import static com.switchfully.service.testbuilders.TestAddressDtoBuilder.testAddressDtoBuilder;
import static com.switchfully.service.user.dto.UserDtoBuilder.userDtoBuilder;

public class TestUserDtoBuilder {
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email";
    private UserRoleDto role;
    private String password = "password";
    private AddressDto address = testAddressDtoBuilder().build();

    private TestUserDtoBuilder() {
    }

    public static TestUserDtoBuilder testUserDtoBuilder() {
        return new TestUserDtoBuilder();
    }

    public UserDto buildCustomerDto() {
        return userDtoBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withRole(UserRoleDto.CUSTOMER)
                .withPassword(password)
                .withAddress(address)
                .buildUserDto();
    }

    public UserDto buildAdminDto() {
        return userDtoBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withRole(UserRoleDto.ADMIN)
                .withPassword(password)
                .withAddress(address)
                .buildUserDto();
    }

    public CreateUserDto buildCreateUserDto() {
        return userDtoBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withRole(role)
                .withPassword(password)
                .withAddress(address)
                .buildCreateUserDto();
    }

    public User buildCustomer() {
        return userBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withAddress(addressBuilder()
                        .withStreet("street")
                        .withStreetNumber("streetNumber")
                        .withPostalCode("1000")
                        .withCity("city")
                        .build())
                .buildCustomer();
    }

    public User buildAdmin() {
        return userBuilder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withAddress(addressBuilder()
                        .withStreet("street")
                        .withStreetNumber("streetNumber")
                        .withPostalCode("1000")
                        .withCity("city")
                        .build())
                .buildAdmin();
    }

    public TestUserDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TestUserDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TestUserDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TestUserDtoBuilder withPassword(String password) {
        this.password = hash(password);
        return this;
    }

    public TestUserDtoBuilder withAddress(AddressDto address) {
        this.address = address;
        return this;
    }

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
