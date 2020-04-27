package com.switchfully.service.item;

import com.switchfully.domain.exceptions.ItemNotFoundException;
import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static com.switchfully.service.testbuilders.TestItemDtoBuilder.testItemDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    List<ItemDto> items = List.of(itemDto);

    @Mock
    ItemMapper itemMapper;
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    void addItem_returnsDtoWithSameValues() {
        when(itemRepository.save(any(Item.class))).thenReturn(item);
        when(itemMapper.toNewItem(any(CreateItemDto.class))).thenReturn(item);
        when(itemMapper.toItemDto(item)).thenReturn(itemDto);
        ItemDto actual = itemService.addItem(createItemDto);
        assertThat(actual).isEqualToIgnoringGivenFields(createItemDto,"id", "shippingDate");
    }

    @Test
    void findById_throwsException_whenItemIsNotFound() {
        assertThrows(ItemNotFoundException.class, () -> itemService.findById(20L));
    }
}