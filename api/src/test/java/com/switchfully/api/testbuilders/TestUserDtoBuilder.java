package com.switchfully.api.testbuilders;

import com.switchfully.service.address.AddressDto;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.dto.UserDtoBuilder;
import com.switchfully.service.user.role.UserRoleDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.switchfully.api.testbuilders.TestAddressDtoBuilder.testAddressDtoBuilder;

public class TestUserDtoBuilder extends UserDtoBuilder {
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email";
    private UserRoleDto role;
    private String password = "password";
    private AddressDto address = new AddressDto(testAddressDtoBuilder());

    protected TestUserDtoBuilder () {
    }

    public static TestUserDtoBuilder testUserDtoBuilder() {
        return new TestUserDtoBuilder();
    }

    public UserDto buildCustomerDto() {
        role = UserRoleDto.CUSTOMER;
        return new UserDto(this);
    }

    public UserDto buildAdminDto() {
        role = UserRoleDto.ADMIN;
        return new UserDto(this);
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

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}