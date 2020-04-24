package com.switchfully.service.item.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.switchfully.service.serializers.LocalDateSerializer;
import com.switchfully.service.user.view.View;

import java.time.LocalDate;
import java.util.Objects;

public class ItemDto {
    @JsonView(View.Public.class)
    private long id;
    @JsonView(View.Public.class)
    private String name;
    @JsonView(View.Public.class)
    private String description;
    @JsonView(View.Public.class)
    private double price;
    @JsonView(View.Restricted.class)
    @JsonSerialize(keyUsing = LocalDateSerializer.class)
    private LocalDate shippingDate;
    @JsonView(View.Restricted.class)
    private int amount;

    public ItemDto() {
    }

    public ItemDto(long id, String name, String description, double price, LocalDate shippingDate, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shippingDate = shippingDate;
        this.amount = amount;
    }

    public ItemDto(ItemDtoBuilder itemDtoBuilder) {
        id = itemDtoBuilder.getId();
        name = itemDtoBuilder.getName();
        description = itemDtoBuilder.getDescription();
        price = itemDtoBuilder.getPrice();
        amount = itemDtoBuilder.getAmount();
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

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public int getAmount() {
        return amount;
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
//    @JsonValue
    public String toString() {
        return "ItemDto{" +
                "id='" + id +
                ", name='" + name +
                ", description='" + description +
                ", price=" + price +
                ", shippingDate=" + shippingDate +
                '}';
    }
}
