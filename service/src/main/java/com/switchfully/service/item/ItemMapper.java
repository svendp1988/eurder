package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;

@Component
public class ItemMapper {
    public Item toNewItem(CreateItemDto createItemDto) {
        return itemBuilder()
                .withName(createItemDto.getName())
                .withDescription(createItemDto.getDescription())
                .withPrice(createItemDto.getPrice())
                .withAmount(createItemDto.getAmount())
                .withImageUrl(createItemDto.getImageUrl())
                .build();
    }

    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getShippingDate(),
                item.getAmount(),
                item.getImageUrl());
    }

    public List<ItemDto> toItemDto(List<Item> items) {
        return items.stream()
                .map(this::toItemDto)
                .collect(Collectors.toList());
    }
}
