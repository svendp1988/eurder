package com.switchfully.service.order;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.serializers.ItemDtoSerializer;

import java.util.Map;
import java.util.Objects;

public class OrderDto {
    private String orderId;
    @JsonSerialize(keyUsing = ItemDtoSerializer.class)
    private Map<ItemDto, Integer> orders;
    private double totalAmount;

    public OrderDto() {
    }

    public OrderDto(String id, Map<ItemDto, Integer> orders, double totalAmount) {
        this.orderId = id;
        this.orders = orders;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return orderId;
    }

    public Map<ItemDto, Integer> getOrders() {
        return orders;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return Double.compare(orderDto.getTotalAmount(), getTotalAmount()) == 0 &&
                Objects.equals(getOrders(), orderDto.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrders(), getTotalAmount());
    }
}
