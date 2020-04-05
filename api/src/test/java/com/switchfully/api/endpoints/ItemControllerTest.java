package com.switchfully.api.endpoints;

import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
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
        when(itemService.viewAllItems()).thenReturn(items);
        assertEquals(items, itemController.viewAllItems());
    }

    @Test
    void getItemByName_returnsCorrectItemFromService() {
        when(itemService.getItemById("id")).thenReturn(itemDto);
        assertEquals(itemDto, itemController.getItemById("id"));
    }
}