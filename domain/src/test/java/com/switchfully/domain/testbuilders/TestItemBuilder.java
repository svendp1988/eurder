package com.switchfully.domain.testbuilders;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.builders.ItemBuilder;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;

public class TestItemBuilder {
    private String name = "name";
    private String description = "description";
    private double price = 0.0;

    private TestItemBuilder() {
    }

    public static TestItemBuilder testItemBuilder() {
        return new TestItemBuilder();
    }

    public Item build() {
        return itemBuilder()
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .build();
    }

    public TestItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TestItemBuilder withPrice(double price) {
        this.price = price;
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
}
