package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.junit.jupiter.api.Test;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static com.switchfully.service.testbuilders.TestItemDtoBuilder.testItemDtoBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class ItemMapperTest {
    CreateItemDto createItemDto = testItemDtoBuilder().buildTestCreateItemDto();
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(0.0)
            .build();
    ItemMapper itemMapper = new ItemMapper();

    @Test
    void toNewItem_returnsItemWithSameValues() {
        Item actualItem = itemMapper.toNewItem(createItemDto);
        assertEquals(createItemDto.getName(), actualItem.getName());
        assertEquals(createItemDto.getDescription(), actualItem.getDescription());
        assertEquals(createItemDto.getPrice(), actualItem.getPrice());
    }

    @Test
    void toDto_returnsDtoWithSameValues() {
        ItemDto actualItemDto = itemMapper.toItemDto(item);
        assertEquals(item.getId(), actualItemDto.getId());
        assertEquals(item.getName(), actualItemDto.getName());
        assertEquals(item.getDescription(), actualItemDto.getDescription());
        assertEquals(item.getPrice(), actualItemDto.getPrice());
    }
}