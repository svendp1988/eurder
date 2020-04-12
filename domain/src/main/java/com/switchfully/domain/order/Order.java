package com.switchfully.domain.order;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.user.User;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Order {
    private final String orderId = UUID.randomUUID().toString();
    private Map<Item, Integer> orders;
    private double totalAmount;

    public Order(Map<Item, Integer> orders) {
        this.orders = orders;
        totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        return orders.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<Item, Integer> getOrders() {
        return orders;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
