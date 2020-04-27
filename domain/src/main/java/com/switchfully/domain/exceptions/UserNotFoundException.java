package com.switchfully.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super(String.format("Could not find a user with id %d.", id));
    }
}
