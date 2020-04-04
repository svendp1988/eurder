package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static com.switchfully.service.testbuilders.TestItemDtoBuilder.testItemDtoBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(0.0)
            .build();
    CreateItemDto createItemDto = testItemDtoBuilder().buildTestCreateItemDto();
    ItemDto itemDto = testItemDtoBuilder().buildTestItemDto();
    Map<String, Integer> items = Map.of("name", 1);

    @Mock
    ItemMapper itemMapper;
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    void addItem_returnsDtoWithSameValues() {
        when(itemRepository.addItem(item)).thenReturn(item);
        when(itemMapper.toNewItem(createItemDto)).thenReturn(item);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        ItemDto actualReturnedItemDto = itemService.addItem(createItemDto);
        assertEquals(createItemDto.getName(), actualReturnedItemDto.getName());
        assertEquals(createItemDto.getDescription(), actualReturnedItemDto.getDescription());
        assertEquals(createItemDto.getPrice(), actualReturnedItemDto.getPrice());
    }

    @Test
    void viewAllItems_returnsMap_groupingTheItemsByName_andCountingTheStock() {
        when(itemRepository.viewAllItems()).thenReturn(items);
        assertEquals(items, itemService.viewAllItems());
    }

    @Test
    void getItemByName_returnsItemByLookingUpNameInDatabase() {
        when(itemRepository.getItemByName("name")).thenReturn(item);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        assertEquals(itemDto, itemService.getItemByName("name"));
    }
}