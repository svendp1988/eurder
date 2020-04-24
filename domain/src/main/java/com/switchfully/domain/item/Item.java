package com.switchfully.domain.item;

import com.sun.istack.Nullable;
import com.switchfully.domain.item.builders.ItemBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(generator = "item_id_seq", strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "shipping_date")
    @Nullable
    private LocalDate shippingDate;
    @Column
    private int amount;

    public Item() {}

    public Item(Item item) {
        id = item.getId();
        name = item.getName();
        description = item.getDescription();
        price = item.getPrice();
        amount = item.getAmount();
    }

    public Item(ItemBuilder itemBuilder) {
        name = itemBuilder.getName();
        description = itemBuilder.getDescription();
        price = itemBuilder.getPrice();
    }

    public long getId() { return id; }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
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
