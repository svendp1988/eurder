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
    private Map<Item, Integer> items;
    private LocalDate shippingDate;
    private double totalAmount;

    public Order(Map<Item, Integer> items, LocalDate shippingDate) {
        this.items = new ConcurrentHashMap<>(items);
        this.shippingDate = shippingDate;
        totalAmount = calculateTotalAmount();
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

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
