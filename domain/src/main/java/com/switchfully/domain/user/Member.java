package com.switchfully.domain.user;

import com.switchfully.domain.user.builders.UserBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member extends User {

    private final String INSS;
    private final Address address;

    public Member(UserBuilder userBuilder) {
        super(userBuilder);
        this.INSS = userBuilder.getInss();
        this.address = userBuilder.getAddress();
    }

    public String getINSS() {
        return INSS;
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getPassword() {
        return super.getPassword();
    }

    public Address getAddress() {
        return address;
    }

    public String getId() {
        return super.getId();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(INSS, member.INSS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(INSS);
    }
}