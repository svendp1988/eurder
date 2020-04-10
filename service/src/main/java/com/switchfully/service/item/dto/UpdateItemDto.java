package com.switchfully.service.item.dto;

public class UpdateItemDto {
    private String id;
    private String name;
    private String description;
    private double price;

    public UpdateItemDto() {
    }

    public UpdateItemDto(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
}
