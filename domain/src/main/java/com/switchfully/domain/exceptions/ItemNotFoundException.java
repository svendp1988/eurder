package com.switchfully.domain.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(long id) {
        super(String.format("Cannot find an item with id %d", id));
    }
}
