package com.switchfully.service.order;

import com.switchfully.service.item.dto.ItemDto;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

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

    public Map<ItemDto, Integer> getItems() {
        return orders;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Double.compare(orderDto.totalAmount, totalAmount) == 0 &&
                Objects.equals(orders, orderDto.orders) &&
                Objects.equals(shippingDate, orderDto.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders, shippingDate, totalAmount);
    }
}
