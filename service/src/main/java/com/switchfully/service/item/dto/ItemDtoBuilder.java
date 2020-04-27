package com.switchfully.service.item.dto;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.builders.ItemBuilder;

public class ItemDtoBuilder {
    private long id;
    private String name;
    private String description;
    private double price;
    private int amount;

    private ItemDtoBuilder() {
    }

    public static ItemDtoBuilder itemDtoBuilder() {
        return new ItemDtoBuilder();
    }

    public CreateItemDto buildCreateItemDto() {
        return new CreateItemDto(this);
    }

    public ItemDto buildItemDto() { return new ItemDto(this);}

    public ItemDtoBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ItemDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemDtoBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemDtoBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
