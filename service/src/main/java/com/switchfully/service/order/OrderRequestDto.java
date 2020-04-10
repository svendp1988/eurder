package com.switchfully.service.order;

public class OrderRequestDto {
    private String itemId;
    private Integer amount;

    public OrderRequestDto() {
    }

    public OrderRequestDto(String itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getAmount() {
        return amount;
    }
}
