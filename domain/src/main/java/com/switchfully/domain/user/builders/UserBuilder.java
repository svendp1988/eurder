package com.switchfully.domain.user.builders;

import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.Member;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.feature.UserRole;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private String passWord;
    private String inss;
    private Address address;

    protected UserBuilder () {
    }

    public static UserBuilder userBuilder() {
        return new UserBuilder();
    }

    public User buildUser() {
        return new User(this);
    }

    public Member buildMember() {
        return new Member(this);
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

    public UserBuilder withRole(UserRole role) {
        this.role = role;
        return this;
    }

    public UserBuilder withPassWord(String passWord) {
        this.passWord = hash(passWord);
        return this;
    }

    public UserBuilder withINSS(String inss) {
        this.inss = inss;
        return this;
    }

    public UserBuilder setAddress(Address address) {
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
        return passWord;
    }

    public String getInss() {
        return inss;
    }

    public Address getAddress() {
        return address;
    }

    private String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}