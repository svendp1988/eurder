package com.switchfully.api.testbuilders;

import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.item.dto.UpdateItemDto;

public class TestItemDtoBuilder {
    private String id = "id";
    private String name = "name";
    private String description = "description";
    private double price = 0.0;

    private TestItemDtoBuilder() {
    }

    public static TestItemDtoBuilder testItemDtoBuilder() {
        return new TestItemDtoBuilder();
    }

    public UpdateItemDto buildTestUpdateItemDto() { return new UpdateItemDto(id, name, description, price); }

    public CreateItemDto buildTestCreateItemDto() {
        return new CreateItemDto(name, description, price);
    }

    public ItemDto buildTestItemDto() {
        return new ItemDto(id, name, description, price, null);
    }

    public TestItemDtoBuilder withId(String id) {
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
