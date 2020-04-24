package com.switchfully.api.endpoints;

import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import com.switchfully.service.item.dto.UpdateItemDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static com.switchfully.api.testbuilders.TestItemDtoBuilder.testItemDtoBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {
    ItemDto itemDto = testItemDtoBuilder().buildTestItemDto();
    UpdateItemDto updateItemDto = testItemDtoBuilder()
            .withName("updatedName")
            .withDescription("updatedDescription")
            .withPrice(5)
            .buildTestUpdateItemDto();
    CreateItemDto createItemDto = testItemDtoBuilder().buildTestCreateItemDto();
    Map<String, Integer> items = Map.of("name", 1);

    @Mock
    ItemService itemService;

    @InjectMocks
    ItemController itemController;

    @Test
    void addItem_returnsDto() {
        when(itemService.addItem(createItemDto)).thenReturn(itemDto);
        assertEquals(itemDto, itemController.addItem(createItemDto));
    }

    @Test
    void viewAllItems_returnsMapFromService() {
//        when(itemService.findAll()).thenReturn(items);
        assertEquals(items, itemController.viewAllItems());
    }

    @Test
    void getItemByName_returnsCorrectItemFromService() {
//        when(itemService.getItemById("id")).thenReturn(itemDto);
//        assertEquals(itemDto, itemController.getItemById("id"));
    }

    @Test
    void updateItem_returnsDtoWithUpdatedValues() {
        ItemDto itemDto = new ItemDto(updateItemDto.getId(), updateItemDto.getName(), updateItemDto.getDescription(), updateItemDto.getPrice(), null, 2);
        when(itemService.updateItem(updateItemDto)).thenReturn(itemDto);
        ItemDto actualItemDto = itemController.updateItem(updateItemDto);
        assertEquals(actualItemDto.getId(), updateItemDto.getId());
        assertEquals(actualItemDto.getName(), updateItemDto.getName());
        assertEquals(actualItemDto.getDescription(), updateItemDto.getDescription());
        assertEquals(actualItemDto.getPrice(), updateItemDto.getPrice());
    }
}