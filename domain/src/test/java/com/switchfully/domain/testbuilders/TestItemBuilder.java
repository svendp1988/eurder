package com.switchfully.domain.testbuilders;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.builders.ItemBuilder;

public class TestItemBuilder extends ItemBuilder {
    private String name = "name";
    private String description = "description";
    private double price = 0.0;
    private int amount = 0;

    protected TestItemBuilder() {
    }

    public static TestItemBuilder testItemBuilder() {
        return new TestItemBuilder();
    }

    public Item build() {
        return new Item(this);
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
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
