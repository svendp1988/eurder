package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.user.User;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final String id = UUID.randomUUID().toString();
    private final Map<Item, Integer> items;
    private LocalDate shippingDate;
    private double totalAmount = calculateTotalAmount();

    public Order(Map<Item, Integer> items) {
        this.items = items;
    }

    private double calculateTotalAmount() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public String getOrderId() {
        return id;
    }


    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
