package com.switchfully.service.item.dto;

public class UpdateItemDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private int amount;

    public UpdateItemDto() {
    }

    public UpdateItemDto(long id, String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
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
