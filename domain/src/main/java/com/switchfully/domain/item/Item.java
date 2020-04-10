package com.switchfully.domain.item;

import com.switchfully.domain.item.builders.ItemBuilder;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private double price;

    public Item(ItemBuilder itemBuilder) {
        name = itemBuilder.getName();
        description = itemBuilder.getDescription();
        price = itemBuilder.getPrice();
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }
}
