package com.switchfully.api.endpoints;

public class OrderRequest {
    String itemId;
    Integer amount;

    public OrderRequest() {
    }

    public OrderRequest(String itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
}
