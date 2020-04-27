package com.switchfully.domain.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(long id) {
        super(String.format("Could not find an item with id %d.", id));
    }
}
