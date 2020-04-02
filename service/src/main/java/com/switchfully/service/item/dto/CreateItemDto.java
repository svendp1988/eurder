package com.switchfully.service.item.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.builders.ItemBuilder;

public class CreateItemDto extends Item {

    @JsonCreator
    public CreateItemDto(@JsonProperty ItemBuilder itemBuilder) {
        super(itemBuilder);
    }
}
