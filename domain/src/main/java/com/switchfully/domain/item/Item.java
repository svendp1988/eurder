package com.switchfully.domain.item;

import com.switchfully.domain.item.builders.ItemBuilder;

public class Item {
    private String name;
    private String description;
    private double price;
    private int amount;

    public Item(ItemBuilder itemBuilder) {
        name = itemBuilder.getName();
        description = itemBuilder.getDescription();
        price = itemBuilder.getPrice();
        amount = itemBuilder.getAmount();
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
