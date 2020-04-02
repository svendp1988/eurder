package com.switchfully.service.item.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateItemDto {
    private String name;
    private String description;
    private double price;

    @JsonCreator
    public CreateItemDto(@JsonProperty ItemDtoBuilder itemDtoBuilder) {
        name = itemDtoBuilder.getName();
        description = itemDtoBuilder.getDescription();
        price = itemDtoBuilder.getPrice();
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
}
