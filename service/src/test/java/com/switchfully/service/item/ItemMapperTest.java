package com.switchfully.service.item;

import com.switchfully.domain.item.Item;
import com.switchfully.domain.item.ItemRepository;
import com.switchfully.service.item.ItemMapper;
import com.switchfully.service.item.ItemService;
import com.switchfully.service.item.dto.CreateItemDto;
import com.switchfully.service.item.dto.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.switchfully.domain.item.builders.ItemBuilder.itemBuilder;
import static com.switchfully.service.testbuilders.TestItemDtoBuilder.testItemDtoBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class ItemMapperTest {
    CreateItemDto createItemDto = testItemDtoBuilder().buildTestCreateItemDto();
    Item item = itemBuilder()
            .withName("name")
            .withDescription("description")
            .withPrice(0.0)
            .withAmount(1)
            .build();
    ItemMapper itemMapper = new ItemMapper();

    @Test
    void toNewItem_returnsItemWithSameValues() {
        Item actualItem = itemMapper.toNewItem(createItemDto);
        assertThat(actualItem).isEqualToComparingOnlyGivenFields(createItemDto, "name", "description", "price");
    }

    @Test
    void toDto_returnsDtoWithSameValues() {
        ItemDto actualItemDto = itemMapper.toItemDto(item);
        assertThat(actualItemDto).isEqualToComparingFieldByField(item);
    }

    @Test
    void toDtoOfList_returnsListOfDtos() {
        Item item = itemBuilder().build();
        List<Item> items = List.of(item);
        ItemDto expectedInList = itemMapper.toItemDto(item);
        List<ItemDto> actual = itemMapper.toItemDto(items);
        assertThat(actual).containsExactly(expectedInList);
    }
}