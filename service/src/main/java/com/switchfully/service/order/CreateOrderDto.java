package com.switchfully.service.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.switchfully.service.item.dto.CreateItemDto;

import java.util.List;

public class CreateOrderDto {
    private final List<CreateItemDto> items;

    @JsonCreator
    public CreateOrderDto(List<CreateItemDto> items) {
        this.items = items;
    }

    public List<CreateItemDto> getItems() {
        return items;
    }
}
