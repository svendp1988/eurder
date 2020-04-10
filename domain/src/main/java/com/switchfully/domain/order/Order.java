package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.user.User;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Order {
    private final String id = UUID.randomUUID().toString();
    private Item item;
    private int amount;
    private LocalDate shippingDate;
    private double totalAmount;

    public Order(Item item, int amount, LocalDate shippingDate) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = shippingDate;
        totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        return item.getPrice() * amount;
    }

    public String getOrderId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
