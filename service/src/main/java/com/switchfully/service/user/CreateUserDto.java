package com.switchfully.service.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.builders.UserBuilder;

public class CreateUserDto extends User {
    @JsonCreator
    public CreateUserDto(@JsonProperty UserBuilder userBuilder) {
        super(userBuilder);
    }
}
