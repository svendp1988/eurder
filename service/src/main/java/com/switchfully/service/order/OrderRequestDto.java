package com.switchfully.service.order;

import java.util.Map;

public class OrderRequestDto {
    private Map<String, Integer> order;

    public OrderRequestDto() {
    }

    public OrderRequestDto(Map<String, Integer> order) {
        this.order = order;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }
}
