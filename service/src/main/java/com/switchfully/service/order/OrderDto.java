package com.switchfully.service.order;

import com.switchfully.service.item.dto.ItemDto;

import java.time.LocalDate;
import java.util.Map;

public class OrderDto {
    private String id;
    private Map<ItemDto, Integer> orders;
    private LocalDate shippingDate;
    private double totalAmount;

    public OrderDto() {
    }

    public OrderDto(String id, Map<ItemDto, Integer> orders, LocalDate shippingDate, double totalAmount) {
        this.id = id;
        this.orders = orders;
        this.shippingDate = shippingDate;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public Map<ItemDto, Integer> getOrders() {
        return orders;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
