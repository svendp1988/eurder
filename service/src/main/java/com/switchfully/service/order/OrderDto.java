package com.switchfully.service.order;

import com.google.gson.Gson;
import com.switchfully.service.item.dto.ItemDto;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class OrderDto {
    private String id;
    private ItemDto itemDto;
    private int amount;
    private LocalDate shippingDate;
    private double totalAmount;

    public OrderDto() {
    }

    public OrderDto(String id, ItemDto itemDto, int amount, LocalDate shippingDate, double totalAmount) {
        this.id = id;
        this.itemDto = itemDto;
        this.amount = amount;
        this.shippingDate = shippingDate;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public ItemDto getItemDto() {
        return itemDto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return amount == orderDto.amount &&
                Double.compare(orderDto.totalAmount, totalAmount) == 0 &&
                Objects.equals(itemDto, orderDto.itemDto) &&
                Objects.equals(shippingDate, orderDto.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemDto, amount, shippingDate, totalAmount);
    }
}
