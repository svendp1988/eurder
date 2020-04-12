package com.switchfully.service.item.dto;

import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDate;
import java.util.Objects;

public class ItemDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private LocalDate shippingDate;

    public ItemDto() {
    }

    public ItemDto(String id, String name, String description, double price, LocalDate shippingDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shippingDate = shippingDate;
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

    public LocalDate getShippingDate() {
        return shippingDate;
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

    @Override
    @JsonValue
    public String toString() {
        return "ItemDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", shippingDate=" + shippingDate +
                '}';
    }
}
