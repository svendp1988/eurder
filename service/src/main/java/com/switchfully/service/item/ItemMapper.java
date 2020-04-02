package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.springframework.stereotype.Component;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static com.switchfully.service.item.dto.ItemDtoBuilder.itemDtoBuilder;

@Component
public class ItemMapper {
    public Item toNewItem(CreateItemDto createItemDto) {
        return itemBuilder()
                .withName(createItemDto.getName())
                .withDescription(createItemDto.getDescription())
                .withPrice(createItemDto.getPrice())
                .build();
    }

    public ItemDto toItemDto(Item item) {
        return itemDtoBuilder()
                .withId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .withPrice(item.getPrice())
                .buildItemDto();
    }
}