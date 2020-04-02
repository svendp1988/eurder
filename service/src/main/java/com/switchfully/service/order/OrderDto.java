package com.switchfully.service.order;

import com.switchfully.service.item.dto.ItemDto;

import java.time.LocalDate;
import java.util.Map;

public class OrderDto {
    private final String id;
    private final Map<ItemDto, Integer> orders;
    private final LocalDate shippingDate;
    private final double totalAmount;

    public OrderDto(String id, Map<ItemDto, Integer> orders, LocalDate shippingDate, double totalAmount) {
        this.id = id;
        this.orders = orders;
        this.shippingDate = shippingDate;
        this.totalAmount = totalAmount;
    }
}
