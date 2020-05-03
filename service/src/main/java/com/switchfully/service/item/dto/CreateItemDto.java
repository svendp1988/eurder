package com.switchfully.service.item.dto;

public class CreateItemDto {
    private String name;
    private String description;
    private double price;
    private int amount;
    private String imageUrl;

    public CreateItemDto() {
    }

    public CreateItemDto(String name, String description, double price, int amount, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.imageUrl = imageUrl;
    }

    public CreateItemDto(ItemDtoBuilder itemDtoBuilder) {
        name = itemDtoBuilder.getName();
        description = itemDtoBuilder.getDescription();
        price = itemDtoBuilder.getPrice();
        amount = itemDtoBuilder.getAmount();
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

    public String getImageUrl() {
        return imageUrl;
    }
}
