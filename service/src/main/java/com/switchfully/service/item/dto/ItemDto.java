package com.switchfully.service.item.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.user.Address;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.feature.UserRole;
import com.switchfully.service.user.dto.UserDto;
import com.switchfully.service.user.view.View;

import java.util.Objects;

public class ItemDto {
    private String id;
    private String name;
    private String description;
    private double price;

    public ItemDto() {
    }

    public ItemDto(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ItemDto(ItemDtoBuilder itemDtoBuilder) {
        id = itemDtoBuilder.getId();
        name = itemDtoBuilder.getName();
        description = itemDtoBuilder.getDescription();
        price = itemDtoBuilder.getPrice();
    }

    public String getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Double.compare(itemDto.price, price) == 0 &&
                Objects.equals(name, itemDto.name) &&
                Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }
}
