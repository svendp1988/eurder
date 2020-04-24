package com.switchfully.service.testbuilders;

import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;

import static com.switchfully.service.item.dto.ItemDtoBuilder.itemDtoBuilder;

public class TestItemDtoBuilder {
    private long id = 1L;
    private String name = "name";
    private String description = "description";
    private double price = 0.0;
    private int amount = 0;

    private TestItemDtoBuilder() {
    }

    public static TestItemDtoBuilder testItemDtoBuilder() {
        return new TestItemDtoBuilder();
    }

    public CreateItemDto buildTestCreateItemDto() {
        return itemDtoBuilder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withAmount(amount)
                .buildCreateItemDto();
    }

    public ItemDto buildTestItemDto() {
        return itemDtoBuilder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withAmount(amount)
                .buildItemDto();
    }

    public TestItemDtoBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TestItemDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestItemDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TestItemDtoBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public TestItemDtoBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
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
